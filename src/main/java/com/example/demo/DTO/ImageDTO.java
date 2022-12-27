package com.example.demo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema (description = "Изображение товара")
public class ImageDTO {

                         @Schema(description = "Идентификатор изображения")
                private long id;

    @Schema(description = "Наименование изображения")



    private String name;

    @Schema(description = "Идентификатор принадлежности к продукту")
    private long product_id;

    @Schema(description = "Изображение")
    private byte[] image;

    @Schema (description = "Товар")
    private ProductDTO product;

}
