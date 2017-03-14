package com.tetrinity.scoretracker.gamelist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tetrinity.scoretracker.R;
import com.tetrinity.scoretracker.game.Game;
import com.tetrinity.scoretracker.game.GameActivity;

import java.util.ArrayList;

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Game> dataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView gameName;
        public ViewHolder(TextView v){
            super(v);
            gameName = v;
        }
    }

    public GameListAdapter(Context context, ArrayList<Game> games){
        this.context = context;
        dataset = games;
    }

    @Override
    public GameListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list_item, parent, false);

        v.setTextSize(24);

        return new ViewHolder(v);
    }

    public static final String GAME_ID = "com.tetrinity.GAME_ID";
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        final Game game = dataset.get(position);

        holder.gameName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(context, GameActivity.class);
                intent.putExtra(GAME_ID, game.getGameId());

                context.startActivity(intent);
            }
        });

        holder.gameName.setText(game.getGameName());
    }

    @Override
    public int getItemCount(){
        return dataset.size();
    }
}
