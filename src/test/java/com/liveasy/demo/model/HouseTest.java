package com.liveasy.demo.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HouseTest {

    private House house;

    @Before
    public void setUp(){
        house = new House();
    }

    @Test
    public void getId() throws Exception {
        Long idValue = 4L;
        house.setId(idValue);
        assertEquals(idValue,house.getId());
    }

    @Test
    public void getAddress() throws Exception {
        String addressValue = "593 Here Street";
        house.setAddress(addressValue);
        assertEquals(addressValue,house.getAddress());
    }

}