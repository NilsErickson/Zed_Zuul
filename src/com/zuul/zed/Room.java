package com.zuul.zed;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;

/**
 * Room class contains the hashmap for each given room to facilitate room creation
 * Room class also contains item information
 * @author Nils Erickson
 * @version 2017-05-23
 */
public class Room
{
    private String description;
    public HashMap<String, Room> exits;
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