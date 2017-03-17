package com.tetrinity.scoretracker.adapter;

import android.content.Context;

public abstract class SingleLayoutAdapter extends BaseOneObjectAdapter {
    // https://medium.com/google-developers/android-data-binding-recyclerview-db7c40d9f0e4#.u7hkwzo96
    private final int layoutId;

    public SingleLayoutAdapter(int layoutId){
        this.layoutId = layoutId;
    }

    public SingleLayoutAdapter(Context context, int layoutId){
        super(context);
        this.layoutId = layoutId;
    }

    @Override
    protected int getLayoutIdForPosition(int position){
        return layoutId;
    }
}
