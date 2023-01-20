package com.example.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface PictureService {

    List<PictureDto> findAll();

    SavedPictureDto uploadPicture(String originalFilename, String contentType, String description, InputStream is) throws IOException;

    void processPicture(SavedPictureDto savedPictureDto);

    Optional<PictureDataDto> getData(long id) throws IOException;

    void remove(long id);
}
