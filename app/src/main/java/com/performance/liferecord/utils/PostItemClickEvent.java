package com.performance.liferecord.utils;

import com.performance.liferecord.model.GankData;

/**
 * Created by Gracker on 2016/6/10.
 */

public class PostItemClickEvent {
    public GankData resultsBean;

    public PostItemClickEvent(GankData resultsBean) {
        this.resultsBean = resultsBean;
    }
}
