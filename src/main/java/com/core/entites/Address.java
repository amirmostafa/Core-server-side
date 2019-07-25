package com.core.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Address extends BasicEntity {

    @Column(name = "GOVERNORATE")
    private String governorate;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STREET")
    private String street;

    public String getGovernorate() {
        return governorate;
    }

    public void setGovernorate(String governorate) {
        this.governorate = governorate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
