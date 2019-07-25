package com.core.models;

public class StoreModel {
    private int id;
    private String storeName;
    private String commercialRegister;
    private boolean active;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCommercialRegister() {
        return commercialRegister;
    }

    public void setCommercialRegister(String commercialRegister) {
        this.commercialRegister = commercialRegister;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
