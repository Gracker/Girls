package com.performance.liferecord.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.performance.liferecord.R;
import com.performance.liferecord.activity.PostDetailActivity;
import com.performance.liferecord.adapter.PostAdapter;
import com.performance.liferecord.gank.GankRetrofit;
import com.performance.liferecord.gank.GankService;
import com.performance.liferecord.model.GankData;
import com.performance.liferecord.utils.PostItemClickEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Gracker on 2016/3/22.
 */
public class PostFragment extends BaseFragment {
    private static String TAG = "GirlFragment";

    private RecyclerView mRecyclerView;
    private GankData mMeizi;
    private PostAdapter mGirlAdapter;
    private List<GankData.ResultsBean> mPostList;

    private Retrofit retrofit;
    private CircularProgressBar mCircularProgressBar;
    private SwipeRefreshLayout swipeRefreshLayout;


    public PostFragment() {
    }

    public static PostFragment newInstance(String dataType) {

        Bundle args = new Bundle();
        args.putString(TYPT, dataType);
        PostFragment fragment = new PostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (null == savedInstanceState) {
            savedInstanceState = getArguments();
            dataType = savedInstanceState.getString(TYPT);
        }

        mMeizi = new GankData();
        mPostList = new ArrayList();
        mGirlAdapter = new PostAdapter(getActivity(), mPostList);

        // get data
        retrofit = GankRetrofit.getRetrofit();
        getData();
    }

    private void getData() {
        GankService gankService = retrofit.create(GankService.class);
        Call<GankData> call = gankService.listPost(dataType, 100, 1);
        call.enqueue(new Callback<GankData>() {
            @Override
            public void onResponse(Call<GankData> call, Response<GankData> response) {
                GankData model = response.body();
                if (!model.isError()) {
                    for (GankData.ResultsBean url : model.getResults()) {
                        Log.d(TAG, "onResponse: " + url);
                        mPostList.addAll(model.getResults());
                    }
                }
                mGirlAdapter.notifyDataSetChanged();
                mCircularProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<GankData> call, Throwable t) {
                Log.v(TAG, t.toString());
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_fragment, null);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.activity_recycke_view);

        //设置adapter
        mRecyclerView.setAdapter(mGirlAdapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(false);

        //设置布局管理器
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mCircularProgressBar = (CircularProgressBar) rootView.findViewById(R.id.circular_progressbar);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(
                R.color.holo_red_light,
                R.color.holo_green_light,
                R.color.holo_blue_bright);

        //swipeRefreshLayout 设置下拉刷新事件
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                //成功了 关闭刷新
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    // This method will be called when a MessageEvent is posted
    @Subscribe
    public void onMessageEvent(PostItemClickEvent event) {
        GankData.ResultsBean resultsBean = event.resultsBean;
        Intent intent = new Intent(getActivity(), PostDetailActivity.class);
        intent.putExtra(GankData.POST_URL, resultsBean.getUrl());
        startActivity(intent);
    }
}
