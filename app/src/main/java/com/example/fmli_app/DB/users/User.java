package com.example.fmli_app.DB.users;

public class User {
    static public final String key = "Users";
    private String id, nickname, about_me, avatar_url;
    private int permission;

    public User() {
    }

    public User(String id, String nickname, String about_me, String avatar_url, int permission) {
        this.id = id;
        this.nickname = nickname;
        this.about_me = about_me;
        this.avatar_url = avatar_url;
        this.permission = permission;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAbout_me() {
        return about_me;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
}
