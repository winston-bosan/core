package com.liveasy.demo.bootstrap;

import com.liveasy.demo.model.House;
import com.liveasy.demo.model.User;
import com.liveasy.demo.repository.HouseRepository;
import com.liveasy.demo.repository.UserRepository;
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
        House secondHouse = new House("43 Gold Street",1,1,1230,2004);
        House thirdHouse = new House("4923 Bloor Street",10,7,39000,2017);

        House firstHouseA = new House("4 Suncrest Drive",4,3,4000,1995);

        User frank = new User();
        frank.setFirstName("Frank"); frank.setLastName("Fang"); frank.setDescription("Hello I am frank!");
        User jerry = new User();
        jerry.setFirstName("Jerry"); jerry.setLastName("Wilhelm"); jerry.setDescription("Guten Tag, Ich bin Jerry");


        frank.addHouse(firstHouse);
        frank.addHouse(secondHouse);
        frank.addHouse(thirdHouse);

        jerry.addHouse(firstHouseA);

        userRepository.save(frank);
        userRepository.save(jerry);
    }

}