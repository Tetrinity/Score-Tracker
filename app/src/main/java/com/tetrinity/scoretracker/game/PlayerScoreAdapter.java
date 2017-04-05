package com.tetrinity.scoretracker.game;

import android.content.Context;
import android.view.View;

import com.tetrinity.scoretracker.R;
import com.tetrinity.scoretracker.adapter.SingleLayoutAdapter;
import com.tetrinity.scoretracker.adapter.SubViewListenerContainer;

import java.util.ArrayList;

public class PlayerScoreAdapter extends SingleLayoutAdapter {

    private Game data;

    public PlayerScoreAdapter(Context context, Game game){
        super(context, R.layout.player_score);
        data = game;
    }

    @Override
    protected Object getObjForPosition(int position) {
        return data;
    }

    @Override
    protected View.OnClickListener getClickListenerForPosition(int position) {
        return null;
    }

    @Override
    protected ArrayList<SubViewListenerContainer> getSubViewListeners() {
        return null;
    }

    @Override
    public int getItemCount() {
        return data.getPlayerCount();
    }
}
