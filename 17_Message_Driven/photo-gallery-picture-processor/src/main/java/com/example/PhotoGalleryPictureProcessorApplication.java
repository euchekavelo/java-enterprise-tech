package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {com.example.config.KafkaConfig.class})
public class PhotoGalleryPictureProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoGalleryPictureProcessorApplication.class, args);
    }

}
