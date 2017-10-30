package com.liveasy.demo.converter.LocationConverter;

import com.liveasy.demo.command.HouseSubcommands.LocationCommand;
import com.liveasy.demo.model.HouseSubmodels.Location;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class LocationToLocationCommand implements Converter<Location,LocationCommand> {

    @Synchronized
    @Nullable
    @Override
    public LocationCommand convert(Location source) {
        //todo think of a good a way to do checknull logic
        //if(source.getId() == null){return null;}

        final LocationCommand locationCommand = new LocationCommand();

        locationCommand.setStreetNumber(source.getStreetNumber());
        locationCommand.setStreet(source.getStreet());
        locationCommand.setCity(source.getCity());
        locationCommand.setHouse(source.getHouse());
        locationCommand.setCountry(source.getCountry());
        locationCommand.setFormattedAddress(source.getFormattedAddress());
        locationCommand.setId(source.getId());
        locationCommand.setLat(source.getLat());
        locationCommand.setLng(source.getLng());
        locationCommand.setLocality(source.getLocality());
        locationCommand.setPostalCode(source.getPostalCode());
        locationCommand.setProvince(source.getProvince());
        
        return locationCommand;

    }

}
