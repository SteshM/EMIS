package com.emis.EMIS.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Slf4j
@Component

public class FileUpload {
    public boolean pathExist(String rootPath){
        File file = new File(rootPath);
        return file.exists();
    }

    public boolean makeDir(String rootPath){
        File file = new File(rootPath);
        return file.mkdir();
    }


    public String uploadImage(String rootFolder, MultipartFile file){
        log.info(file.getContentType());
        this.makeDir(rootFolder);
        String fileName =""+System.currentTimeMillis();
        try{
            fileName = fileName+"."+extPath(file);
            file.transferTo(Paths.get(rootFolder+"/"+fileName));
            return fileName;
        }catch(Exception e){
            log.info(e.getMessage());
            return null;
        }
    }

    public String extPath(MultipartFile file){
        return file.getContentType().split("/")[1];
    }

    public boolean deleteLocalPicture(String fileName){
        File file = new File(fileName);
        return file.delete();
    }


    public ResponseEntity<byte[]> getPic(String fileName) {
        File file = new File(fileName);
        HttpHeaders headers = new HttpHeaders();
        if(this.getExtension(file).equals("jpeg")){
            headers.setContentType(MediaType.IMAGE_JPEG);
            log.info("file is jpeg");
        }else{
            headers.setContentType(MediaType.IMAGE_PNG);
            log.info("file is png");
        }
        try{
            String path = file.getAbsolutePath();
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            return new ResponseEntity<>( bytes, headers, HttpStatus.valueOf(200));
        }catch(Exception e){
            log.warn(e.getLocalizedMessage());
            return null;
        }
    }
    public String getExtension(File file){
        log.info(file.getName());
        String filename = file.getName();
        if(filename.contains("jpeg")){
            return "jpeg";
        }
        return "png";
    }

}
