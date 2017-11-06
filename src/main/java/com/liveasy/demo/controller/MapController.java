package com.liveasy.demo.controller;

import com.liveasy.demo.service.houseService.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MapController {

    private HouseService houseService;

    @Autowired
    public MapController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("/map")
    public String seeMap(Model model){
        model.addAttribute("houses",houseService.getAllHouses());
        return "mapview";
    }


    // call http://<host>/hello.json
    @RequestMapping(value = "/map/xml", method = RequestMethod.GET, produces = "application/xml")
    @ResponseBody
    public FileSystemResource helloRest() {
        return new FileSystemResource("//Users//simon//IdeaProjects//liveasyDemo//src//main//resources//location//location.xml");
    }



}
