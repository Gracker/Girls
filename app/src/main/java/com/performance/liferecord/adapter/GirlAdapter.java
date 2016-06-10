package com.performance.liferecord.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.performance.liferecord.R;
import com.performance.liferecord.model.GankData;
import com.performance.liferecord.utils.GirlItemClickEvent;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Gracker on 2016/6/10.
 */

public class GirlAdapter extends RecyclerView.Adapter<GirlViewHolder> implements View.OnClickListener {
    private Context mContext;
    private List<GankData.ResultsBean> mGirlDataList;

    public GirlAdapter(Context context, List<GankData.ResultsBean> postData) {
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
        Picasso.with(mContext).load(mGirlDataList.get(position).getUrl()).into(holder.type);
        holder.type.setOnClickListener(this);
        holder.type.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mGirlDataList.size();
    }

    @Override
    public void onClick(View v) {
        EventBus.getDefault().post(new GirlItemClickEvent(mGirlDataList.get((int) v.getTag())));
    }
}