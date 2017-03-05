package com.tetrinity.scoretracker.game;

/**
 * Created by Tetrinity on 02-Mar-17.
 */

public class Move {
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
