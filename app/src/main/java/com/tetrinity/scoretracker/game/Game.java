package com.tetrinity.scoretracker.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class Game implements Serializable {

    private LinkedHashMap<String, List<Move>> moves = new LinkedHashMap<>();

    public Game(){

    }

    public void addPlayer(String playerName){
        if (moves.keySet().contains(playerName)){ return; }

        moves.put(playerName, new ArrayList<Move>());
    }


    public List getPlayers(){
        return new ArrayList<String>(moves.keySet());
    }

}
