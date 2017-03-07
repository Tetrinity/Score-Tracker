package com.tetrinity.scoretracker.game;

import org.junit.Test;
import org.junit.Assert;

import java.util.List;

public class GameUnitTest {

    @Test
    public void test_addPlayer(){
        Game game = new Game();

        game.addPlayer("Test Player");
        game.addPlayer("Other Player");

        List gamePlayers = game.getPlayers();

        Assert.assertEquals("Expected first player to be \"Player 1\"", "Player 1", gamePlayers.get(0));
        Assert.assertEquals("Expected second player to be \"Player 2\"", "Player 2", gamePlayers.get(1));
        Assert.assertEquals("Expected third player to be \"Test Player\"", "Test Player", gamePlayers.get(2));
        Assert.assertEquals("Expected fourth player to be \"Other Player\"", "Other Player", gamePlayers.get(3));
    }

    @Test
    public void test_removePlayer(){
        Game game = new Game();

        game.addPlayer("Test Player");
        game.addPlayer("Other Player");

        game.removePlayer("Test Player");
        game.removePlayer("Not Included");

        List gamePlayers = game.getPlayers();

        Assert.assertEquals("Expected three players remaining", 3, gamePlayers.size());
        Assert.assertEquals("Expected first player to be \"Player 1\"", "Player 1", gamePlayers.get(0));
        Assert.assertEquals("Expected second player to be \"Player 2\"", "Player 2", gamePlayers.get(1));
        Assert.assertEquals("Expected third player to be \"Other Player\"", "Other Player", gamePlayers.get(2));
    }

    @Test
    public void test_addMove(){
        Game game = new Game();
        String playerName = "Test Player";

        game.addPlayer(playerName);
        game.addMove(playerName, new Move(18));
        game.addMove(playerName, new Move(14, "Java"));

        List<Move> moves = game.getPlayerMoves(playerName);

        Assert.assertEquals("Expected to have two moves", 2, moves.size());
        Assert.assertEquals("Expected first move to have a score of 18", 18, moves.get(0).score);
        Assert.assertEquals("Expected first move to have no associated word", null, moves.get(0).word);
        Assert.assertEquals("Expected second move to have a score of 14", 14, moves.get(1).score);
        Assert.assertEquals("Expected second move to have the word \"Java\"", "Java", moves.get(1).word);
    }
}
