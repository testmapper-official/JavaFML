package com.example.fmli_app.DB.news;

public class NewsItem {
    static public final String key = "News";
    private String id;
    private String uid, url, text, title;
    private String date;

    public NewsItem() {
    }

    public NewsItem(String id, String uid, String url, String date, String text, String title) {
        this.id = id;
        this.uid = uid;
        this.url = url;
        this.date = date;
        this.text = text;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
