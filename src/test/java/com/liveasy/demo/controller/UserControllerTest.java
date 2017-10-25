package com.liveasy.demo.controller;

import com.liveasy.demo.exception.NotFoundException;
import com.liveasy.demo.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class UserControllerTest {

    @Mock
    UserService userService;
    UserController userController;
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userController = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }


    @Test
    public void getUserPage() throws Exception {
    }

    @Test
    public void newUser() throws Exception {
    }

    @Test
    public void saveOrUpdate() throws Exception {
    }

    @Test
    public void saveOrUpdate1() throws Exception {
    }

    @Test
    public void deleteById() throws Exception {
    }

    @Test
    public void handleNotFoundTest() throws Exception {
        when(userService.findById(anyLong())).thenThrow(NotFoundException.class);

        //verify
        mockMvc.perform(get("/user/1/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
    }

}