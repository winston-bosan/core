package com.liveasy.demo.bootstrap;

import com.liveasy.demo.model.House;
import com.liveasy.demo.model.HouseSubmodels.Comment;
import com.liveasy.demo.model.HouseSubmodels.Layout;
import com.liveasy.demo.model.HouseSubmodels.Location;
import com.liveasy.demo.model.Role;
import com.liveasy.demo.model.User;
import com.liveasy.demo.repository.HouseRepository;
import com.liveasy.demo.repository.UserRepository;
import com.liveasy.demo.service.UserService;
import com.liveasy.demo.service.houseService.HouseService;
import com.liveasy.demo.service.mapService.MapServiceImpl;
import com.liveasy.demo.service.roleService.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
@Profile("default")
public class Bootstraper implements ApplicationListener<ContextRefreshedEvent> {

    private HouseRepository houseRepository;
    private UserRepository userRepository;
    private RoleService roleService;
    private MapServiceImpl mapService;
    private UserService userService;
    private HouseService houseService;

    @Autowired
    public Bootstraper(HouseRepository houseRepository, UserRepository userRepository, RoleService roleService,
                       MapServiceImpl mapService, UserService userService, HouseService houseService) {
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.mapService = mapService;
        this.userService = userService;
        this.houseService = houseService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("========Loading Bootstrap Data========");
        loadUsers();
        loadRoles();
        assignUsersToAdminRole();
        assignUsersToUserRole();
        init();
    }

    private void loadUsers() {
        User user1 = new User();
        user1.setEmail("user@mail.com");
        user1.setPassword("user");
        userService.saveOrUpdate(user1);

        User user2 = new User();
        user2.setEmail("admin@mail.com");
        user2.setPassword("admin");
        userService.saveOrUpdate(user2);

    }

    private void loadRoles() {
        Role role = new Role();
        role.setRole("USER");
        roleService.saveOrUpdate(role);
        log.info("Saved role" + role.getRole());

        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        roleService.saveOrUpdate(adminRole);
        log.info("Saved role" + adminRole.getRole());
    }

    private void assignUsersToUserRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("USER")) {
                users.forEach(user -> {
                    if (user.getEmail().equals("user@mail.com")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });
    }

    private void assignUsersToAdminRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("ADMIN")) {
                users.forEach(user -> {
                    if (user.getEmail().equals("admin@mail.com")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });
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

        User frank = userService.findByUsername("user@mail.com");
        frank.setFirstName("Frank"); frank.setLastName("Fang"); frank.setDescription("Hello I am frank!");
        User jerry = userService.findByUsername("admin@mail.com");
        jerry.setFirstName("Jerry"); jerry.setLastName("Wilhelm"); jerry.setDescription("Guten Tag, Ich bin Jerry");

        houseService.saveHouse(firstHouse);
        houseService.saveHouse(thirdHouse);
        houseService.saveHouse(firstHouseA);

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