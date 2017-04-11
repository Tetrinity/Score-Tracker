package com.tetrinity.scoretracker.game;

import android.content.Context;
import android.widget.TextView;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MoveUnitTest {

    @Mock
    Context mockContext;

    @Mock
    TextView textView;

    @Test
    public void test_defaultWordConstructor(){
        Move move = new Move(10);

        Assert.assertEquals("Expected score to be the passed value", 10, move.score);
        Assert.assertEquals("Expected default move to be the empty string", "", move.word);
    }

    @Test
    public void test_setWordConstructor(){
        Move move = new Move(10, "cake");

        Assert.assertEquals("Expected score to be the passed value", 10, move.score);
        Assert.assertEquals("Expected move to be the passed value", "cake", move.word);
    }

    @Test
    public void test_getText_Integer(){
        Mockito.when(textView.getText()).thenReturn("14");
        Assert.assertEquals("Expected integer 14", 14, Move.getText(textView));
    }

    @Test
    public void test_getText_EmptyString(){
        Mockito.when(textView.getText()).thenReturn("");
        Assert.assertEquals("Expected integer 0", 0, Move.getText(textView));
    }

    @Test
    public void test_getText_Negative(){
        Mockito.when(textView.getText()).thenReturn("-7");
        Assert.assertEquals("Expected integer -7", -7, Move.getText(textView));
    }

    @Test
        public void test_getText_NegativeSignOnly(){
        Mockito.when(textView.getText()).thenReturn("-");
        Assert.assertEquals("Expected integer 0", 0, Move.getText(textView));
    }

}
