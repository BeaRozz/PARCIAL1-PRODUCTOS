package com.productos.productoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProductoserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductoserviceApplication.class, args);
	}

}
