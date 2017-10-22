package com.liveasy.demo.controller;

import com.liveasy.demo.command.UserCommand;
import com.liveasy.demo.service.HouseService;
import com.liveasy.demo.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class HouseControllerTest {

    @Mock
    UserService userService;
    @Mock
    HouseService houseService;
    HouseController houseController;
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        houseController = new HouseController(userService, houseService);
        mockMvc = MockMvcBuilders.standaloneSetup(houseController).build();
    }

    @Test
    public void getHousesTest() throws Exception {
        //given
        UserCommand userCommand = new UserCommand();
        when(userService.findCommandById( anyLong() )).thenReturn(userCommand);

        //when
        mockMvc.perform( get("/user/1/houses") )
                .andExpect(status().isOk())
                .andExpect(view().name("user/house/list"))
                .andExpect(model().attributeExists("user"))
        ;

        //verify
        verify(userService,times(1)).findCommandById(anyLong());
    }

    @Test
    public void showHousesTest() throws Exception {
        //given
        UserCommand userCommand = new UserCommand();
        when(userService.findCommandById( anyLong() )).thenReturn(userCommand);

        //when
        mockMvc.perform( get("/user/1/houses") )
                .andExpect(status().isOk())
                .andExpect(view().name("user/house/list"))
                .andExpect(model().attributeExists("user"))
        ;

        //verify
        verify(userService,times(1)).findCommandById(anyLong());
    }


}