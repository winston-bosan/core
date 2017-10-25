package com.liveasy.demo.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MapController {

    @GetMapping("/map")
    public String seeMap(Model model){
        return "hellotest";
    }


    // call http://<host>/hello.json
    @RequestMapping(value = "/map/xml", method = RequestMethod.GET, produces = "application/xml")
    @ResponseBody
    public FileSystemResource helloRest() {
        return new FileSystemResource("//Users//simon//IdeaProjects//liveasyDemo//src//main//resources//location.xml");
    }



}
