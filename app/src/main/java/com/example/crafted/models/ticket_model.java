package com.example.crafted.models;

import java.util.Date;
import java.util.List;

public class ticket_model
{
    private int id;
    private String title;
    private String description;
    private String status;
    private Date createdDate;
    private int userId;
    private int assignedTo;
    private List<image_model> imageModelList;

    public ticket_model(int id, String title, String description, String status, Date createdDate, int userId, int assignedTo, List<image_model> imageModelList) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
        this.userId = userId;
        this.assignedTo = assignedTo;
        this.imageModelList = imageModelList;
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

    public List<image_model> getImageList() {
        return imageModelList;
    }
}
