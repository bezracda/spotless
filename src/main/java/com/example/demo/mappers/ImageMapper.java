package com.example.demo.mappers;

import com.example.demo.DTO.ImageDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.models.Image;
import com.example.demo.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface ImageMapper {


    @Mappings({
            @Mapping(target = "images", ignore = true)
    })
    ProductDTO productToDto(Product product);

    @Mappings({
            @Mapping(target = "images", ignore = true)
    })
    Product dtoToProduct(ProductDTO productDTO);


    ImageDTO toImageDTO (Image image);

    Image toImage (ImageDTO imageDTO);

    List<ImageDTO> toListImageDTO (List<Image> images);

    List<Image> toListImage (List<ImageDTO> imageDTOS);
}
