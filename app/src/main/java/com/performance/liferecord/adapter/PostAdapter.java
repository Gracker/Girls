package com.performance.liferecord.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.performance.liferecord.R;
import com.performance.liferecord.model.GankData;
import com.performance.liferecord.utils.TimeUtils;

import java.util.List;

/**
 * Created by Gracker on 2016/6/10.
 */

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<GankData.Data> mPostDataList;

    public PostAdapter(Context context, List<GankData.Data> postData) {
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

        if (null != mPostDataList.get(position).getImages()
                && mPostDataList.get(position).getImages().length >= 0) {
            holder.image.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().displayImage(mPostDataList.get(position).getImages()[0].replace("http", "https"), holder.image); // Get singleton instance
        } else {
            holder.image.setVisibility(View.GONE);
        }
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
//        EventBus.getDefault().post(new PostItemClickEvent(mPostDataList.get((int) v.getTag())));
    }
}