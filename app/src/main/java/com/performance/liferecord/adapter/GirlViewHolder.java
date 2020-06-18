package com.performance.liferecord.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.performance.liferecord.R;

/**
 * Created by Gracker on 2016/6/10.
 */

class GirlViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;

    public GirlViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.activity_recycke_item_type);

    }
}