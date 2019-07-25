package com.core.entites;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User extends BasicEntity {

    @NotNull
    @Column(name = "USERNAME", nullable = false)
    private String username;

    @NotNull
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @Column(name = "TYPE", nullable = false)
    private String type;

    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @Column(name = "MOBILE", nullable = false)
    private String mobile;

    @NotNull
    @Column(name = "LANGUAGE", nullable = false)
    private String language;

    @Lob
    @Column(name = "AVATAR", length = 4000)
    private String avatar;

    @Column(name = "COMMERCIAL_REGISTER")
    private String commercialRegister;

    @Column(name = "ACTIVE", length = 1)
    private Boolean active;

    @OneToOne(cascade = {CascadeType.ALL})
    @NotNull
    @JoinColumn(name = "ADDRESS_ID", nullable = false)
    private Address address;

    @ManyToMany
    private Set<Role> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCommercialRegister() {
        return commercialRegister;
    }

    public void setCommercialRegister(String commercialRegister) {
        this.commercialRegister = commercialRegister;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
