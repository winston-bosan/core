package com.liveasy.demo.service.houseService;

import com.liveasy.demo.command.HouseCommand;
import com.liveasy.demo.model.House;

import java.util.Set;

public interface HouseService {
    Set<House> getAllHouses();
    House findByUserIdAndHouseId(Long userId, Long houseId);
    HouseCommand findCommandByUserIdAndHouseId(Long userId, Long houseId);
    HouseCommand saveHouseCommand(HouseCommand command);
    void deleteById(Long l);
    House findByHouseId(Long houseId);
    House saveHouse(House house);

}
