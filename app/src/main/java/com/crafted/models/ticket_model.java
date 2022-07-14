package com.crafted.models;

import java.util.Date;

public class ticket_model
{
    private int id;
    private String title;
    private String description;
    private String status;
    private Date createdDate;
    private int userId;
    private int assignedTo;

    public ticket_model(int id, String title, String description, String status, Date createdDate, int userId, int assignedTo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
        this.userId = userId;
        this.assignedTo = assignedTo;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public int getUserId() {
        return userId;
    }

    public int getAssignedTo() {
        return assignedTo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAssignedTo(int assignedTo) {
        this.assignedTo = assignedTo;
    }
}
