package com.tetrinity.scoretracker.game;

import android.content.Context;
import android.view.View;

import com.tetrinity.scoretracker.R;
import com.tetrinity.scoretracker.adapter.SingleLayoutAdapter;

public class MoveListAdapter extends SingleLayoutAdapter {

    private Game data;

    public MoveListAdapter(Context context, Game game){
        super(context, R.layout.move_list_item);
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
    public int getItemCount() {
        return data.countTotalMoves();
    }
}
