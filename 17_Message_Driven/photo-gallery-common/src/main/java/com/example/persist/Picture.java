package com.example.persist;

import javax.persistence.*;

@Table(name = "pictures")
@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1024, nullable = false)
    private String originalFileName;

    @Column(nullable = false)
    private String contentType;

    @Lob
    private String description;

    @Column(length = 1024, nullable = false, unique = true)
    private String pictureFilePath;

    public Picture() {
    }

    public Picture(Long id, String originalFileName, String contentType, String description, String pictureFilePath) {
        this.id = id;
        this.originalFileName = originalFileName;
        this.contentType = contentType;
        this.description = description;
        this.pictureFilePath = pictureFilePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureFilePath() {
        return pictureFilePath;
    }

    public void setPictureFilePath(String pictureFilePath) {
        this.pictureFilePath = pictureFilePath;
    }
}
