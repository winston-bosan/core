package com.liveasy.demo.controller;

import com.liveasy.demo.command.HouseCommand;
import com.liveasy.demo.command.UserCommand;
import com.liveasy.demo.converter.HouseConverter.HouseToHouseCommand;
import com.liveasy.demo.service.UserService;
import com.liveasy.demo.service.houseService.HouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class HouseController {

    private final UserService userService;
    private final HouseService houseService;
    private final HouseToHouseCommand houseToHouseCommand;

    @Autowired
    public HouseController(UserService userService, HouseService houseService, HouseToHouseCommand houseToHouseCommand) {
        this.userService = userService;
        this.houseService = houseService;
        this.houseToHouseCommand = houseToHouseCommand;
    }

    @GetMapping("/user/{userId}/houses")
    public String getHouses(@PathVariable String userId , Model model){
        log.debug("Currently Getting Houses List from User ID: " + userId);
        model.addAttribute("user",userService.findCommandById(Long.valueOf(userId)));
        return "user/house/list";
    }

    @GetMapping("/user/{userId}/houses/{id}/show")
    public String showHousesDetailed( @PathVariable String userId , @PathVariable String id, Model model ){

        model.addAttribute("house",
                houseService.findCommandByUserIdAndHouseId(Long.valueOf(userId),Long.valueOf(id)) );

        return "user/house/show";
    }

    @GetMapping("/user/{userId}/houses/new")
    public String newHouse(@PathVariable String userId, Model model){



        //Testing if we have a good user!
        UserCommand userCommand = userService.findCommandById( Long.valueOf(userId));
            //todo raise exception if null

        //Now we create the model for the houseCommand object we will allow the user to modify, and pass the user in
        HouseCommand houseCommand = new HouseCommand();
        houseCommand.setUser(userService.findById(Long.valueOf(userId)));

        //We now add this new houseCommand object to the view-model
        model.addAttribute("userId",userId);
        model.addAttribute("house",houseCommand);

        log.debug("Currently creating a new house for User ID: " + userId);

        return "user/house/houseform";
    }

    /*
    *
    *  This part is dedicated for updating and saving houses.
    *
    *
     */

    @GetMapping("/user/{userId}/houses/{id}/update")
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
        log.debug(savedCommand.toString());

        log.debug("Saved House ID: " + savedCommand.getId());
        log.debug("Saved User ID: " + savedCommand.getUser().getId());

        return "redirect:/user/" + savedCommand.getUser().getId() + "/houses/" + savedCommand.getId() + "/show";
    }

    @GetMapping("user/{userId}/houses/{id}/delete")
    public String deleteById(@PathVariable String userId,
                             @PathVariable String id){

        log.debug("Deleting House ID: " + id + " in User ID: " + userId);
        houseService.deleteById(Long.valueOf(id));

        return "redirect:/" + "user/" + userId + "/houses/";
    }


}
