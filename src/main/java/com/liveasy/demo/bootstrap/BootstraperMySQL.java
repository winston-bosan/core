package com.liveasy.demo.bootstrap;

import com.liveasy.demo.repository.HouseRepository;
import com.liveasy.demo.repository.UserRepository;
import com.liveasy.demo.service.MapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@Profile({"dev","prod"})
public class BootstraperMySQL implements ApplicationListener<ContextRefreshedEvent> {

    HouseRepository houseRepository;
    UserRepository userRepository;
    MapService mapService;
    Bootstraper bootstraper;

    @Autowired
    public BootstraperMySQL(HouseRepository houseRepository, UserRepository userRepository, MapService mapService, Bootstraper bootstraper) {
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
        this.mapService = mapService;
        this.bootstraper = bootstraper;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("========Loading Bootstrap Data for MySQL========");
        bootstraper.init();
    }



}