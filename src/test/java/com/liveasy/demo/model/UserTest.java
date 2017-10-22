package com.liveasy.demo.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    private User user;

    @Before
    public void setUp(){
        user = new User();
    }

    @Test
    public void getId() throws Exception {
        Long idValue = 4L;
        user.setId(idValue);
        assertEquals(idValue,user.getId());
    }


}