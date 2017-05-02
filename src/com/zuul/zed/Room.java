package com.zuul.zed;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  The exits are labelled north,
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

/**
 * A1 No changes to this class
 *
 * A2 Changes include:
 * - Editing the room class to incorperate new directions in getExit
 *
 * A3 Changes include:
 * -Add Iterator to include interate on items and make them easier to describe
 * -Add Hashmap sets for items so to be similar to exits
 *
 * @author Nils Erickson
 * @version 2017.04.25
 */
public class Room
{
    private String description;
    private HashMap<String, Room> exits;
    private HashSet items;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<>();
        items = new HashSet();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room to which the exit leads.
     */
    public void setExits(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction." If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return the room in the given direction.
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    /**
     * Return a string describing the items in the room, for example
     * "Items: beer wine".
     */
    public String getItemString()
    {
        String returnString = "Items:";
        for(Iterator iter = items.iterator(); iter.hasNext(); )
            returnString += " " + ((Item) iter.next()).getDescription();

        return returnString;
    }
    /**
     * Return a description of the room's exits,
     * for example, "Exits: north west".
     * @return A description of the available exits.
     */
    public String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys){
            returnString += " " + exit;
        }
        return returnString;
    }
    /**Return a long description of this room, of the form:
     *    You are in the kitchen.
     *    Exits: north west
     * @return A description of the room, including exits
     */
    public String getLongDescription(){
        return "You are " + description + ".\n" + getExitString() + ".\n" + getItemString();
    }
    /**
     * Puts an item into this room.
     */
    public void addItem(Item item) {
        items.add(item);
    }
}