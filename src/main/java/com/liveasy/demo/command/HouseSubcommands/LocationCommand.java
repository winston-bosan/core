package com.liveasy.demo.command.HouseSubcommands;

import com.liveasy.demo.model.House;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LocationCommand {

    private Long id;
    private House house;
    private int streetNumber;
    private String street;
    private String locality;
    private String city;
    private String province;
    private String country;
    private String postalCode;
    private String formattedAddress;
    private double lat;
    private double lng;


    @Override
    public String toString() {
        return "LocationCommand{" +
                "id=" + id +
                ", house=" + house +
                ", streetNumber=" + streetNumber +
                ", street='" + street + '\'' +
                ", locality='" + locality + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", formattedAddress='" + formattedAddress + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
