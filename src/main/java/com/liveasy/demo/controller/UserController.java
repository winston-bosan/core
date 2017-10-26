package com.liveasy.demo.controller;

import com.liveasy.demo.command.UserCommand;
import com.liveasy.demo.exception.NotFoundException;
import com.liveasy.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@Slf4j
public class UserController {
    private final String USER_USERFORM_URL = "user/userform";
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}/show")
    public String getUserPage(@PathVariable String id, Model model){
        //Checks for id format
        /*
        if(!WriteXMLFile.isInteger(id)){
            throw new NumberFormatException("Id Formatting incorrect : " + id.toString());
        }*/

        model.addAttribute("user", userService.findById(new Long(id)));

        return "user/show";

    }

    @GetMapping(value = "user/new")
    public String newUser(Model model){

        model.addAttribute("user" , new UserCommand());

        return USER_USERFORM_URL;
    }


    @GetMapping("/user/{id}/update")
    public String saveOrUpdate(@PathVariable String id, Model model){
        model.addAttribute("user",userService.findCommandById(Long.valueOf(id)));
        return USER_USERFORM_URL;
    }

    @PostMapping
    @RequestMapping("/user/")
    public String saveOrUpdate(@Valid @ModelAttribute("user") UserCommand command,
                               BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
                }
            );
            return USER_USERFORM_URL;
        }

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
     *
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound( Exception exception ){
        log.error("***** Handling not found exception ***** ");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/404error");
        modelAndView.addObject("exception",exception);


        return modelAndView;
    }

}
