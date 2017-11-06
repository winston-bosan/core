package com.liveasy.demo.service.imageService;


import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void saveImageFile(Long houseId, MultipartFile file);

}
