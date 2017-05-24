package com.zuul.zed;


/**
 * This class creates the items for the games that is later referenced in other classes
 * todo implement a "weight limit system" to restrict what player can carry; will turn into Adventure/puzzle game (low)
 * @author Nils Erickson
 * @version 2017-05-23
 */
public class Item {
    private String description;
    private int weight;

    /**
     * Create a new item with the given description and weight.
     * @param description
     * @param weight
     */
    public Item(String description, int weight){
        this.description = description;
        this.weight = weight;
    }

    /**
     * @return weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }
}
