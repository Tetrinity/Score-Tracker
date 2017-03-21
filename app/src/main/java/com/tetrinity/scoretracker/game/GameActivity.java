package com.tetrinity.scoretracker.game;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.tetrinity.scoretracker.R;
import com.tetrinity.scoretracker.databinding.ActivityGameBinding;
import com.tetrinity.scoretracker.gamelist.GameListActivity;

public class GameActivity extends AppCompatActivity {

    ActivityGameBinding binding;

    private Game game = null;

    private RecyclerView playerNames;
    private StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(Game.DEFAULT_PLAYER_COUNT, StaggeredGridLayoutManager.VERTICAL);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        playerNames = binding.playerList;
        playerNames.setHasFixedSize(true);
        playerNames.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        Integer gameId = intent.getIntExtra(GameListActivity.GAME_ID, -1);

        game = Game.load(this, gameId);
        if (game == null){ game = new Game(this); }

        initGame(game);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                game.setGameName("Test Game Name");
//            }
//        });
    }

    @Override
    protected void onStop(){
        super.onStop();

        game.save(this);
    }

    private void initGame(Game game){
        binding.setDataSource(game);

        playerNames.setAdapter(new PlayerNameAdapter(this, game));
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
