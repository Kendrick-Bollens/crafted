package com.crafted.models;

import java.util.List;

public class ticket_info_model {
    private ticket_model ticket;
    private user_model user;
    private List<tag_model> tags;
    private List<image_model> imageModelList;

    public ticket_info_model(ticket_model ticket, user_model user, List<tag_model> tags, List<image_model> imageModelList) {
        this.ticket = ticket;
        this.user = user;
        this.tags = tags;
        this.imageModelList = imageModelList;
    }

    public ticket_model getTicket() {
        return ticket;
    }

    public user_model getUser() {
        return user;
    }

    public List<tag_model> getTags() {
        return tags;
    }

    public List<image_model> getImageModelList() {
        return imageModelList;
    }

    public void setTicket(ticket_model ticket) {
        this.ticket = ticket;
    }

    public void setUser(user_model user) {
        this.user = user;
    }

    public void setTags(List<tag_model> tags) {
        this.tags = tags;
    }

    public void setImageModelList(List<image_model> imageModelList) {
        this.imageModelList = imageModelList;
    }
}
