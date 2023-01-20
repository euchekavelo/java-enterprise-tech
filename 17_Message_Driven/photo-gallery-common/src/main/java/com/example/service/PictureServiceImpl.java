package com.example.service;

import com.example.persist.Picture;
import com.example.persist.PictureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
class PictureServiceImpl implements PictureService {

    private static final Logger logger = LoggerFactory.getLogger(PictureServiceImpl.class);

    private static final Random rnd = new Random();

    @Autowired
    private PictureRepository pictureRepository;

    @Value("${path.to.picture.storage}")
    private String pathToPictureStorage;

    @Override
    public List<PictureDto> findAll() {
        return pictureRepository.findAll().stream()
                .map(pic -> new PictureDto(pic.getId(), pic.getDescription()))
                .collect(Collectors.toList());
    }

    /**
     * Метод сохраняет загружаемое изображение в директории, путь к которой указан
     * в файле photo-gallery-app/src/main/resources/application.properties
     *
     * @param originalFilename имя файла, который пользователь выбрал в браузере для загрузки
     * @param contentType MIME-тип содержимого для данного файла (application/jpeg и т.п.)
     * @param description текстовое описание картинки, введенное пользователем
     * @param is поток ввода для загрузки файла
     * @return экземпляр SavedPictureDto с информацией о сохраненном файле
     * @throws IOException
     */
    @Override
    public SavedPictureDto uploadPicture(String originalFilename, String contentType, String description, InputStream is) throws IOException {
        String randomFileName = UUID.randomUUID().toString();
        try (OutputStream os = Files.newOutputStream(Path.of(pathToPictureStorage, randomFileName))) {
            is.transferTo(os);
        }
        return new SavedPictureDto(originalFilename, contentType, randomFileName, description);
    }

    /**
     * Метод обрабатывает загружаемый файл. Длительная обработка
     * имитируется вызовом метода {@link #someLongPictureProcessing(String)})
     * @param dto - экземпляр SavedPictureDto с информацией о сохраненном файле
     */
    @Override
    public void processPicture(SavedPictureDto dto) {
        someLongPictureProcessing(dto.getOriginalFileName());
        pictureRepository.save(new Picture(null, dto.getOriginalFileName(), dto.getContentType(), dto.getDescription(), dto.getPathToFile()));
    }

    @Override
    public Optional<PictureDataDto> getData(long id) throws IOException {
        Optional<Picture> opt = pictureRepository.findById(id);
        if (opt.isEmpty()) {
            return Optional.empty();
        }
        Picture pic = opt.get();
        Path path = Path.of(pathToPictureStorage, pic.getPictureFilePath());
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return Optional.empty();
        }
        return Optional.of(new PictureDataDto(pic.getContentType(), Files.newInputStream(path)));
    }

    @Transactional
    @Override
    public void remove(long id) {
        pictureRepository.findById(id)
                .map(pic -> Path.of(pathToPictureStorage, pic.getPictureFilePath()))
                .filter(path -> Files.exists(path) && Files.isRegularFile(path))
                .ifPresent(path -> {
                    try {
                        Files.deleteIfExists(path);
                    } catch (IOException ex) {
                        throw new IllegalStateException("Error in time of picture remove", ex);
                    }
                });
    }

    /**
     * Метод эмитирует длительную обработку изображения
     *
     * @param pictureName - название изображение
     */
    private static void someLongPictureProcessing(String pictureName) {
        try {
            logger.info("Picture '{}' processing started", pictureName);
            int delay = 5000 + rnd.nextInt(5000);
            Thread.sleep(delay);
            logger.info("Picture '{}' processing takes {} millis", pictureName, delay);
        } catch (InterruptedException ex) {
            logger.error("", ex);
        }
    }
}
