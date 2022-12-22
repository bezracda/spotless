package com.example.demo.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Сущность товара")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор товара")
    private long id;

    @Column(name = "type")
    @Schema(description = "Тип товара")
    private String type;

    @Column(name = "description")
    @Schema(description = "Описание товара")
    private String description;

    @Column(name = "price")
    @Schema(description = "Цена товара")
    private BigDecimal price;

}
