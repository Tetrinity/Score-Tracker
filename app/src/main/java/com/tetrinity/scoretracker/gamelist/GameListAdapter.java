package com.tetrinity.scoretracker.gamelist;

import android.content.Context;
import android.view.View;

import com.tetrinity.scoretracker.R;
import com.tetrinity.scoretracker.adapter.SingleLayoutAdapter;
import com.tetrinity.scoretracker.game.Game;

import java.util.ArrayList;

public class GameListAdapter extends SingleLayoutAdapter {

    private ArrayList<Game> dataset;
    private ArrayList<View.OnClickListener> clickListeners;

    public GameListAdapter(Context context, ArrayList<Game> games, ArrayList<View.OnClickListener> onClickListeners){
        super(context, R.layout.game_list_item);
        dataset = games;
        clickListeners = onClickListeners;
    }

    @Override
    protected Object getObjForPosition(int position) {
        return dataset.get(position);
    }

    @Override
    protected View.OnClickListener getClickListenerForPosition(int position){
        return clickListeners.get(position);
    }

    @Override
    public int getItemCount(){
        return dataset.size();
    }
}
