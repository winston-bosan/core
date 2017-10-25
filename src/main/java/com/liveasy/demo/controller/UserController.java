package com.liveasy.demo.controller;

import com.liveasy.demo.command.UserCommand;
import com.liveasy.demo.exception.NotFoundException;
import com.liveasy.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}/show")
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
    @RequestMapping("/user/")
    public String saveOrUpdate(@ModelAttribute UserCommand command){
        UserCommand saveCommand = userService.saveUserCommand(command);
        return"redirect:/user/" + saveCommand.getId() + "/show";
    }

    @GetMapping("user/{id}/delete")
    public String deleteById(@PathVariable String id){
        log.debug("Deleting Id: " + id);
        userService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

    /*
     *
     * Below are our Exception Handlers
     *
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound( Exception exception ){
        log.error("Handling not found exception");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/404error");
        modelAndView.addObject("exception",exception);


        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleBadRequest( Exception exception ){
        log.error("Handling Bad Request");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/400error");
        modelAndView.addObject("exception",exception);


        return modelAndView;
    }


}
