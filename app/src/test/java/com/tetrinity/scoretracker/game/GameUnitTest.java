package com.tetrinity.scoretracker.game;

import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameUnitTest {

    @Test
    public void test_initGame(){
        Game game = new Game();

        Assert.assertEquals("Expected to have two players by default", 2, game.getPlayerCount());
        Assert.assertEquals("Expected each player to have one move", 1, game.getMoveRowCount());
    }

    @Test
    public void test_addMoveRow(){
        Game game = new Game();

        ArrayList<String> players = new ArrayList<String>();
        players.add("Player 1"); players.add("Player 2"); players.add("Player 3");
        game.setPlayerNames(players);

        ArrayList<List<Move>> moves = new ArrayList<>();
        moves.add(createMoveList(new Move(1), new Move(2)));
        moves.add(createMoveList(new Move(3), new Move(4)));
        moves.add(createMoveList(new Move(5), new Move(6)));
        game.setMoves(moves);

        Assert.assertEquals("Expected setup to have 2 move rows", 2, game.getMoveRowCount());
        Assert.assertEquals("Expected setup to have 3 players", 3, game.getPlayerCount());
        Assert.assertEquals("Expected setup to have a total of 6 moves", 6, game.getTotalMoveCount());

        game.addMoveRow();

        Assert.assertEquals("Expected to have 3 move rows after adding a move row", 3, game.getMoveRowCount());
        Assert.assertEquals("Expected to have 3 players after adding a move row", 3, game.getPlayerCount());
        Assert.assertEquals("Expected to have a total of 9 moves after adding a move row", 9, game.getTotalMoveCount());

        List<List<Move>> actualMoves = game.getMoves();
        for (List<Move> playerMoves : actualMoves){
            Move lastMove = playerMoves.get(playerMoves.size()-1);
            Assert.assertEquals("Expected newly created move to default to 0", 0, lastMove.score);
            Assert.assertEquals("Expected newly created move to have no word", "", lastMove.word);
        }
    }

    @Test
    public void test_setPlayerCount_Increase(){
        Game game = new Game();

        ArrayList<String> players = new ArrayList<String>();
        players.add("Player 1"); players.add("Player 2");
        game.setPlayerNames(players);

        ArrayList<List<Move>> moves = new ArrayList<>();
        moves.add(createMoveList(new Move(1), new Move(2)));
        moves.add(createMoveList(new Move(3), new Move(4)));
        game.setMoves(moves);

        Assert.assertEquals("Expected setup to have 2 move rows", 2, game.getMoveRowCount());
        Assert.assertEquals("Expected setup to have 2 players", 2, game.getPlayerCount());
        Assert.assertEquals("Expected setup to have a total of 4 moves", 4, game.getTotalMoveCount());

        game.setPlayerCount(3);

        Assert.assertEquals("Expected to have 2 move rows after increasing player count", 2, game.getMoveRowCount());
        Assert.assertEquals("Expected to have 3 players after increasing player count", 3, game.getPlayerCount());
        Assert.assertEquals("Expected to have a total of 6 moves after increasing player count", 6, game.getTotalMoveCount());

    }

    private List<Move> createMoveList(Move... rawMoveArray){
        ArrayList<Move> moves = new ArrayList<>();
        Collections.addAll(moves, rawMoveArray);
        return moves;
    }
}
