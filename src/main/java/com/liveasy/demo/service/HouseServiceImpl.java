package com.liveasy.demo.service;

import com.liveasy.demo.command.HouseCommand;
import com.liveasy.demo.converter.HouseCommandToHouse;
import com.liveasy.demo.converter.HouseToHouseCommand;
import com.liveasy.demo.model.House;
import com.liveasy.demo.model.User;
import com.liveasy.demo.repository.HouseRepository;
import com.liveasy.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class HouseServiceImpl implements HouseService{

    private final HouseToHouseCommand houseToHouseCommand;
    private final HouseCommandToHouse houseCommandToHouse;
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public HouseServiceImpl(HouseToHouseCommand houseToHouseCommand, HouseCommandToHouse houseCommandToHouse, HouseRepository houseRepository, UserRepository userRepository, UserService userService) {
        this.houseToHouseCommand = houseToHouseCommand;
        this.houseCommandToHouse = houseCommandToHouse;
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public Set<House> getAllHouses() {
        return new HashSet<>( (Collection) houseRepository.findAll());

    }

    @Override
    public House findByUserIdAndHouseId(Long userId, Long houseId) {

        Optional<User> userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent()){
            //todo Impl Error handling
            log.debug("User Id not found: " + userId);
        }

        User user = userOptional.get();

        Optional<House> houseOptional = user.getHouses().stream()
                .filter(house -> house.getId().equals(houseId)).findFirst();

        if(!houseOptional.isPresent()){
            //todo Impl Error handling
            log.debug("House Id not found: " + houseId);
        }

        log.debug("Hey we got a house like this: " + houseOptional.get().toString());
        return houseOptional.get();

    }

    @Override
    public HouseCommand findCommandByUserIdAndHouseId(Long userId, Long houseId) {
        HouseCommand result = houseToHouseCommand.convert( findByUserIdAndHouseId(userId, houseId) );
        log.debug("Hey we got a HouseCommand like this: " + result);
        return result;
    }

    @Override
    @Transactional
    public HouseCommand saveHouseCommand(HouseCommand command) {

        if(command==null){
            log.error("WAIT HOW IS IT NULL?");
        }

        log.debug(command.toString());
        Optional<User> userOptional = userRepository.findById(command.getUser().getId());

        if(!userOptional.isPresent()){
            //todo toss that error!
            log.error("User not found for id: " + command.getUser().getId());
            return new HouseCommand();
        } else {
            User user = userOptional.get();

            Optional<House> houseOptional = user
                    .getHouses().stream()
                    .filter(house -> house.getId().equals(command.getId()))
                    .findFirst();

            if(houseOptional.isPresent()){

                House houseFound = houseOptional.get();
                houseFound.setAddress(command.getAddress());
                houseFound.setYearBuilt(command.getYearBuilt());
                houseFound.setWashrooms(command.getWashrooms());
                houseFound.setBedrooms(command.getBedrooms());
                houseFound.setArea(command.getArea());
                houseFound.setPostal(command.getPostal());
                houseFound.setCity(command.getCity());
                houseFound.setImage(command.getImage());

            } else {

                log.debug("Instead, we are creating a new house for " + user.getId());
                //Getting a new house, then commit/save it, and fish it out with the unique id generated during commit
                House house = new House();
                houseRepository.save(house);
                house = houseRepository.findById(house.getId()).get();

                //Now copying command to house, but with the old id
                command.setId(house.getId());
                house = houseCommandToHouse.convert(command);
                house.setUser(user);
                user.addHouse(house);

                log.debug("The new house is " + house.toString());

            }

            User savedUser = userRepository.save(user);

            return houseToHouseCommand.convert(
                    savedUser.getHouses().stream()
                    .filter(userHouses -> userHouses.getId().equals(command.getId()))
                    .findFirst().get());
        }

    }

    @Override
    public void deleteById(Long l){
        Optional<House> houseOptional = houseRepository.findById(l);
        if(!houseOptional.isPresent()){
            log.debug("Couldn't find house with ID: " + l.toString());
        } else {
            House house = houseOptional.get();
            User user = house.getUser();
            user.getHouses().remove(house);
            houseRepository.delete(house);
            userRepository.save(user);
        }

    }

}
