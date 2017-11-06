package com.liveasy.demo.controller;

import com.liveasy.demo.command.UserCommand;
import com.liveasy.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
public class RegisterController {

    private UserService userService;
    private static final String REGISTRATION_URL = "registration/index";

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/register")
    public String newUser(Model model){

        model.addAttribute("user" , new UserCommand());

        return REGISTRATION_URL;
    }

    @PostMapping
    @RequestMapping("/register")
    public String saveOrUpdate(@Valid @ModelAttribute("user") UserCommand command,
                               BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                        log.debug(objectError.toString());
                    }
            );
            return REGISTRATION_URL;
        }

        UserCommand saveCommand = userService.saveUserCommand(command);
        return"redirect:/login";
    }


}
