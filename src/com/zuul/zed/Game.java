package com.zuul.zed;
import java.lang.String;
/**
 * This class is part of the "World of Ninja: Zed" application
 *
 * Changes include:
 * -Changed the printWelcome to include more descriptive text
 * -Changed the rooms to include the newer versions that include the newer games
 * -Changed the room layouts to fit my scenario. Added 7 rooms in total to createRooms class
 * -Changed the Quit dialog as well as Help dialog
 *
 * @author Nils Erickson
 * @version A1 2017.04.09
 * todo Clean up Comments dialog
 */

/**
 * A2 Changes include:
 * - Refractored the class by adding a new method called printLocationInfo() to cut down
 * on coupling between printWelcome and goRoom
 * @author Nils Erickson
 * @version A1 2017.04.12
 */

/**
 * A3 Changes include:
 * Adding the items to this class
 * Included item descriptions
 * Lessed reliant on print and more on string
 * @author Nils Erickson
 * @version A1 2017.04.25
 */

/**
 * A4 Changes:
 * Added repository to GitHub
 * Commited project to master
 * Added Sneak command
 * Cleaned up Welcome
 */
public class Game
{
    private Parser parser;
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room ninjaRoom, weaponroom, meetingRoom, zenTemple, stealthRoom, restRoom, masterRoom;

        // create the rooms
        ninjaRoom = new Room("in the main enterence to the camp. You'll need to walk 10000 steps to get to the next room.");
        weaponroom = new Room("in the Weapon's room. Normally this is where you go to grab your Nunchaku, but not today.");
        meetingRoom = new Room("in the Meeting room. This is where classes are normally held. We will be here tomorrow to be sure");
        zenTemple = new Room("in the Meditation room. When done for the day, this is where you clear your head.");
        stealthRoom = new Room("in the Stealth training room. Ninja are made here, slow but steady will keep you alive.");
        restRoom = new Room("in the Barracks. When I am done for th day, I will come back here for some rest.");
        masterRoom = new Room("in the Master's room. Master is current meditating right now. Better leave him alone.");

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
        ninjaRoom.setExits("north", weaponroom);
        weaponroom.setExits("north", meetingRoom);
        meetingRoom.setExits("north", stealthRoom);
        meetingRoom.setExits("east", restRoom);
        meetingRoom.setExits("south", weaponroom);
        meetingRoom.setExits("west", zenTemple);
        zenTemple.setExits("east", meetingRoom);
        stealthRoom.setExits("south", meetingRoom);
        restRoom.setExits("east", masterRoom);
        restRoom.setExits("west", meetingRoom);
        masterRoom.setExits("west", restRoom);

        currentRoom = ninjaRoom;  // start game @ ninjaRoom
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Tomorrow is another day, better get some sleep.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Ninja: Zed!!!");
        System.out.println("World of Ninja: Zed is a new adventure game.");
        System.out.println("Setting: NINJA TRAINING CAMP, IGA PROVENCE, JAPAN Time: 1597");
        System.out.println("Since you were born, you have lived in this camp.");
        System.out.println("Today is your day off, your allowed to move around the camp.");
        System.out.println("Take a look around the camp to get your bearing.");
        System.out.println("Type quit to get some rest as tomorrow is another training day");
        System.out.println("Type 'go' and the desired direction to move around");
        System.out.println("Type 'help' if you need help remembering.");
        System.out.println("Try out all of the commands to gain some insight with the world");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("Uhhhh...what was that");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("look")){
            look();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("meditate")){
            meditate();
        }
        else if (commandWord.equals("sneak")){
            sneak();
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp()
    {
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * Prints information about the current location
     */
    private void printLocationInfo()
    {
        System.out.println("You are " + currentRoom.getLongDescription());
        System.out.print("Exits: ");
        currentRoom.getExitString();
    }
    /**
     * Prints information about a particular object within the world
     */
    private void look(){
        System.out.println(currentRoom.getLongDescription());
    }
    /**
     * Has your character meditate...
     * todo expand upon concept to trigger while in ZenTemple to provide flavor dialog
     */
    private void meditate(){
        System.out.println("You spend a few moments reflecting on what you have learned...");
    }
    /**
     * Enters Sneak mode...
     * todo expand upon to have character enter stealth mode to sneak past a gaurd
     */
    private void sneak(){
        System.out.println("You suddenly get low to the ground and assume the Shinobi stance");
    }
}