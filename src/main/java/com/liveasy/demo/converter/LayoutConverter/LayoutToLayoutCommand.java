package com.liveasy.demo.converter.LayoutConverter;

import com.liveasy.demo.command.HouseSubcommands.LayoutCommand;
import com.liveasy.demo.model.HouseSubmodels.Layout;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class LayoutToLayoutCommand implements Converter<Layout,LayoutCommand> {

    @Synchronized
    @Nullable
    @Override
    public LayoutCommand convert(Layout source) {
        //todo think of a good a way to do checknull logic
        //if(source.getId() == null){return null;}

        final LayoutCommand layoutCommand = new LayoutCommand();

        layoutCommand.setArea(source.getArea());
        layoutCommand.setBedrooms(source.getBedrooms());
        layoutCommand.setHouse(source.getHouse());
        layoutCommand.setId(source.getId());
        layoutCommand.setWashrooms(source.getWashrooms());
        layoutCommand.setYearBuilt(source.getYearBuilt());

        return layoutCommand;

    }

}
