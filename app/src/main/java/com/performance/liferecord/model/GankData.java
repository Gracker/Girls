package com.performance.liferecord.model;

/**
 * Created by Gracker on 2016/6/10.
 */

public class GankData {
    public static final String IMAGE_URL = "imageUrl";
    public static final String POST_URL = "postUrl";

    private Data[] data;
    private String page;
    private String page_count;
    private String status;
    private String total_counts;

    public Data[] getData() {
        return data;
    }

    public void setData(Data[] data) {
        this.data = data;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPage_count() {
        return page_count;
    }

    public void setPage_count(String page_count) {
        this.page_count = page_count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotal_counts() {
        return total_counts;
    }

    public void setTotal_counts(String total_counts) {
        this.total_counts = total_counts;
    }

    public static class Data {
        private String _id;
        private String author;
        private String category;
        private String createdAt;
        private String desc;
        private String[] images;
        private String likeCounts;
        private String publishedAt;
        private String stars;
        private String title;
        private String type;
        private String url;
        private String views;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
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

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public String getViews() {
            return views;
        }

        public void setViews(String views) {
            this.views = views;
        }

        public String[] getImages() {
            return images;
        }

        public void setImages(String[] images) {
            this.images = images;
        }

        public String getLikeCounts() {
            return likeCounts;
        }

        public void setLikeCounts(String likeCounts) {
            this.likeCounts = likeCounts;
        }
    }
}
