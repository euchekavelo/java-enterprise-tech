package com.example.controller;

import org.springframework.web.multipart.MultipartFile;

public class UploadPictureDto {

    private MultipartFile pictureFile;

    private String pictureDescription;

    public UploadPictureDto() {
    }

    public UploadPictureDto(MultipartFile pictureFile, String pictureDescription) {
        this.pictureFile = pictureFile;
        this.pictureDescription = pictureDescription;
    }

    public MultipartFile getPictureFile() {
        return pictureFile;
    }

    public void setPictureFile(MultipartFile pictureFile) {
        this.pictureFile = pictureFile;
    }

    public String getPictureDescription() {
        return pictureDescription;
    }

    public void setPictureDescription(String pictureDescription) {
        this.pictureDescription = pictureDescription;
    }
}
