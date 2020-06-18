package com.performance.liferecord.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.performance.liferecord.R;
import com.performance.liferecord.model.GankData;

import java.util.List;

/**
 * Created by Gracker on 2016/6/10.
 */

public class GirlAdapter extends RecyclerView.Adapter<GirlViewHolder> implements View.OnClickListener {
    ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance
    private Context mContext;
    private List<GankData.Data> mGirlDataList;

    public GirlAdapter(Context context, List<GankData.Data> postData) {
        mContext = context;
        mGirlDataList = postData;
    }

    @Override
    public GirlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GirlViewHolder holder = new GirlViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.girl_list_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(GirlViewHolder holder, int position) {
        Log.v("Gracker", "getUrl = " + mGirlDataList.get(position).getUrl());
//        Picasso.with(mContext).load(mGirlDataList.get(position).getUrl()).into(holder.imageView);
        imageLoader.displayImage(mGirlDataList.get(position).getUrl().replace("http", "https"), holder.imageView);
        holder.imageView.setOnClickListener(this);
        holder.imageView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mGirlDataList.size();
    }

    @Override
    public void onClick(View v) {
//        EventBus.getDefault().post(new GirlItemClickEvent(mGirlDataList.get((int) v.getTag())));
    }
}