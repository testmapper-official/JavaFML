package com.example.fmli_app.DB.tags;

public class Tag {
    private final long id;
    private String name;

    public Tag(long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Tag(String name) {
        this(-1, name);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
