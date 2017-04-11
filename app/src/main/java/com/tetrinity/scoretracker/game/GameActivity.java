package com.tetrinity.scoretracker.game;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.tetrinity.scoretracker.R;
import com.tetrinity.scoretracker.databinding.ActivityGameBinding;
import com.tetrinity.scoretracker.dialog.GameNameDialogFragment;
import com.tetrinity.scoretracker.dialog.PlayerCountDialogFragment;
import com.tetrinity.scoretracker.gamelist.GameListActivity;

public class GameActivity extends AppCompatActivity implements GameNameDialogFragment.OnGameNameSetListener, PlayerCountDialogFragment.OnPlayerCountSetListener {

    ActivityGameBinding binding;

    private Game game = null;

    private RecyclerView playerNameView;
    private GridLayoutManager playerLayoutManager = new GridLayoutManager(this, Game.DEFAULT_PLAYER_COUNT);

    private RecyclerView movesView;
    private GridLayoutManager moveLayoutManager = new GridLayoutManager(this, Game.DEFAULT_PLAYER_COUNT);

    private RecyclerView scoreTotalView;
    private GridLayoutManager scoreTotalLayoutManager = new GridLayoutManager(this, Game.DEFAULT_PLAYER_COUNT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initGameLayout();

        Intent intent = getIntent();
        Integer gameId = intent.getIntExtra(GameListActivity.GAME_ID, -1);

        game = Game.load(this, gameId);
        if (game == null){ game = new Game(this); }

        displayGameData(game);

        FloatingActionButton fab = binding.addMoveButton;
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                game.addMoveRow();
            }
        });
    }

    @Override
    protected void onStop(){
        super.onStop();

        game.save(this);
    }

    private void initGameLayout(){
        playerNameView = binding.playerList;
        playerNameView.setHasFixedSize(true);
        playerNameView.setLayoutManager(playerLayoutManager);

        movesView = binding.moveList;
        movesView.setHasFixedSize(false);
        movesView.setLayoutManager(moveLayoutManager);

        scoreTotalView = binding.scoreTotals;
        scoreTotalView.setHasFixedSize(false);
        scoreTotalView.setLayoutManager(scoreTotalLayoutManager);
    }

    private void displayGameData(Game game){
        binding.setDataSource(game);

        playerNameView.setAdapter(new PlayerNameAdapter(this, game));
        movesView.setAdapter(new MoveListAdapter(this, game));
        scoreTotalView.setAdapter(new PlayerScoreAdapter(this, game));

        setLayoutSpanCount(game.getPlayerCount());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        MenuItem turnWordModeOn = menu.findItem(R.id.action_turn_word_mode_on);
        MenuItem turnWordModeOff = menu.findItem(R.id.action_turn_word_mode_off);

        turnWordModeOn.setVisible(!game.isWordMode());
        turnWordModeOff.setVisible(game.isWordMode());

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_game_name) {
            GameNameDialogFragment gndf = GameNameDialogFragment.newInstance(game);
            gndf.show(getSupportFragmentManager(), "game_name");

        } else if (id == R.id.action_turn_word_mode_on || id == R.id.action_turn_word_mode_off) {
            game.setWordMode(!game.isWordMode());
            invalidateOptionsMenu();

        } else if (id == R.id.action_player_count){
            PlayerCountDialogFragment gndf = PlayerCountDialogFragment.newInstance(game);
            gndf.show(getSupportFragmentManager(), "player_count");

        } else if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @BindingAdapter("android:layout_marginBottom")
    public static void setMoveListBottomMargin(View view, float bottomMargin){
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams)view.getLayoutParams();

        layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.rightMargin, (int)bottomMargin);
        view.setLayoutParams(layoutParams);
    }

    @Override
    public void onGameNameSet(String newGameName){
        game.setGameName(newGameName);
    }

    @Override
    public void onPlayerCountSet(Integer playerCount) {
        game.setPlayerCount(playerCount);
        setLayoutSpanCount(playerCount);
    }

    private void setLayoutSpanCount(Integer spanCount) {
        playerLayoutManager.setSpanCount(spanCount);
        moveLayoutManager.setSpanCount(spanCount);
        scoreTotalLayoutManager.setSpanCount(spanCount);
    }
}
