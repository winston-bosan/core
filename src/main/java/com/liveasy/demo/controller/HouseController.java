package com.liveasy.demo.controller;

import com.liveasy.demo.command.HouseCommand;
import com.liveasy.demo.service.HouseService;
import com.liveasy.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class HouseController {

    private final UserService userService;
    private final HouseService houseService;

    @Autowired
    public HouseController(UserService userService, HouseService houseService) {
        this.userService = userService;
        this.houseService = houseService;
    }

    @GetMapping
    @RequestMapping("/user/{userId}/houses")
    public String getHouses(@PathVariable String userId , Model model){
        log.debug("Currently Getting Houses List from User ID: " + userId);
        model.addAttribute("user",userService.findCommandById(Long.valueOf(userId)));
        return "user/house/list";
    }

    @GetMapping
    @RequestMapping("user/{userId}/houses/{id}/show")
    public String showHousesDetailed( @PathVariable String userId , @PathVariable String id, Model model ){

        model.addAttribute("house",
                houseService.findCommandByUserIdAndHouseId(Long.valueOf(userId),Long.valueOf(id)) );

        return "user/house/show";
    }

    @GetMapping
    @RequestMapping("user/{userId}/houses/{id}/update")
    public String updateUserHouse( @PathVariable String userId , @PathVariable String id, Model model ){

        log.debug("Currently on User: " + userId + " House: " + id);

        model.addAttribute("house",
                houseService.findCommandByUserIdAndHouseId(Long.valueOf(userId),Long.valueOf(id)) );

        return "user/house/houseform";
    }

    @PostMapping("/user/{userId}/houses")
    public String saveOrUpdateUserHouse(@ModelAttribute HouseCommand house){
        //log.debug("DOES THIS WORK??!?!?!?!?!??!");

        HouseCommand savedCommand = houseService.saveHouseCommand(house);

        log.debug("Saved House ID: " + savedCommand.getId());
        log.debug("Saved User ID: " + savedCommand.getUser().getId());

        return "redirect:/user/" + savedCommand.getUser().getId() + "/houses/" + savedCommand.getId() + "/show";
    }


}
