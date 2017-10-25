package com.liveasy.demo.aspect;

import com.liveasy.demo.xmlwriter.WriteXMLFile;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//Aspect oriented method for Map XML update
@Aspect
//Mark as Component for Spring Bean Loader
@Component
//Lombok custom logger
@Slf4j
public class HouseServiceAspect {

    private final WriteXMLFile writeXMLFile;

    @Autowired
    public HouseServiceAspect(WriteXMLFile writeXMLFile) {
        this.writeXMLFile = writeXMLFile;
    }

    @After("execution(* saveHouseCommand(..))")
    public void saveAfterHouseCommand(){
        //Note, really, the only time we save anything is a houseCommand converted into a house, so this covers house
        //as well

        log.debug("\nGenerating Test XML\n");

        writeXMLFile.write();

    }

}
