package com.liveasy.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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

    private double lat;
    private double lng;

    private int bedrooms;

    private int washrooms;

    private int area;
    private int yearBuilt;

    @Lob
    private Byte[] image;

    public House() {
    }

    public House(String address, int bedrooms, int washrooms, int area, int yearBuilt) {
        this.address = address;
        this.bedrooms = bedrooms;
        this.washrooms = washrooms;
        this.area = area;
        this.yearBuilt = yearBuilt;
    }

    public String getFullAddress(){
        return this.address + ", " + this.city;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", user=" + user +
                ", address='" + address + '\'' +
                ", postal='" + postal + '\'' +
                ", city='" + city + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", bedrooms=" + bedrooms +
                ", washrooms=" + washrooms +
                ", area=" + area +
                ", yearBuilt=" + yearBuilt +
                '}';
    }
}
