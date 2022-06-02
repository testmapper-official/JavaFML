package com.example.fmli_app.DB.users;

import com.google.firebase.Timestamp;

public class User {
    private String password, birthday, about_me, avatar_url, banner_url, email, number;
    private Timestamp date;
    private int permission;

    public User() {}

    public User(String password, String email, String number, String about_me, String avatar_url,
                String banner_url, Timestamp date, String birthday, int permission) {
        this.password = password;
        this.email = email;
        this.number = number;
        this.about_me = about_me;
        this.date = date;
        this.avatar_url = avatar_url;
        this.banner_url = banner_url;
        this.birthday = birthday;
        this.permission = permission;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout_me() {
        return about_me;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getDate() {
        return date;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getBanner_url() {
        return banner_url;
    }

    public void setBanner_url(String banner_url) {
        this.banner_url = banner_url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
}
