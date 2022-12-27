package com.example.demo.controllers;

import com.example.demo.DTO.ImageDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.exception_handling.IncorrectProductData;
import com.example.demo.mappers.ImageMapper;
import com.example.demo.models.Image;
import com.example.demo.services.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
@Tag(name = "Image Controller", description = "Методы для работы с изображениями")
public class ImageRestController {

    private final ImageService imageService;
    private final ImageMapper imageMapper;

    @GetMapping("/")
    @Operation(summary = "Возвращает список всех изображений")
    @ApiResponse(
            responseCode = "200",
            description = "Изображения отображены",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ImageDTO.class))
            ))
    @ApiResponse(
            responseCode = "404",
            description = "Нет ни одного изображения в базе данных",
            content = @Content(mediaType = "application/json"
            ))
    public ResponseEntity<List<ImageDTO>> getAllImages () {
        List<Image> images = imageService.getAllImage();
        return new ResponseEntity<>(imageMapper.toListImageDTO(images), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Возвращает изображение по идентификатору")
    @ApiResponse(
            responseCode = "200",
            description = "Изображение с таким идентификатором найдено",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ImageDTO.class)
            ))
    @ApiResponse(
            responseCode = "404",
            description = "Изображение с таким идентификатором не существует",
            content = @Content(mediaType = "application/json"
            ))
    public ResponseEntity<?> getImageById (@PathVariable @Parameter(description = "Идентификатор изображения") Long id) {
        Image image = imageService.findImageById(id);
        return new ResponseEntity<>(imageMapper.toImageDTO(image), HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(summary = "Загрузка нового изображения")
    @ApiResponse(
            responseCode = "201",
            description = "Изображение успешно загружено",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ImageDTO.class)
            ))
    @ApiResponse(
            responseCode = "404",
            description = "Изображение с таким идентификатором уже существует " +
                    "(для гарантированного добавления используй id = 0)",
            content = @Content(mediaType = "application/json"
            ))
    public ResponseEntity<ImageDTO> addImage(@RequestBody ImageDTO imageDTO) {
        Image image = imageService.addImage(imageMapper.toImage(imageDTO));
        return new ResponseEntity<>(imageMapper.toImageDTO(image), HttpStatus.CREATED);
    }



    @PutMapping("/{id}")
    @Operation(summary = "Обновляет изображение с указанным идентификатором")
    @ApiResponse(
            responseCode = "200",
            description = "Изображение успешно обновлено",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ImageDTO.class)
            ))
    @ApiResponse(
            responseCode = "404",
            description = "Изображения с таким идентификатором не существует",
            content = @Content(mediaType = "application/json"
            ))
    public ResponseEntity<Image> updateImage(@RequestBody ImageDTO imageDTO,
                                                @PathVariable Long id) {
        imageDTO.setId(id);
        Image image = imageService.updateImage(imageMapper.toImage(imageDTO));
        return new ResponseEntity<>((image), HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    @Operation(summary = "Удаляет изображение с указанным идентификатором")
    @ApiResponse(
            responseCode = "204",
            description = "Изображение успешно удалено"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Изображения с таким идентификатором не существует",
            content = @Content(mediaType = "application/json"
            ))
    public ResponseEntity<?> deleteImage(@PathVariable Long id) {
        imageService.deleteImageById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
