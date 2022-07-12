package com.example.crafted.models;

public class image_model {
    private int id;
    private String url;
    private String altText;

    public image_model(int id, String url, String altText) {
        this.id = id;
        this.url = url;
        this.altText = altText;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getAltText() {
        return altText;
    }
}
