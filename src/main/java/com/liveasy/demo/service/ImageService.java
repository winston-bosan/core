package com.liveasy.demo.service;


import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void saveImageFile(Long houseId, MultipartFile file);

}
