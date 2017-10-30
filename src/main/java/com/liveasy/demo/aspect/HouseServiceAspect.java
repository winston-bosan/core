package com.liveasy.demo.aspect;

import com.liveasy.demo.command.HouseCommand;
import com.liveasy.demo.converter.HouseConverter.HouseCommandToHouse;
import com.liveasy.demo.model.House;
import com.liveasy.demo.service.MapService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//Aspect oriented method for Map XML update
// Mark as Component for Spring Bean Loader
//Lombok custom logger

@Aspect
@Component
@Slf4j
public class HouseServiceAspect {

    private final MapService mapService;
    private final HouseCommandToHouse houseCommandToHouse;

    @Autowired
    public HouseServiceAspect(MapService mapService, HouseCommandToHouse houseCommandToHouse) {
        this.mapService = mapService;
        this.houseCommandToHouse = houseCommandToHouse;
    }

    @Around("execution(* com.liveasy.demo.service.HouseServiceImpl.saveHouseCommand(..))")
    public HouseCommand logAroundSaveCommand(ProceedingJoinPoint joinPoint) throws Throwable
    {
        log.debug("****LoggingAspect.logAroundSaveCommand() : " + joinPoint.getSignature().getName() + ": Before Method Execution");
        HouseCommand houseCommand = (HouseCommand) joinPoint.proceed();
        House house = houseCommandToHouse.convert(houseCommand);
        mapService.updateHouseById(house.getId());
        mapService.write();

        log.debug("****LoggingAspect.logAroundSaveCommand() : " + joinPoint.getSignature().getName() + ": After Method Execution");
        return houseCommand;
    }



}
