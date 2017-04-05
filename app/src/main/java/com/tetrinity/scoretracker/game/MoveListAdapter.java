package com.tetrinity.scoretracker.game;

import android.content.Context;
import android.view.View;

import com.tetrinity.scoretracker.R;
import com.tetrinity.scoretracker.adapter.SingleLayoutAdapter;
import com.tetrinity.scoretracker.adapter.SubViewListenerContainer;

import java.util.ArrayList;

public class MoveListAdapter extends SingleLayoutAdapter {

    private Game data;
    private ArrayList<SubViewListenerContainer> svclList = new ArrayList<>();

    public MoveListAdapter(Context context, Game game){
        super(context, R.layout.move_list_item);
        initSubViewListeners();

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
        return svclList;
    }

    public void initSubViewListeners(){
        svclList.add(new SubViewListenerContainer(R.id.move_score, null, new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                data.updateMoveScoreTotal();
            }
        }));
    }

    @Override
    public int getItemCount() {
        return data.getTotalMoveCount();
    }
}
