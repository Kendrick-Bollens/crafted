package com.crafted.models;

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

    public void setId(int id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }
}
