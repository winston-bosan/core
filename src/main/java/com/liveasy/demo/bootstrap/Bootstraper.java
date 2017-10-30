package com.liveasy.demo.bootstrap;

import com.liveasy.demo.model.House;
import com.liveasy.demo.model.HouseSubmodels.Comment;
import com.liveasy.demo.model.HouseSubmodels.Layout;
import com.liveasy.demo.model.HouseSubmodels.Location;
import com.liveasy.demo.model.User;
import com.liveasy.demo.repository.HouseRepository;
import com.liveasy.demo.repository.UserRepository;
import com.liveasy.demo.service.MapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Slf4j
@Component
@Profile("default")
public class Bootstraper implements ApplicationListener<ContextRefreshedEvent> {

    HouseRepository houseRepository;
    UserRepository userRepository;
    MapService mapService;

    @Autowired
    public Bootstraper(HouseRepository houseRepository, UserRepository userRepository, MapService mapService) {
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
        this.mapService = mapService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("========Loading Bootstrap Data========");
        init();
    }

    public void init() {

        House firstHouse = new House();
        House thirdHouse = new House();
        House firstHouseA = new House();

        Location firstHouseLocation = new Location();
        Location thirdHouseLocation = new Location();
        Location firstHouseALocation = new Location();

        firstHouseLocation.setCity("Toronto"); firstHouseLocation.setStreet("Suncrest Drive");
        firstHouseLocation.setStreetNumber(8);

        thirdHouseLocation.setCity("Toronto"); thirdHouseLocation.setStreet("Bloor Street");
        thirdHouseLocation.setStreetNumber(54);

        firstHouseALocation.setCity("Toronto"); firstHouseALocation.setStreet("Bridle Path");
        firstHouseALocation.setStreetNumber(1);


        Layout firstHouseLayout = new Layout(5,2,1000,1995);
        Layout thirdHouseLayout = new Layout(5,2,1000,1995);
        Layout firstHouseALayout = new Layout(5,2,1000,1995);

        ArrayList<String> test = new ArrayList<>();
        test.add("TEST");
        Comment comment1 = new Comment(test);
        Comment comment2 = new Comment(test);
        Comment comment3 = new Comment(test);



        firstHouse.setComment(comment1);
        thirdHouse.setComment(comment2);
        firstHouseA.setComment(comment3);

        firstHouse.setLocation(firstHouseLocation);
        thirdHouse.setLocation(thirdHouseLocation);
        firstHouseA.setLocation(firstHouseALocation);

        firstHouse.setLayout(firstHouseLayout);
        thirdHouse.setLayout(thirdHouseLayout);
        firstHouseA.setLayout(firstHouseALayout);

        User frank = new User();
        frank.setFirstName("Frank"); frank.setLastName("Fang"); frank.setDescription("Hello I am frank!");
        User jerry = new User();
        jerry.setFirstName("Jerry"); jerry.setLastName("Wilhelm"); jerry.setDescription("Guten Tag, Ich bin Jerry");

        frank.addHouse(firstHouse);
        frank.addHouse(thirdHouse);
        jerry.addHouse(firstHouseA);



        userRepository.save(frank);
        userRepository.save(jerry);

        mapService.updateHouseById(firstHouse.getId());
        mapService.updateHouseById(thirdHouse.getId());
        mapService.updateHouseById(firstHouseA.getId());
        mapService.write();

        log.debug("========Finished Loading Bootstrapper========");
    }

}