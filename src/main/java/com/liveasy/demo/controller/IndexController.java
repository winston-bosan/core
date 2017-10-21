package com.liveasy.demo.controller;

import com.liveasy.demo.model.User;
import com.liveasy.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class IndexController {

    private UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/","/main"})
    public String getIndex(Model model){

        List<User> test = userService.getAllUsers();
        model.addAttribute("users" , test);

        return "index";
    }

}
