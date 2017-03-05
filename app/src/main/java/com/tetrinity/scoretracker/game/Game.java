package com.tetrinity.scoretracker.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Game implements Serializable {

    private LinkedHashMap<String, List<Move>> moves = new LinkedHashMap<>();

    private boolean isWordMode = false;

    public Game(){

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

}
