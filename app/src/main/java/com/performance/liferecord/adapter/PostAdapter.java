package com.performance.liferecord.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.performance.liferecord.R;
import com.performance.liferecord.model.GankData;
import com.performance.liferecord.utils.PostItemClickEvent;
import com.performance.liferecord.utils.TimeUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Gracker on 2016/6/10.
 */

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<GankData.ResultsBean> mPostDataList;

    public PostAdapter(Context context, List<GankData.ResultsBean> postData) {
        mContext = context;
        mPostDataList = postData;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PostViewHolder holder = new PostViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.android_list_item, parent,
                false));
        holder.title.getPaint().setFakeBoldText(true);//加粗
        return holder;
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.title.setText(mPostDataList.get(position).getDesc());
        holder.des.setText(mPostDataList.get(position).getType());
        holder.date.setText(TimeUtils.getFormatTime(mPostDataList.get(position).getPublishedAt()));
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        if (null == mPostDataList) {
            return 0;
        }
        return mPostDataList.size();
    }

    @Override
    public void onClick(View v) {
        EventBus.getDefault().post(new PostItemClickEvent(mPostDataList.get((int) v.getTag())));
    }
}