package com.crafted.models;

import java.util.List;

public class ticket_post_model {
    private String title;
    private String  description;
    private List<tag_model> tags;
    private List<image_model> images;

    public ticket_post_model(String title, String description, List<tag_model> tags, List<image_model> images) {
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<tag_model> getTags() {
        return tags;
    }

    public void setTags(List<tag_model> tags) {
        this.tags = tags;
    }

    public List<image_model> getImages() {
        return images;
    }

    public void setImages(List<image_model> images) {
        this.images = images;
    }
}
