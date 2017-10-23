package com.liveasy.demo.converter;


import com.liveasy.demo.command.HouseCommand;
import com.liveasy.demo.model.House;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class HouseCommandToHouse implements Converter<HouseCommand,House>{

    @Synchronized
    @Nullable
    @Override
    public House convert(HouseCommand source) {
        //todo think of a good a way to do checknull logic
        //if(source.getId() == null){return null;}

        final House house = new House();
        house.setId(source.getId());
        house.setUser(source.getUser());
        house.setAddress(source.getAddress());
        house.setArea(source.getArea());
        house.setBedrooms(source.getBedrooms());
        house.setWashrooms(source.getWashrooms());
        house.setYearBuilt(source.getYearBuilt());
        house.setCity(source.getCity());
        house.setPostal(source.getPostal());
        house.setImage(source.getImage());

        return house;

    }

    @Synchronized
    @Nullable
    public House convertNoId(HouseCommand source) {
        //todo think of a good a way to do checknull logic
        //if(source.getId() == null){return null;}

        final House house = new House();

        house.setUser(source.getUser());
        house.setAddress(source.getAddress());
        house.setArea(source.getArea());
        house.setBedrooms(source.getBedrooms());
        house.setWashrooms(source.getWashrooms());
        house.setYearBuilt(source.getYearBuilt());
        house.setCity(source.getCity());
        house.setPostal(source.getPostal());
        house.setImage(source.getImage());

        return house;

    }


}
