package com.tetrinity.scoretracker.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tetrinity.scoretracker.BR;

public class OneObjectViewHolder extends RecyclerView.ViewHolder {

    private final ViewDataBinding binding;

    public OneObjectViewHolder(ViewDataBinding binding){
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Object obj, Integer position){
        binding.setVariable(BR.dataSource, obj);
        binding.setVariable(BR.position, position);

        binding.executePendingBindings();
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        binding.getRoot().setOnClickListener(onClickListener);
    }
}
