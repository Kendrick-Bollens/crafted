package com.crafted.models;

import java.util.List;

public class user_profile_model {
    private user_model user;
    private List<ticket_info_model> tickets;
    private List<ticket_info_model> projects;
    private List<tag_model> tags;
    private image_model profilePhoto;

    public user_profile_model(user_model user, List<ticket_info_model> tickets, List<ticket_info_model> projects, List<tag_model> tags, image_model profilePhoto) {
        this.user = user;
        this.tickets = tickets;
        this.projects = projects;
        this.tags = tags;
        this.profilePhoto = profilePhoto;
    }

    public user_model getUser() {
        return user;
    }

    public List<ticket_info_model> getTickets() {
        return tickets;
    }

    public List<ticket_info_model> getProjects() {
        return projects;
    }

    public List<tag_model> getTags() {
        return tags;
    }

    public image_model getProfilePhoto() {
        return profilePhoto;
    }

    public void setUser(user_model user) {
        this.user = user;
    }

    public void setTickets(List<ticket_info_model> tickets) {
        this.tickets = tickets;
    }

    public void setProjects(List<ticket_info_model> projects) {
        this.projects = projects;
    }

    public void setTags(List<tag_model> tags) {
        this.tags = tags;
    }

    public void setProfilePhoto(image_model profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}
