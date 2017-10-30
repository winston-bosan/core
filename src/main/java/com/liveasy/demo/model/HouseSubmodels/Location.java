package com.liveasy.demo.model.HouseSubmodels;

import com.liveasy.demo.model.House;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private House house;

    private int streetNumber;
    private String street;
    private String locality;
    private String city;
    private String province;
    private String country;
    private String postalCode;

    private String formattedAddress;

    //Specifically for map generation and location pin-pointing
    //todo add additional types of latitude and longitude
    private double lat;
    private double lng;



    public String getFullLocation(){
        return streetNumber + " " + street + ", " + city;
    }
    public String getAddress() {return streetNumber + " " + street + ", " + city;}
}
