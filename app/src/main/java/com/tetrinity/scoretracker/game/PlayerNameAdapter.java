package com.tetrinity.scoretracker.game;

import android.content.Context;
import android.view.View;

import com.tetrinity.scoretracker.R;
import com.tetrinity.scoretracker.adapter.SingleLayoutAdapter;

import java.util.List;

public class PlayerNameAdapter extends SingleLayoutAdapter {

    private Game data;

    public PlayerNameAdapter(Context context, Game game){
        super(context, R.layout.player_name);
        data = game;
    }

    @Override
    protected Object getObjForPosition(int position) {
        List<String> players = data.getPlayers();
        return players.get(position);
    }

    @Override
    protected View.OnClickListener getClickListenerForPosition(int position) {
        return null;
    }

    @Override
    public int getItemCount() {
        return data.getPlayers().size();
    }
}
