package com.example.service;

public class SavedPictureDto {

    private String originalFileName;

    private String contentType;

    private String pathToFile;

    private String description;

    public SavedPictureDto() {
    }

    public SavedPictureDto(String originalFileName, String contentType, String pathToFile, String description) {
        this.originalFileName = originalFileName;
        this.contentType = contentType;
        this.pathToFile = pathToFile;
        this.description = description;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
