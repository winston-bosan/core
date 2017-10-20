package com.liveasy.core.bootstrap;

import com.liveasy.core.model.House;
import com.liveasy.core.model.User;
import com.liveasy.core.repository.HouseRepository;
import com.liveasy.core.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class Bootstraper implements ApplicationListener<ContextRefreshedEvent> {

    HouseRepository houseRepository;
    UserRepository userRepository;

    @Autowired
    public Bootstraper(HouseRepository houseRepository, UserRepository userRepository) {
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("Loading Bootstrap Data");
        init();
    }

    private void init() {

        House firstHouse = new House("3 Suncrest Drive",4,3,4000,1995);
        User frank = new User();

        frank.addHouse(firstHouse);
        userRepository.save(frank);
    }

}