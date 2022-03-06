package com.example.fmli_app;


public class NotificationItem {
    private final long id;
    private long userId;
    private int type;
    private String text;
    private final String date;

    public NotificationItem(long id, long userId, int type, String text, String date) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.text = text;
        this.date = date;
    }

    public NotificationItem(long userId, int type, String text, String date) {
        this( -1, userId, type, text, date);
    }

    public int getDrawableResource() {
        if (this.type == 1) {
            return R.drawable.notify_system;
        } else if (this.type == 2) {
            return R.drawable.notify_like;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "NotificationItem{" +
                "id=" + id +
                ", userId=" + userId +
                ", type=" + type +
                ", text='" + text + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }
}
