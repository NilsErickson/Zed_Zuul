package com.zuul.zed;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * The Scenario represents the world we move in. It holds all locations
 * we can enter in this game.
 *
 * @author Nils Erickson
 * @version 2017-05-23
 */

/**
 * Create all the rooms and link their exits together.
 */
public class Scenario
{
    private ArrayList<Room> rooms;     // List of all rooms in the game
    private Room startRoom;
    private Random random;

    /**
     * Create a complete scenario (with all rooms).
     */
    public Scenario() {
        Room ninjaRoom, weaponroom, meetingRoom, zenTemple, stealthRoom, restRoom, masterRoom, ninjaTransporter;

        // create the rooms
        ninjaRoom = new Room("in the main enterence to the camp. You'll need to walk 10000 steps to get to the next room.");
        weaponroom = new Room("in the Weapon's room. Normally this is where you go to grab your Nunchaku, but not today.");
        meetingRoom = new Room("in the Meeting room. This is where classes are normally held. We will be here tomorrow to be sure");
        zenTemple = new Room("in the Meditation room. When done for the day, this is where you clear your head.");
        stealthRoom = new Room("in the Stealth training room. Ninja are made here, slow but steady will keep you alive.");
        restRoom = new Room("in the Barracks. When I am done for th day, I will come back here for some rest.");
        masterRoom = new Room("in the Master's room. Master is current meditating right now. Better leave him alone.");
        ninjaTransporter = new TransporterRoom("in the secret Shumpo room...with this I can teleport anywhere", this);

        weaponroom.addItem(new Item("Katana", 3));
        weaponroom.addItem(new Item("Nunchaku", 1));
        weaponroom.addItem(new Item("Bo", 2));
        weaponroom.addItem(new Item("Sai", 2));
        ninjaRoom.addItem(new Item("Master's Keys", 1));
        meetingRoom.addItem(new Item("Secret Plans", 1));
        zenTemple.addItem(new Item("Prayer beads", 1));
        stealthRoom.addItem(new Item("Throwing Star", 2));
        restRoom.addItem(new Item("Risque magazines", 1));
        masterRoom.addItem(new Item("Ancient Shinobi Scroll", 5));

        // initialise room exits
        ninjaRoom.setExits("north", ninjaTransporter);

        ninjaTransporter.setExits("north", weaponroom);
        ninjaTransporter.setExits("south", ninjaRoom);

        weaponroom.setExits("north", meetingRoom);
        weaponroom.setExits("south", ninjaTransporter);

        meetingRoom.setExits("north", stealthRoom);
        meetingRoom.setExits("east", restRoom);
        meetingRoom.setExits("south", weaponroom);
        meetingRoom.setExits("west", zenTemple);

        zenTemple.setExits("east", meetingRoom);

        stealthRoom.setExits("south", meetingRoom);

        restRoom.setExits("east", masterRoom);
        restRoom.setExits("west", meetingRoom);

        masterRoom.setExits("west", restRoom);

        // Set the start room
        startRoom = ninjaRoom;  // start game @ ninjaRoom

        rooms = new ArrayList();
        rooms.add(ninjaRoom);
        rooms.add(weaponroom);
        rooms.add(meetingRoom);
        rooms.add(zenTemple);
        rooms.add(stealthRoom);
        rooms.add(restRoom);
        rooms.add(masterRoom);
        rooms.add(ninjaTransporter);

        random = new Random();
    }

    /**
     * Return the room where players start the game.
     */
    public Room getStartRoom(){
        return startRoom;
    }

    /**
     * Return a random room in this game.
     */
    public Room getRandomRoom(){
        return (Room)rooms.get(random.nextInt(rooms.size()));
    }
}
