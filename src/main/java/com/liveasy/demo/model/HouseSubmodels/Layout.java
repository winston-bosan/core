package com.liveasy.demo.model.HouseSubmodels;

import com.liveasy.demo.model.House;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Layout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private House house;

    private int bedrooms;
    private int washrooms;
    private int area;
    private int yearBuilt;

    public Layout(int bedrooms, int washrooms, int area, int yearBuilt) {
        this.bedrooms = bedrooms;
        this.washrooms = washrooms;
        this.area = area;
        this.yearBuilt = yearBuilt;
    }
}
