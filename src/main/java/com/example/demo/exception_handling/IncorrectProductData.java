package com.example.demo.exception_handling;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Сообщение об ошибке")
public class IncorrectProductData {
    @Schema(description = "Описание ошибки")
    private String info;

    public IncorrectProductData() {
    }
}
