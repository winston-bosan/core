package com.liveasy.demo.controller;

import com.liveasy.demo.command.HouseCommand;
import com.liveasy.demo.service.HouseService;
import com.liveasy.demo.service.ImageService;
import com.liveasy.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Controller
public class ImageController {

    private ImageService imageService;
    private UserService userService;
    private HouseService houseService;

    @Autowired
    public ImageController(ImageService imageService, UserService userService, HouseService houseService) {
        this.imageService = imageService;
        this.userService = userService;
        this.houseService = houseService;
    }

    @GetMapping("user/{userId}/houses/{id}/image")
    public String uploadImages(@PathVariable String userId,
                               @PathVariable String id, Model model){

        model.addAttribute("house",houseService.findCommandByUserIdAndHouseId(Long.valueOf(userId),
                Long.valueOf(id)));
        log.debug("Getting Ready to Change Image for House ID: " + id);
        return "user/house/imageuploadform";
    }

    @PostMapping("user/{userId}/houses/{id}/image")
    public String uploadImages(@PathVariable String userId,
                               @PathVariable String id,
                               @RequestParam("imagefile")MultipartFile file){

        imageService.saveImageFile( Long.valueOf(id),file);
        log.debug("Changed Image for House ID: " + id);

        return "redirect:/user/" + userId + "/houses/" + id + "/show";

    }

    @GetMapping("/user/{userId}/houses/{id}/houseimage")
    public void renderImageFromDB(@PathVariable String userId,
                                  @PathVariable String id,
                                  HttpServletResponse response) throws IOException {

        HouseCommand houseCommand = houseService.findCommandByUserIdAndHouseId(Long.valueOf(userId) , Long.valueOf(id));
        byte[] byteArray = new byte[houseCommand.getImage().length];

        int i = 0;
        for(Byte wrappedByte : houseCommand.getImage()){
            byteArray[i++] = wrappedByte;
        }

        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(byteArray);
        IOUtils.copy(is,response.getOutputStream());
    }

}
