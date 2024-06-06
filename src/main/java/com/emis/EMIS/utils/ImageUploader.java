package com.emis.EMIS.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;

@Component
@Slf4j
public class ImageUploader {

    public String uploadImage(String path, MultipartFile file){
        String imageName = STR."\{System.currentTimeMillis()}";
        imageName +="." +extName(file);
        createDir(path);
        try{
            file.transferTo(Paths.get(path+"/"+imageName));
            return imageName;
        }catch (Exception e){
            log.warn(e.getLocalizedMessage());
            return null;
        }
    }

    public void deleteImg(String imageLocation){
        File file=  new File(imageLocation);
        file.delete();
    }
    private void createDir(String path){
        File file=  new File(path);
        if(!file.exists()){
            file.mkdir();
        }
    }

    private String extName(MultipartFile file){
        String fileName = file.getOriginalFilename();
        System.out.println(fileName.split("."));
        return "png";
    }
}