package com.liveasy.demo.converter.HouseConverter;

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
        houseCommand.setLayout(source.getLayout());
        houseCommand.setLocation(source.getLocation());
        houseCommand.setComment(source.getComment());
        houseCommand.setImage(source.getImage());


        return houseCommand;

    }

}