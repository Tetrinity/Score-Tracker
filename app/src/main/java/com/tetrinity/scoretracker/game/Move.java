package com.tetrinity.scoretracker.game;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.widget.TextView;

import java.io.Serializable;

public class Move implements Serializable {
    public int score;
    public String word;

    public Move(Integer score){
        this(score, "");
    }

    public Move(Integer score, String word){
        this.score = score;
        this.word = word;
    }

    @BindingAdapter("android:text")
    public static void setText(TextView view, int value){
        view.setText(Integer.toString(value));
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static int getText(TextView view){
        String viewText = view.getText().toString();

        if (viewText.equals("") || viewText.equals("-")){
            return 0;
        } else {
            return Integer.parseInt(viewText);
        }
    }
}
