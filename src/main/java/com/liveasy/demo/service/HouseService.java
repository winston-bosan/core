package com.liveasy.demo.service;

import com.liveasy.demo.command.HouseCommand;
import com.liveasy.demo.model.House;

public interface HouseService {

    House findByUserIdAndHouseId(Long userId, Long houseId);
    HouseCommand findCommandByUserIdAndHouseId(Long userId, Long houseId);
    HouseCommand saveHouseCommand(HouseCommand command);

}
