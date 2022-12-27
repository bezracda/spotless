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
public interface ProductMapper {


    @Mappings({
            @Mapping(target = "product.images", ignore = true)
    })
    ImageDTO ImageToDto(Image image);

    @Mappings({
            @Mapping(target = "product.images", ignore = true)
    })
    Image dtoToImage(ImageDTO imageDTO);

    ProductDTO toProductDTO (Product product);

    Product toProduct (ProductDTO  productDTO);

    List<ProductDTO> toListProductsDTO (List<Product> products);

    List<Product> toListProducts (List<ProductDTO> productDTOS);

}
