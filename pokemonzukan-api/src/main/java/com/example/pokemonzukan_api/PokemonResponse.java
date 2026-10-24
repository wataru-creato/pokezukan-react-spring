package com.example.pokemonzukan_api;

import com.example.pokemonzukan_api.client.PokemonStats;

import java.net.URL;
import java.util.List;

public record PokemonResponse (
    int id;
    String name;
    List<String> types;
    double height;
    double weight;
    int generation;
    URL url;
    PokemonStats stats;
    String swordText,
    String shieldText,
    String latestFlavorText

){}

