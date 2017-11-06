package com.liveasy.demo.controller;

import com.liveasy.demo.model.House;
import com.liveasy.demo.model.User;
import com.liveasy.demo.service.UserService;
import com.liveasy.demo.service.houseService.HouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
@Slf4j
public class IndexController {

    private UserService userService;
    private HouseService houseService;

    @Autowired
    public IndexController(UserService userService, HouseService houseService) {
        this.userService = userService;
        this.houseService = houseService;
    }

    @GetMapping("/list")
    public String getList(Model model){

        Set<User> test = userService.getAllUsers();
        Set<House> houses = houseService.getAllHouses();

        model.addAttribute("users" , test);
        model.addAttribute("houses", houses);
        log.debug("Currently at List Page");
        return "listview";
    }

    @GetMapping({"/"})
    public String getIndex(Model model){

        return "index";
    }


}
