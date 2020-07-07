package com.plainid.assignment.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Created by Omer Dekel on 03/07/2020.
 * Each Trainer has a name, level (number), and a bag of Pokemons.
 */
public class Trainer {
    //members
    @NotBlank
    private String name;
    private int level;
    private List<String> bag;
    @JsonIgnore
    private int maxSizeBag =3;
    @JsonIgnore
    private int id;

    /**
     * maxSize Getter
     * @return max size of the bag.
     */
    public int getMaxSizeBag() {
        return maxSizeBag;
    }

    /***
     * id getter.
     * @return trainer's id.
     */
    public int getId() {
        return id;
    }

    /***
     * id setter.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /***
     * name getter
     * @return trainer's name.
     */
    public String getName() {
        return name;
    }

    /***
     * name setter .
     * @param name trainer's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /***
     * bag getter.
     * @return trainer's pokemon bag.
     */
    public List<String> getBag() {
        return bag;
    }

    /***
     * bag setter.
     * @param bag trainer's pokemon bag.
     */
    public void setBag(List<String> bag) {
        this.bag = bag;
    }

    /***
     *level getter.
     * @return trainer's level.
     */
    public int getLevel() {
        return level;
    }

    /***
     *level setter.
     * @param level .
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /***
     * bagContains
     * @param name of pokemon .
     * @return either the bag contains the pokemon or not.
     */
    public Boolean bagContains(String name){
        return  (bag.contains(name));
    }

    /***
     * isFull
     * @return true if the bag is full,
     * else false.
     */
    @JsonIgnore
    public Boolean isFull(){
        return this.bag.size()>= maxSizeBag;
    }

    /***
     * maxSize setter.
     * @param max_size
     */
    public void setMaxSize(int max_size) {
        this.maxSizeBag = max_size;
    }

}
