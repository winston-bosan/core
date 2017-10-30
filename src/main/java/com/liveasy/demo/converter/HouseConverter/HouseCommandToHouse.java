package com.liveasy.demo.converter.HouseConverter;


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
        house.setLayout(source.getLayout());
        house.setLocation(source.getLocation());
        house.setComment(source.getComment());
        house.setImage(source.getImage());

        return house;

    }



}
