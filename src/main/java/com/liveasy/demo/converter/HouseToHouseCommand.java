package com.liveasy.demo.converter;

import com.liveasy.demo.command.HouseCommand;
import com.liveasy.demo.model.House;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class HouseToHouseCommand implements Converter<House,HouseCommand> {

    @Synchronized
    @Nullable
    @Override
    public HouseCommand convert(House source) {
        //todo think of a good a way to do checknull logic
        //if(source.getId() == null){return null;}

        final HouseCommand houseCommand = new HouseCommand();
        houseCommand.setId(source.getId());
        houseCommand.setUser(source.getUser());
        houseCommand.setAddress(source.getAddress());
        houseCommand.setArea(source.getArea());
        houseCommand.setBedrooms(source.getBedrooms());
        houseCommand.setWashrooms(source.getWashrooms());
        houseCommand.setYearBuilt(source.getYearBuilt());
        houseCommand.setCity(source.getCity());
        houseCommand.setPostal(source.getPostal());
        houseCommand.setImage(source.getImage());

        return houseCommand;

    }

    @Synchronized
    @Nullable
    public HouseCommand convertNoId(House source) {
        //todo think of a good a way to do checknull logic
        //if(source.getId() == null){return null;}

        final HouseCommand houseCommand = new HouseCommand();

        houseCommand.setUser(source.getUser());
        houseCommand.setAddress(source.getAddress());
        houseCommand.setArea(source.getArea());
        houseCommand.setBedrooms(source.getBedrooms());
        houseCommand.setWashrooms(source.getWashrooms());
        houseCommand.setYearBuilt(source.getYearBuilt());
        houseCommand.setCity(source.getCity());
        houseCommand.setPostal(source.getPostal());
        houseCommand.setImage(source.getImage());

        return houseCommand;

    }

}