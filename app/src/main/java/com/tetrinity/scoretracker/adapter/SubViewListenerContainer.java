package com.tetrinity.scoretracker.adapter;

import android.view.View;

public class SubViewListenerContainer {
    private final Integer viewId;
    private final View.OnClickListener onClickListener;
    private final View.OnFocusChangeListener onFocusChangeListener;

    public SubViewListenerContainer(Integer viewId, View.OnClickListener onClickListener, View.OnFocusChangeListener onFocusChangeListener) {
        this.viewId = viewId;
        this.onClickListener = onClickListener;
        this.onFocusChangeListener = onFocusChangeListener;
    }

    public Integer getViewId() {
        return viewId;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public View.OnFocusChangeListener getOnFocusChangeListener() {
        return onFocusChangeListener;
    }
}
