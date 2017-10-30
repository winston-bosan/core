package com.liveasy.demo.converter.LayoutConverter;

import com.liveasy.demo.command.HouseSubcommands.LayoutCommand;
import com.liveasy.demo.model.HouseSubmodels.Layout;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class LayoutCommandToLayout implements Converter<LayoutCommand,Layout> {

    @Synchronized
    @Nullable
    @Override
    public Layout convert(LayoutCommand source) {
        //todo think of a good a way to do checknull logic
        //if(source.getId() == null){return null;}

        final Layout layout = new Layout();

        layout.setArea(source.getArea());
        layout.setBedrooms(source.getBedrooms());
        layout.setHouse(source.getHouse());
        layout.setId(source.getId());
        layout.setWashrooms(source.getWashrooms());
        layout.setYearBuilt(source.getYearBuilt());

        return layout;

    }

}
