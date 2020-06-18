package com.performance.liferecord.gank;

import com.performance.liferecord.model.GankData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Gracker on 2016/6/10.
 */

public interface GankService {
    //https://gank.io/api/v2/data/category/Girl/type/Girl/page/1/count/10
    //https://gank.io/api/v2/data/category/<category>/type/<type>/page/<page>/count/<count>
    //    category 可接受参数 All(所有分类) | Article | GanHuo | Girl
    //    type 可接受参数 All(全部类型) | Android | iOS | Flutter | Girl ...，即分类API返回的类型数据
    //    count: [10, 50]
    //    page: >=1
    @GET("api/v2/data/category/{category}/type/{type}/page/{page}/count/{count}")
    Call<GankData> listPost(
            @Path("category") String category,
            @Path("type") String type,
            @Path("page") int page,
            @Path("count") int count
    );
}
