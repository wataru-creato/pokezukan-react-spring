package com.example.pokemonzukan_api.client;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.net.URL;
import java.util.ArrayList;

//ポケモン基本情報のクラス、親クラス　ID、名前、高さ、重さをもつ
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {
    public int id;
    public String name;
    public ArrayList<TypeSlot> types;
    public double height;
    public double weight;
    public Integer generaion;
    public URL url;
    public ArrayList<StatSlot> stats;
    public ArrayList<TextSlot> textEntries;

    public Pokemon() {
    }

    public Pokemon(int id, String name, ArrayList<TypeSlot> types, double height, double weight, Integer generaion,URL url, ArrayList<StatSlot> stats,ArrayList<TextSlot> textEntries) {
        this.id = id;
        this.name = name;
        this.types = types;
        this.height = height;
        this.weight = weight;
        this.generaion=generaion;
        this.url = url;
        this.stats = stats;
        this.textEntries=textEntries;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<TypeSlot> getTypes() {
        return this.types;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public  Integer getGeneraion(){return generaion;}

    public URL getUrl() {
        return url;
    }

    public ArrayList<StatSlot> getStats() {
        return this.stats;
    }

    public ArrayList<TextSlot> getTextEntries() {
            return textEntries;
    }
}
