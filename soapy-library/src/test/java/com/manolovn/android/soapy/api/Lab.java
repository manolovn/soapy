package com.manolovn.android.soapy.api;

/**
 * Lab entity
 */
public class Lab {

    private int id;
    private String title;

    public Lab() {
        // empty constructor
    }

    public Lab(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
