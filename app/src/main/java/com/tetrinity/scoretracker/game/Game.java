package com.tetrinity.scoretracker.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game implements Serializable {

    private HashMap<String, List<Move>> moves = new HashMap<>();

    public Game(){

    }

    public void addPlayer(String playerName){
        if (moves.keySet().contains(playerName)){ return; }

        moves.put(playerName, new ArrayList<Move>());
    }

}
