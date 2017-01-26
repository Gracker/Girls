package com.performance.liferecord.model;

import java.util.List;

/**
 * Created by Gracker on 2016/6/10.
 */

public class GankData {

    public static final String IMAGE_URL = "imageUrl";
    public static final String POST_URL = "postUrl";

    /**
     * error : false results : [{"_id":"5757975b421aa90eca080dd5","createdAt":"2016-06-08T11:56:11.8Z","desc":"6.8","publishedAt":"2016-06-08T12:39:36.270Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1f4nog8tjfrj20eg0mgab7.jpg","used":true,"who":"daimajia"}]
     */

    private boolean error;
    /**
     * _id : 5757975b421aa90eca080dd5
     * createdAt : 2016-06-08T11:56:11.8Z
     * desc : 6.8
     * publishedAt : 2016-06-08T12:39:36.270Z
     * source : chrome
     * type : 福利
     * url : http://ww4.sinaimg.cn/large/610dc034jw1f4nog8tjfrj20eg0mgab7.jpg
     * used : true
     * who : daimajia
     */

    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private String[] images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public String[] getImages() {
            return images;
        }

        public void setImages(String[] images) {
            this.images = images;
        }
    }
}
