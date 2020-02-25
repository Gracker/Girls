package com.performance.liferecord.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.performance.liferecord.R;

/**
 * Created by Gracker on 2016/6/10.
 */

class PostViewHolder extends RecyclerView.ViewHolder {

    TextView title;
    TextView des;
    TextView date;
    ImageView image;

    public PostViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.post_title);
        des = (TextView) itemView.findViewById(R.id.post_desc);
        date = (TextView) itemView.findViewById(R.id.post_date);
        image = (ImageView) itemView.findViewById(R.id.post_images);
    }
}