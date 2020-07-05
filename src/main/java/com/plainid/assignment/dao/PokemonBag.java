package com.plainid.assignment.dao;

import java.util.List;

/**
 * Created by Omer Dekel on 05/07/2020.
 */
public class PokemonBag {
    List<String> pokemons;
    int max_size =3;

    public List<String> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<String> pokemons) {
        this.pokemons = pokemons;
    }
    public int getMax_size() {
        return max_size;
    }
    public Boolean isFull(){
        return this.pokemons.size()>=3;
    }
    public void setMax_size(int max_size) {
        this.max_size = max_size;
    }
}
