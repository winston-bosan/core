package com.liveasy.demo.service;

import com.liveasy.demo.model.House;
import com.liveasy.demo.repository.HouseRepository;
import com.liveasy.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService{

    private final UserRepository userRepository;
    private final HouseRepository houseRepository;

    @Autowired
    public ImageServiceImpl(UserRepository userRepository, HouseRepository houseRepository) {
        this.userRepository = userRepository;
        this.houseRepository = houseRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long houseId, MultipartFile file) {
        try{

            House house = houseRepository.findById(houseId).get();
            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;

            for(byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            house.setImage(byteObjects);
            houseRepository.save(house);

        } catch (IOException e){
            log.debug("Error occured", e);
            e.printStackTrace();
        }

    }
}
