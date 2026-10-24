package com.example.pokemonzukan_api;

import com.example.pokemonzukan_api.client.PokeApiClient;
import com.example.pokemonzukan_api.client.Pokemon;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/pokemon")
@RestController
public class PokemonController {

    private final PokeApiClient pokeApiClient;

    public PokemonController(PokeApiClient pokeApiClient) {
        this.pokeApiClient = pokeApiClient;
    }

    @GetMapping("/{id}")
    public Pokemon get(@PathVariable int id) throws Exception {
        return pokeApiClient.getTotalPokemonInfo(id);
    }
}
