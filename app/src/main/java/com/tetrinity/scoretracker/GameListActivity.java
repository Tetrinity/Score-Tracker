package com.tetrinity.scoretracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.tetrinity.scoretracker.game.Game;
import com.tetrinity.scoretracker.game.GameActivity;

import java.io.File;
import java.util.Arrays;
import java.util.List;

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
    protected void onResume(){
        super.onResume();

        loadGameList();
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

    public void addGame(View view){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public static final String GAME_ID = "com.tetrinity.GAME_ID";
    public void editGame(Integer gameId){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(GAME_ID, gameId);

        startActivity(intent);
    }

    private void loadGameList(){
        LinearLayout list = (LinearLayout)findViewById(R.id.game_list);
        list.removeAllViews();

        TextView textViewHead = new TextView(this);
        textViewHead.setLayoutParams(new ListView.LayoutParams(
                ListView.LayoutParams.WRAP_CONTENT,
                ListView.LayoutParams.WRAP_CONTENT
        ));
        textViewHead.setText("Game List");
        list.addView(textViewHead);

        File saveDirectory = getFilesDir();
        List<File> files = Arrays.asList(saveDirectory.listFiles());

        for (final File file : files){
            TextView textView = new TextView(this);
            textView.setLayoutParams(new ListView.LayoutParams(
                    ListView.LayoutParams.WRAP_CONTENT,
                    ListView.LayoutParams.WRAP_CONTENT
            ));
            textView.setTextSize(24);
            textView.setText(file.getName());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editGame(Game.getIdFromFilename(file.getName()));
                }
            });
            list.addView(textView);
        }
    }
}
