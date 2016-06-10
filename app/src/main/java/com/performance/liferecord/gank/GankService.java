package com.performance.liferecord.gank;

import com.performance.liferecord.model.GankData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Gracker on 2016/6/10.
 */

public interface GankService {

    @GET("api/data/{type}/{count}/{page}")
    Call<GankData> listPost(
            @Path("type") String type,
            @Path("count") int count,
            @Path("page") int page
    );
}
