package com.liveasy.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/sell")
public class SellController {

    @GetMapping("/new-listing")
    public String newListing(){
        return "sell/new-listing";
    }

    @GetMapping("/")
    public String sellPage(){
        return "sell/index";
    }

}
