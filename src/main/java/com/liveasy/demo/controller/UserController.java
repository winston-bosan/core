package com.liveasy.demo.controller;

import com.liveasy.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/show/{id}")
    public String getUserPage(@PathVariable String id, Model model){

        model.addAttribute("user", userService.findById(new Long(id)));

        return "user/show";

    }

}
