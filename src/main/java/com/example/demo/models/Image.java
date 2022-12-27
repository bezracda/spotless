package com.example.demo.models;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Hidden
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

                       @Column(name="name")
    private String name;

                                   @Column(name = "image")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

}
