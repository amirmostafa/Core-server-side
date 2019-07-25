package com.core.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class RequestModel extends BasicModel {
    private String date;
    private LocalTime time;
    private BigDecimal totalAmount;
    private String deliveryAddress;
    private String name;
    private String description;
    private String status;
    private UserModel userModel;
    private String userName;
    private UserModel assignedTo;
    private String assignedtoName;
    private UserModel assignedBy;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public UserModel getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(UserModel assignedTo) {
        this.assignedTo = assignedTo;
    }

    public UserModel getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(UserModel assignedBy) {
        this.assignedBy = assignedBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignedtoName() {
        return assignedtoName;
    }

    public void setAssignedtoName(String assignedtoName) {
        this.assignedtoName = assignedtoName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
