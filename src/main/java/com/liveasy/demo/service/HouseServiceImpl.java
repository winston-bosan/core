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
import java.util.Optional;

@Slf4j
@Service
public class HouseServiceImpl implements HouseService{

    private final HouseToHouseCommand houseToHouseCommand;
    private final HouseCommandToHouse houseCommandToHouse;
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;

    @Autowired
    public HouseServiceImpl(HouseToHouseCommand houseToHouseCommand, HouseCommandToHouse houseCommandToHouse, HouseRepository houseRepository, UserRepository userRepository) {
        this.houseToHouseCommand = houseToHouseCommand;
        this.houseCommandToHouse = houseCommandToHouse;
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
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

            } else {user.addHouse( houseCommandToHouse.convert(command) );}

            User savedUser = userRepository.save(user);

            return houseToHouseCommand.convert(
                    savedUser.getHouses().stream()
                    .filter(userHouses -> userHouses.getId().equals(command.getId()))
                    .findFirst().get());
        }

    }
}
