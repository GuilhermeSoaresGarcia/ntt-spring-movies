package com.example.moviesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "NTT Movies Spring API", version = "1", description = "API desenvolvida com Spring para a academia NTT"))
public class MoviesapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesapiApplication.class, args);
	}
}
