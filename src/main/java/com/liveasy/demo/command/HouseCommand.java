package com.liveasy.demo.command;

import com.liveasy.demo.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class HouseCommand {

    private Long id;
    private User user;

    @NotBlank
    private String address;
    private String postal;
    private String city;
    private String locality;


    private double lat;
    private double lng;

    @Min(0)
    @Max(100)
    private int bedrooms;

    @Min(0)
    @Max(100)
    private int washrooms;

    @Min(100)
    private int area;

    @Min(1800)
    @Max(2018)
    private int yearBuilt;


    private Byte[] image;

    @Override
    public String toString() {
        return "HouseCommand{" +
                "id=" + id +
                ", user=" + user +
                ", address='" + address + '\'' +
                ", postal='" + postal + '\'' +
                ", city='" + city + '\'' +
                ", bedrooms=" + bedrooms +
                ", washrooms=" + washrooms +
                ", area=" + area +
                ", yearBuilt=" + yearBuilt +
                '}';
    }
}
