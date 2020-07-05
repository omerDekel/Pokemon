package com.plainid.assignment.dao;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Created by Omer Dekel on 03/07/2020.
 */
public class Trainer {
    @NotBlank
    private String name;
    private List<String> bag;
    private int level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    /*public Trainer(@NotBlank String name,int level) {
        this.name = name;
        this.level = level;
        this.bag = new ArrayBlockingQueue<Pokemon>(3) ;
    }*/
    /*public void catchNewPokemon(Pokemon pokemon){
        this.bag.add(pokemon);
    }*/
    public void replacePokemon(String newP,String oldP){
        this.bag.set(bag.indexOf(oldP),newP);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBag() {
        return bag;
    }

    public void setBag(List<String> bag) {
        this.bag = bag;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
