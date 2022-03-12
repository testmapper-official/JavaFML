package com.example.fmli_app.DB.news;

import com.example.fmli_app.DB.tags.Tag;
import com.example.fmli_app.DB.users.User;

public class NewsItem {
    private final long id;
    private Tag[] tags;
    private User[] authors;
    private String url, text;
    private final String date;

    public NewsItem(long id, Tag[] tags, String url, String date, String text) {
        this.id = id;
        this.tags = tags;
        this.authors = new User[]{};
        this.url = url;
        this.date = date;
        this.text = text;
    }

    public NewsItem(Tag[] tags, String url, String date, String text) {
        this(-1, tags, url, date, text);
    }

    public long getId() {
        return id;
    }

    public Tag[] getTags_id() {
        return tags;
    }

    public void setTags_id(Tag[] tags) {
        this.tags = tags;
    }

    public User[] getAuthors_id() {
        return authors;
    }

    public void setAuthors_id(User[] authors) {
        this.authors = authors;
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
