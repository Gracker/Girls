package com.performance.liferecord.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.performance.liferecord.R;

/**
 * Created by Gracker on 2016/6/10.
 */

class GirlViewHolder extends RecyclerView.ViewHolder {

    ImageView type;

    public GirlViewHolder(View itemView) {
        super(itemView);
        type = (ImageView) itemView.findViewById(R.id.activity_recycke_item_type);

    }
}