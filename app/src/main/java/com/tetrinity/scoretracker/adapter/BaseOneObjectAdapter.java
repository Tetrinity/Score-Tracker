package com.tetrinity.scoretracker.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public abstract class BaseOneObjectAdapter extends RecyclerView.Adapter<OneObjectViewHolder> {

    protected Context context;

    public BaseOneObjectAdapter(){}

    public BaseOneObjectAdapter(Context context){
        this.context = context;
    }

    public OneObjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);

        return new OneObjectViewHolder(binding);
    }

    public void onBindViewHolder(OneObjectViewHolder holder, int position){
        Object obj = getObjForPosition(position);
        holder.bind(obj, position);

        holder.setOnClickListener(getClickListenerForPosition(position));

        ArrayList<SubViewListenerContainer> subViewListeners = getSubViewListeners();
        if (subViewListeners != null){
            for (SubViewListenerContainer svcl : subViewListeners){
                holder.setSubViewListeners(svcl);
            }
        }
    }

    @Override
    public int getItemViewType(int position){
        return getLayoutIdForPosition(position);
    }

    protected abstract Object getObjForPosition(int position);
    protected abstract View.OnClickListener getClickListenerForPosition(int position);
    protected abstract ArrayList<SubViewListenerContainer> getSubViewListeners();

    protected abstract int getLayoutIdForPosition(int position);
}
