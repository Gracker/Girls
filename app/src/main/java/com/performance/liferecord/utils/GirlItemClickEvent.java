package com.performance.liferecord.utils;

import com.performance.liferecord.model.GankData;

/**
 * Created by Gracker on 2016/6/10.
 */

public class GirlItemClickEvent {
    public GankData.ResultsBean resultsBean;

    public GirlItemClickEvent(GankData.ResultsBean resultsBean) {
        this.resultsBean = resultsBean;
    }
}
