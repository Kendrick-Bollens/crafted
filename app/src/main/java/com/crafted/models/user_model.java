package com.crafted.models;

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

    public user_model(int id, String username, String description, String subject, boolean verified, Date userCreateDate, Date userLastModifiedDate, int profilePhotoId) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.subject = subject;
        this.verified = verified;
        this.userCreateDate = userCreateDate;
        this.userLastModifiedDate = userLastModifiedDate;
        this.profilePhotoId = profilePhotoId;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public void setUserCreateDate(Date userCreateDate) {
        this.userCreateDate = userCreateDate;
    }

    public void setUserLastModifiedDate(Date userLastModifiedDate) {
        this.userLastModifiedDate = userLastModifiedDate;
    }

    public void setProfilePhotoId(int profilePhotoId) {
        this.profilePhotoId = profilePhotoId;
    }
}
