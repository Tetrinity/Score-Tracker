package com.tetrinity.scoretracker.game;

import org.junit.Test;
import org.junit.Assert;

import java.util.List;

public class GameUnitTest {

    @Test
    public void test_addPlayer(){
        Game game = new Game();

        game.addPlayer("Test Player");
        game.addPlayer("Second Player");

        List gamePlayers = game.getPlayers();

        Assert.assertEquals("Expected first player to be \"Test Player\"", "Test Player", gamePlayers.get(0));
        Assert.assertEquals("Expected second player to be \"Second Player\"", "Second Player", gamePlayers.get(1));
    }

    @Test
    public void test_removePlayer(){
        Game game = new Game();

        game.addPlayer("Test Player");
        game.addPlayer("Second Player");

        game.removePlayer("Test Player");
        game.removePlayer("Not Included");

        List gamePlayers = game.getPlayers();

        Assert.assertEquals("Expected one player remaining", 1, gamePlayers.size());
        Assert.assertEquals("Expected player to be \"Second Player\"", "Second Player", gamePlayers.get(0));
    }
}
