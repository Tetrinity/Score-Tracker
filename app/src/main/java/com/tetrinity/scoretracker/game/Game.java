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
import java.util.LinkedHashMap;
import java.util.List;

public class Game extends BaseObservable implements Serializable {

    private Integer gameId = -1;

    private String gameName;

    private LinkedHashMap<String, List<Move>> moves = new LinkedHashMap<>();

    private boolean isWordMode = false;

    public Game(){
        initData();
    }

    public Game(Context context){
        initData();
        save(context);
    }

    private void initData(){
        addPlayer("Player 1");
        addPlayer("Player 2");

        addMove("Player 1", new Move(14));
        addMove("Player 2", new Move(17, "testing"));
    }

    @Bindable
    public String getGameName(){
        return this.gameName;
    }
    public void setGameName(String gameName){
        this.gameName = gameName;
        notifyPropertyChanged(BR.gameName);
    }

    public static Game load(Context context, int gameId){
        File saveFile = getSaveFile(context, gameId);
        if (!saveFile.exists()){ return null; }

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

    public void addPlayer(String playerName){
        if (moves.keySet().contains(playerName)){ return; }

        moves.put(playerName, new ArrayList<Move>());
    }
    public void removePlayer(String playerName){
        moves.remove(playerName);
    }

    public void addMove(String playerName, Move move){
        List<Move> playerMoves = getPlayerMoves(playerName);
        playerMoves.add(move);
    }

    public List<String> getPlayers(){
        return new ArrayList<String>(moves.keySet());
    }

    public List<Move> getPlayerMoves(String playerName){
        return moves.get(playerName);
    }

    public boolean isWordMode(){
        return isWordMode;
    }

    public void setWordMode(boolean wordMode){
        isWordMode = wordMode;
    }


    private static File getSaveFile(Context context, Integer id){
        File saveDirectory = context.getFilesDir();
        return new File(saveDirectory, "game" + id + ".gme");
    }
    public static Integer getIdFromFilename(String filename){
        return Integer.parseInt(filename.substring(4, filename.indexOf(".")));
    }

}
