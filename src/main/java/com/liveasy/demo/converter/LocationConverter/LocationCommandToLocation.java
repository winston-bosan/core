package com.liveasy.demo.converter.LocationConverter;

import com.liveasy.demo.command.HouseSubcommands.LocationCommand;
import com.liveasy.demo.model.HouseSubmodels.Location;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class LocationCommandToLocation implements Converter<LocationCommand,Location> {

    @Synchronized
    @Nullable
    @Override
    public Location convert(LocationCommand source) {
        //todo think of a good a way to do checknull logic
        //if(source.getId() == null){return null;}

        final Location location = new Location();

        location.setStreetNumber(source.getStreetNumber());
        location.setStreet(source.getStreet());
        location.setCity(source.getCity());
        location.setHouse(source.getHouse());
        location.setCountry(source.getCountry());
        location.setFormattedAddress(source.getFormattedAddress());
        location.setId(source.getId());
        location.setLat(source.getLat());
        location.setLng(source.getLng());
        location.setLocality(source.getLocality());
        location.setPostalCode(source.getPostalCode());
        location.setProvince(source.getProvince());

        return location;

    }

}
