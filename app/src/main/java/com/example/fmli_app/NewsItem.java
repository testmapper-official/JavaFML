package com.example.fmli_app;

public class NewsItem {
    private final long id;
    private long[] tags_id, authors_id;
    private String url, text;
    private final String date;

    public NewsItem(long id, long[] tags_id, String url, String date, String text) {
        this.id = id;
        this.tags_id = tags_id;
        this.authors_id = new long[]{};
        this.url = url;
        this.date = date;
        this.text = text;
    }

    public NewsItem(long[] tags_id, String url, String date, String text) {
        this(-1, tags_id, url, date, text);
    }

    public long getId() {
        return id;
    }

    public long[] getTags_id() {
        return tags_id;
    }

    public void setTags_id(long[] tags_id) {
        this.tags_id = tags_id;
    }

    public long[] getAuthors_id() {
        return authors_id;
    }

    public void setAuthors_id(long[] authors_id) {
        this.authors_id = authors_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
