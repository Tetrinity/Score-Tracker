package com.tetrinity.scoretracker.game;

import java.io.Serializable;

public class Move implements Serializable {
    public int score;
    public String word;

    public Move(Integer score){
        this(score, null);
    }

    public Move(Integer score, String word){
        this.score = score;
        this.word = word;
    }
}
