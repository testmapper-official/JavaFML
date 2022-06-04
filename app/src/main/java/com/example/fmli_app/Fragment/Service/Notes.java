package com.example.fmli_app.Fragment.Service;

public class Notes {
    private long id;
    private String head;
    private String body;

    public Notes(long id, String head, String body) {
        this.id = id;
        this.head = head;
        this.body = body;
    }

    public Notes(String head, String body) {
        this(-1, head, body);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public String getHead() {
        return head;
    }

    public String getBody() {
        return body;
    }
}
