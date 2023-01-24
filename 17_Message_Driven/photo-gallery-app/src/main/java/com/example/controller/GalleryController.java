package com.example.controller;

import com.example.service.PictureDataDto;
import com.example.service.PictureService;
import com.example.service.SavedPictureDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class GalleryController {

    private final static Logger LOGGER = LoggerFactory.getLogger(GalleryController.class);
    private final PictureService pictureService;
    private final KafkaTemplate<String, SavedPictureDto> kafkaTemplate;

    @Autowired
    public GalleryController(PictureService pictureService, KafkaTemplate<String, SavedPictureDto> kafkaTemplate) {
        this.pictureService = pictureService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("pictures", pictureService.findAll());
        return "index";
    }

    @PostMapping("/upload")
    public String uploadPicture(UploadPictureDto uploadPictureDto) throws IOException {
        MultipartFile file = uploadPictureDto.getPictureFile();
        SavedPictureDto savedPictureDto = pictureService.uploadPicture(file.getOriginalFilename(),
                file.getContentType(),
                uploadPictureDto.getPictureDescription(),
                file.getInputStream());

        kafkaTemplate.send("first.kafka.topic", savedPictureDto);
        LOGGER.info("Картинка загружена и передана в очередь для ассинхронной обработки.");
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public void pictureData(@PathVariable long id, HttpServletResponse response) throws IOException {
        PictureDataDto dto = pictureService.getData(id)
                .orElseThrow(() -> new NotFoundException("Picture with id " + id + " not found"));

        response.setContentType(dto.getContentType());
        try (InputStream is = dto.getInputStream()) {
            is.transferTo(response.getOutputStream());
        }
    }

    @PostMapping("/{id}")
    public String removePicture(@PathVariable long id) {
        pictureService.remove(id);
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({IOException.class, IllegalStateException.class})
    public String serverErrorHandler(Exception ex, Model model) {
        model.addAttribute("errorMessage", ex.getLocalizedMessage());
        model.addAttribute("pictures", pictureService.findAll());
        return "index";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public String notFoundExceptionHandler(NotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getLocalizedMessage());
        model.addAttribute("pictures", pictureService.findAll());
        return "index";
    }
}
