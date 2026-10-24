package com.example.pokemonzukan_api.client;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//スタッツの値を持つクラス、StatSlotが親クラスである。
@JsonIgnoreProperties(ignoreUnknown = true)
class StatInfo {
    public String name;

    public StatInfo() {
    }
}