package com.example.demo.services;

import com.example.demo.models.Image;
import com.example.demo.models.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    Image addImage(Image image);
    List<Image> getAllImage();
    Image findImageById(long id);
    Image updateImage(Image image);
    void deleteImageById(long id);
}
