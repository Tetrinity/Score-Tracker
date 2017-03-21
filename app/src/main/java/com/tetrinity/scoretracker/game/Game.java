package com.tetrinity.scoretracker.game;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

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

    private ObservableArrayList<String> playerNames = new ObservableArrayList<>();
    private ObservableArrayList<List<Move>> moves = new ObservableArrayList<>();

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
        for (int i = 1; i <= DEFAULT_PLAYER_COUNT; i++){
            String playerName = "Player " + i;

            addPlayer(playerName);
            addMove(playerName, new Move(0));
        }
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

    public void addPlayer(String playerName){
        playerNames.add(playerName);
        moves.add(new ArrayList<Move>());
    }
    public void removePlayer(String playerName){
        int index = playerNames.indexOf(playerName);

        playerNames.remove(index);
        moves.remove(index);
    }

    public void addMove(String playerName, Move move){
        List<Move> playerMoves = getPlayerMoves(playerName);
        playerMoves.add(move);
    }

    public List<String> getPlayers(){
        return playerNames;
    }

    public List<Move> getPlayerMoves(String playerName){
        int index = playerNames.indexOf(playerName);
        return moves.get(index);
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

    public String getPlayerName(int position){
        return playerNames.get(position);
    }
    public void setPlayerName(int position, String newName){
        playerNames.set(position, newName);
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
