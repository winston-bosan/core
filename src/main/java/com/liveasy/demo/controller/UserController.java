package com.liveasy.demo.controller;

import com.liveasy.demo.command.UserCommand;
import com.liveasy.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "user/new")
    public String newUser(Model model){

        model.addAttribute("user" , new UserCommand());

        return "user/userform";
    }


    @GetMapping("/user/{id}/update")
    public String saveOrUpdate(@PathVariable String id, Model model){
        model.addAttribute("user",userService.findCommandById(Long.valueOf(id)));
        return"user/userform";
    }

    @PostMapping
    @RequestMapping("user")
    public String saveOrUpdate(@ModelAttribute UserCommand command){
        UserCommand saveCommand = userService.saveUserCommand(command);
        return"redirect:/user/show/" + saveCommand.getId();
    }

}
