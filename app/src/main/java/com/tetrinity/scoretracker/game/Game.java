package com.tetrinity.scoretracker.game;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.tetrinity.scoretracker.BR;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Game extends BaseObservable implements Serializable {

    // static fields

    public static final Integer DEFAULT_PLAYER_COUNT = 2;

    // fields

    private Integer gameId = -1;

    private String gameName = "Untitled";
    private Date gameDate = new Date();

    private ArrayList<String> playerNames = new ArrayList<>();
    private ArrayList<List<Move>> moves = new ArrayList<>();

    private boolean isWordMode = false;

    // constructors

    public Game(){
        initData();
    }

    public Game(Context context){
        initData();
        save(context);
    }

    // data loading

    private void initData(){
        setPlayerCount(DEFAULT_PLAYER_COUNT);
        addMoveRow();
    }

    public static Game load(Context context, int gameId){
        File saveFile = getSaveFile(context, gameId);
        if (!saveFile.exists()){ return null; }

        return load(saveFile);
    }
    public static Game load(File saveFile){
        Game game = null;

        try {
            FileInputStream fis = new FileInputStream(saveFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            game = (Game)ois.readObject();
            ois.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        return game;
    }
    public void save(Context context){
        if (gameId == -1){
            gameId = getNextAvailableId(context);
        }
        File saveFile = getSaveFile(context, gameId);

        try {
            FileOutputStream fout = new FileOutputStream(saveFile, false);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(this);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Integer getNextAvailableId(Context context){
        File saveDirectory = context.getFilesDir();
        List<String> filenames = Arrays.asList(saveDirectory.list());

        Integer highestId = 0;
        for (String filename : filenames){
            try {
                Integer id = getIdFromFilename(filename);
                if (id > highestId) {
                    highestId = id;
                }
            } catch (Exception e){
                // non-game file, don't care
            }
        }

        return highestId + 1;
    }

    private static File getSaveFile(Context context, Integer id){
        File saveDirectory = context.getFilesDir();
        return new File(saveDirectory, "game" + id + ".gme");
    }

    public static Integer getIdFromFilename(String filename){
        return Integer.parseInt(filename.substring(4, filename.indexOf(".")));
    }


    // data manipulation

    public void addMoveRow(){
        List<List<Move>> moves = getMoves();

        for (List<Move> moveList : moves){
            moveList.add(new Move(0));
        }

        notifyPropertyChanged(BR.moves);
    }

    public void setPlayerCount(Integer newPlayerCount){
        Integer currentPlayerCount = getPlayerCount();
        Integer changeCount = Math.abs(newPlayerCount - currentPlayerCount);

        if (newPlayerCount > currentPlayerCount){
            for (int i = 0; i < changeCount; i++){
                addPlayer();
            }
        } else if (newPlayerCount < currentPlayerCount){
            for (int i = 0; i < changeCount; i++){
                // TODO: implement removePlayer
                // removePlayer();
            }
        }
    }

    private void addPlayer(){
        Integer newPlayerNumber = getPlayerCount() + 1;
        playerNames.add("Player " + newPlayerNumber);

        Integer numMoves = getMoveRowCount();

        List<Move> moveList = new ArrayList<>();
        for (int i = 0; i < numMoves; i++){
            moveList.add(new Move(0));
        }
        moves.add(moveList);

        notifyPropertyChanged(BR.playerNames);
        notifyPropertyChanged(BR.moves);
    }

    public int getPlayerCount(){
        return getPlayerNames().size();
    }

    public int getMoveRowCount(){
        List moves = getMoves();

        if (moves.size() == 0){ return 0; }
        else { return getMoves().get(0).size(); }
    }

    public int getTotalMoveCount(){
        return getPlayerCount() * getMoveRowCount();
    }


    // bindable getters / setters

    @Bindable
    public String getGameName(){
        return this.gameName;
    }
    public void setGameName(String gameName){
        this.gameName = gameName;
        notifyPropertyChanged(BR.gameName);
    }

    @Bindable
    public Date getGameDate(){ return this.gameDate; }

    @Bindable
    public Integer getGameId(){ return this.gameId; }

    @Bindable
    public ArrayList<String> getPlayerNames(){
        return this.playerNames;
    }
    public void setPlayerNames(ArrayList<String> playerNames){
        this.playerNames = playerNames;
        notifyPropertyChanged(BR.playerNames);
    }

    @Bindable
    public ArrayList<List<Move>> getMoves(){
        return this.moves;
    }
    public void setMoves(ArrayList<List<Move>> moves){
        this.moves = moves;
        notifyPropertyChanged(BR.moves);
    }

    @Bindable
    public boolean isWordMode(){
        return isWordMode;
    }
    public void setWordMode(boolean wordMode){
        isWordMode = wordMode;
        notifyPropertyChanged(BR.wordMode);
    }
}
