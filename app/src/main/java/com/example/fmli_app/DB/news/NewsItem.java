package com.example.fmli_app.DB.news;

import com.example.fmli_app.DB.tags.Tag;
import com.example.fmli_app.DB.users.User;

public class NewsItem {
    private final long id;
    private Tag[] tags;
    private User author;
    private String URL, text, title;
    private final String date;

    public NewsItem(long id, User author, String URL, String date, String text, String title) {
        this.id = id;
        this.tags = new Tag[]{};
        this.author = author;
        this.URL = URL;
        this.date = date;
        this.text = text;
        this.title = title;
    }

    public NewsItem(User author, String URL, String date, String text, String title) {
        this(-1, author, URL, date, text, title);
    }

    public long getId() {
        return id;
    }

    public Tag[] getTags() {
        return tags;
    }

    public void setTags(Tag[] tags) {
        this.tags = tags;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
