package com.liveasy.demo.model;

import javax.persistence.*;

@Entity
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String address;
    private String postal;
    private String city;


    private int bedrooms;
    private int washrooms;
    private int area;
    private int yearBuilt;

    public House(){}

    public House(String address, int bedrooms, int washrooms, int area, int yearBuilt) {
        this.address = address;
        this.bedrooms = bedrooms;
        this.washrooms = washrooms;
        this.area = area;
        this.yearBuilt = yearBuilt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getWashrooms() {
        return washrooms;
    }

    public void setWashrooms(int washrooms) {
        this.washrooms = washrooms;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(int yearBuilt) {
        this.yearBuilt = yearBuilt;
    }
}
