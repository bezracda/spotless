package com.example.demo;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class ProductMarketApplication {

	@GetMapping("/test")
	String home() {
		return "Product is here!";
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductMarketApplication.class, args);
	}
}