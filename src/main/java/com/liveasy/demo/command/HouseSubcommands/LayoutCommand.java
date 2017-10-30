package com.liveasy.demo.command.HouseSubcommands;

import com.liveasy.demo.model.House;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LayoutCommand {

    private Long id;
    private House house;
    private int bedrooms;
    private int washrooms;
    private int area;
    private int yearBuilt;

    @Override
    public String toString() {
        return "LayoutCommand{" +
                "id=" + id +
                ", house=" + house +
                ", bedrooms=" + bedrooms +
                ", washrooms=" + washrooms +
                ", area=" + area +
                ", yearBuilt=" + yearBuilt +
                '}';
    }
}
