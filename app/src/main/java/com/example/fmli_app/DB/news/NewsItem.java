package com.example.fmli_app.DB.news;

import com.google.firebase.Timestamp;

public class NewsItem {
    static public final String key = "News";
    private long author;
    private String url, text, title;
    private Timestamp date;

    public NewsItem() {}

    public NewsItem(long author, String url, Timestamp date, String text, String title) {
        this.author = author;
        this.url = url;
        this.date = date;
        this.text = text;
        this.title = title;
    }

    public long getAuthor() {
        return author;
    }

    public void setAuthor(long author) {
        this.author = author;
    }

    public String getURL() {
        return url;
    }

    public void setURL(String URL) {
        this.url = url;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
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

    @Override
    public String toString() {
        return "NewsItem{" +
                "author=" + author +
                ", URL='" + url + '\'' +
                ", text='" + text + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
