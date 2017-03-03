package com.tetrinity.scoretracker.game;

import org.junit.Test;

/**
 * Created by Tetrinity on 02-Mar-17.
 */

public class GameUnitTest {

    @Test
    public void test_addPlayer() throws Exception {
        Game game = new Game();

        game.addPlayer("Test Player");

        assert true;
    }
}
