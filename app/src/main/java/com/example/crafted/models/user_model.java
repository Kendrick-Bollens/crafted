package com.example.crafted.models;

import java.util.Date;
import java.util.List;

public class user_model {
    private int id;
    private String username;
    private String description;
    private String subject;
    private boolean verified;
    private Date userCreateDate;
    private Date userLastModifiedDate;
    private int profilePhotoId;
    private List<ticket_model> tickets;
    private List<project_model> projects;
    private List<tag_model> tags;
    private image_model profilePhoto;

    public user_model(int id, String username, String description, String subject, boolean verified, Date userCreateDate, Date userLastModifiedDate, int profilePhotoId, List<ticket_model> tickets, List<project_model> projects, List<tag_model> tags, image_model profilePhoto) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.subject = subject;
        this.verified = verified;
        this.userCreateDate = userCreateDate;
        this.userLastModifiedDate = userLastModifiedDate;
        this.profilePhotoId = profilePhotoId;
        this.tickets = tickets;
        this.projects = projects;
        this.tags = tags;
        this.profilePhoto = profilePhoto;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public String getSubject() {
        return subject;
    }

    public boolean isVerified() {
        return verified;
    }

    public Date getUserCreateDate() {
        return userCreateDate;
    }

    public Date getUserLastModifiedDate() {
        return userLastModifiedDate;
    }

    public int getProfilePhotoId() {
        return profilePhotoId;
    }

    public List<ticket_model> getTickets() {
        return tickets;
    }

    public List<project_model> getProjects() {
        return projects;
    }

    public List<tag_model> getTags() {
        return tags;
    }

    public image_model getProfilePhoto() {
        return profilePhoto;
    }
}
