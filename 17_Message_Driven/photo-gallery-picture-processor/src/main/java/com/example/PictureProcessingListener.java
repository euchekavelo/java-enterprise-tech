package com.example;

import com.example.service.PictureService;
import com.example.service.SavedPictureDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PictureProcessingListener {

    @Autowired
    private PictureService pictureService;

    // TODO настроить подписку на события (сообщения) о новых загруженных изображениях
    public void onNewPicture(SavedPictureDto savedPictureDto) {
        pictureService.processPicture(savedPictureDto);
    }
}
