package com.tetrinity.scoretracker.game;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TableRow;

import com.tetrinity.scoretracker.GameListActivity;
import com.tetrinity.scoretracker.R;
import com.tetrinity.scoretracker.databinding.ActivityGameBinding;

public class GameActivity extends AppCompatActivity {

    ActivityGameBinding binding;

    private Game game = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Integer gameId = intent.getIntExtra(GameListActivity.GAME_ID, -1);

        game = Game.load(this, gameId);
        if (game == null){ game = new Game(this); }

        initGame(game);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.setGameName("Test Game Name");
            }
        });
    }

    @Override
    protected void onStop(){
        super.onStop();

        game.save(this);
    }

    private void initGame(Game game){
        binding.setGame(game);

        TableRow header = (TableRow)findViewById(R.id.score_table_header);

        for (String player : game.getPlayers()){
            EditText editText = new EditText(this);
            editText.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
            ));
            editText.setGravity(Gravity.CENTER);

            editText.append(player);
            header.addView(editText);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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
}
