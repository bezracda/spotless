package com.example.demo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Schema(name = "Product", description = "Сущность товара")
public class ProductDTO {

    @Schema(description = "Идентификатор товара")
    private long id;

    @Schema(description = "Категория товара")
    private String category;

    @Schema(description = "Описание товара")
    private String name;

    @Schema(description = "Цена товара")
    private BigDecimal price;

    @Schema(description = "Изображения товара")
    private List<ImageDTO> images = new ArrayList<>();

}

