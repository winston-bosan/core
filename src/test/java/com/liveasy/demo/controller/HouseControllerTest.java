package com.liveasy.demo.controller;

import com.liveasy.demo.command.UserCommand;
import com.liveasy.demo.controllerAdvice.ControllerExceptionHandler;
import com.liveasy.demo.converter.HouseConverter.HouseToHouseCommand;
import com.liveasy.demo.service.UserService;
import com.liveasy.demo.service.houseService.HouseService;
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
    private UserService userService;
    @Mock
    private HouseService houseService;
    @Mock
    private HouseToHouseCommand houseToHouseCommand;
    private HouseController houseController;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        houseController = new HouseController(userService, houseService, houseToHouseCommand);
        mockMvc = MockMvcBuilders.standaloneSetup(houseController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
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