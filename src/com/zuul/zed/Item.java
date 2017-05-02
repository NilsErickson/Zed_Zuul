package com.zuul.zed;


/**
 * This class creates the items for the games that is later referenced in other classes
 *
 * @param (Description, weight)
 *
 * @author Nils Erickson
 * @version 4.25.2017
 */
public class Item
{
    private String description;
    private int weight;

    /**
     * Create a new item with the given description and weight.
     */
    public Item(String description, int weight)
    {
        this.description = description;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public String getDescription() {
        return description;
    }
}
