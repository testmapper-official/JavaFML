package com.example.fmli_app.DB.users;

public class User {
    private final long id;
    private String login, password, birthday, about_me, avatar_url, banner_url, email, number;
    private final String creation_date;
    private int permission;
    private Object[] likes;
    private Object[] posts;
    private User[] subscribers;
    private User[] subscribed;

    public User(long id, String login, String password, String email, String number, String about_me, String avatar_url,
                String banner_url, String creation_date, String birthday, int permission) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.number = number;
        this.about_me = about_me;
        this.creation_date = creation_date;
        this.avatar_url = avatar_url;
        this.banner_url = banner_url;
        this.birthday = birthday;
        this.permission = permission;
        this.likes = new Object[]{};
        this.posts = new Object[]{};
        this.subscribers = new User[]{};
        this.subscribed = new User[]{};
    }

    private User(String login, String password, String email, String number, String about_me, String avatar_url,
                 String banner_url, String creation_date, String birthday, int permission) {
        this(-1, login, password, email, number, about_me, avatar_url, banner_url, creation_date, birthday,
                permission);
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getCreation_date() {
        return creation_date;
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

    public Object[] getLikes() {
        return likes;
    }

    public void setLikes(Object[] likes) {
        this.likes = likes;
    }

    public Object[] getPosts() {
        return posts;
    }

    public void setPosts(Object[] posts) {
        this.posts = posts;
    }

    public User[] getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(User[] subscribers) {
        this.subscribers = subscribers;
    }

    public User[] getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(User[] subscribed) {
        this.subscribed = subscribed;
    }
}
