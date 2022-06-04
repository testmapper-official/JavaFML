package com.example.fmli_app.Fragment.Home;

public class Comment {
    public static String key = "Comments";
    private String id, uid, nid, text;

    public Comment() {
    }

    public Comment(String id, String uid, String nid, String text) {
        this.id = id;
        this.uid = uid;
        this.nid = nid;
        this.text = text;
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

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
