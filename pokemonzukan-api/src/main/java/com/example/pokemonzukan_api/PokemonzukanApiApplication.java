package com.example.pokemonzukan_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class PokemonzukanApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonzukanApiApplication.class, args);
	}

}


