package com.liveasy.demo.command;

import com.liveasy.demo.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HouseCommand {

    private Long id;
    private User user;

    private String address;
    private String postal;
    private String city;


    private int bedrooms;
    private int washrooms;
    private int area;
    private int yearBuilt;

}
