package com.performance.liferecord.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melnykov.fab.FloatingActionButton;
import com.performance.liferecord.R;
import com.performance.liferecord.activity.GirlActivity;
import com.performance.liferecord.adapter.GirlAdapter;
import com.performance.liferecord.gank.GankRetrofit;
import com.performance.liferecord.gank.GankService;
import com.performance.liferecord.model.GankData;
import com.performance.liferecord.utils.GirlItemClickEvent;

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
public class GirlFragment extends BaseFragment {
    private static String TAG = "GirlFragment";

    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButton;
    private SwipeRefreshLayout swipeRefreshLayout;

    private GankData mMeizi;
    private GirlAdapter mGirlAdapter;
    private List<GankData.ResultsBean> mMeiziList;

    private Retrofit retrofit;
    private CircularProgressBar mCircularProgressBar;


    public GirlFragment() {
    }

    public static GirlFragment newInstance(String dataType) {

        Bundle args = new Bundle();
        args.putString(TYPT, dataType);
        GirlFragment fragment = new GirlFragment();
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
        mMeiziList = new ArrayList();
        mGirlAdapter = new GirlAdapter(getActivity(), mMeiziList);

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
                        mMeiziList.addAll(model.getResults());
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


        //设置布局管理器,2表示两列，并且是竖直方向的瀑布流
        StaggeredGridLayoutManager mStaggeredGridLayoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);

        //设置adapter
        mRecyclerView.setAdapter(mGirlAdapter);
        mRecyclerView.setHasFixedSize(true);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mFloatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.fab_girl);
        mFloatingActionButton.attachToRecyclerView(mRecyclerView);

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
    public void onMessageEvent(GirlItemClickEvent event) {
        GankData.ResultsBean resultsBean = event.resultsBean;
        Intent intent = new Intent(getActivity(), GirlActivity.class);
        intent.putExtra(GankData.IMAGE_URL, resultsBean.getUrl());
        startActivity(intent);
    }

}
