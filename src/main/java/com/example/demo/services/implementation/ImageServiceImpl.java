package com.example.demo.services.implementation;

import com.example.demo.models.Image;
import com.example.demo.repositories.ImageRepository;
import com.example.demo.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public Image addImage(Image image) {
        return imageRepository.save(image);
    }

    @Override
    @Transactional (readOnly = true)
    public List<Image> getAllImage() {
        return imageRepository.findAll();
    }

    @Override
    @Transactional (readOnly = true)
    public Image findImageById(long id) {
        return imageRepository.findImageById(id);
    }

    @Override
    @Transactional
    public Image updateImage(Image image) {
        return imageRepository.save(image);
    }

    @Override
    @Transactional
    public void deleteImageById(long id) {
        imageRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Image toImageEntity(MultipartFile multipartFile) {
        try {
            Image image = new Image();
            image.setImage(multipartFile.getBytes());
            return image;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
