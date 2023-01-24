package com.example;

import com.example.service.PictureService;
import com.example.service.SavedPictureDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PictureProcessingListener {

    @Autowired
    private PictureService pictureService;

    @KafkaListener(topics = "first.kafka.topic")
    public void onNewPicture(SavedPictureDto savedPictureDto) {
        pictureService.processPicture(savedPictureDto);
    }
}
