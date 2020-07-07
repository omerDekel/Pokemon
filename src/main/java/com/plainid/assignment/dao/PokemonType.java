package com.plainid.assignment.dao;

/**
 * In our pokemon world there are 3 types of pokemons:
 * Grass, Water and fire.
 * Pokemon from particular type has advantage or weakness against other types
 */
public enum PokemonType {
    Grass,Water,Fire;

    /**
     * compare.
     * comparing this pokemonType with another one.
     * @param other pokemo typen to compare with.
     * @return 0 if this pokemonType and the other one are from the same type.
     *  1 if this pokemonType is stronger than the other's pokemonType.
     *  -1 if this pokemonType is weaker than the other's pokemonType.
     */
    public int compare(PokemonType other){
            if(this == other){
            return 0;
        }
        if (this == Water){
            //Grass is stronger than water
            if (other == Grass){
                return -1;
            }
            //watter stronger than fire
            return 1;
        }
        if (this == Fire){
            if (other == Water){
                return -1;
            }
            return 1;
        }
        if (this == Grass){
            //fire stronger than grass
            if (other == Fire)
                return -1;
            return 1;
        }
        return 0;
    }
}
