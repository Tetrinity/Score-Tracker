package com.tetrinity.scoretracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tetrinity.scoretracker.game.Game;
import com.tetrinity.scoretracker.game.GameActivity;

public class GameListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        // set up app main toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static final String GAME_KEY = "com.tetrinity.GAME_KEY";
    public void addGame(View view){
        Game game = new Game();

        // DEBUG
        game.addPlayer("Test Player");
        game.addPlayer("Other Player");
        game.addPlayer("Third Player");

        // send game to score view
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(GAME_KEY, game);

        startActivity(intent);
    }

    public void editGame(View view){
        // get game and send to score view
//        throw new NotImplementedException();
    }
}
