package com.zuul.zed;

import java.lang.String;
/**
 * Changes implemented since last update:
 * Removed createRoom method, created Scenario class instead
 * Main class now handles game start instead of Game method
 * Added comments on each method for brief description
 * todo add specified comments with // to explain method features (low)
 * @author Nils Erickson
 * @version 2017.04.25
 */
public class Game {
    private Parser parser;
    private Room currentRoom;
    private Scenario scenario;

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() {
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
     * Create the game and initialise its internal map.
     */
    public Game(){
        scenario = new Scenario();
        currentRoom = scenario.getStartRoom();
        parser = new Parser();
    }
    /**
     * Print out the opening message for the player.
     * todo keep for alpha testing; once features added, replace with established story (low)
     */
    private void printWelcome() {
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
    private boolean processCommand(Command command)  //implementation of user commands
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("Uhhhh...what was that");
            return false;
        }
        //todo features to add: pickup; drop; attack; (to be added) (med)
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

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp(){
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command){
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
    private boolean quit(Command command){
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
    private void printLocationInfo(){
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
     * todo add two functions to this ability; have user restore stamina/health; provides unique dialog in Temple
     * todo explore option to turn into separate class
     */
    private void meditate(){
        System.out.println("You spend a few moments reflecting on what you have learned...");
    }

    /**
     * Enters Sneak mode...
     * todo expand upon to have character enter stealth mode to sneak past guards
     * todo explore option to create class to handle
     */
    private void sneak(){
        System.out.println("You suddenly get low to the ground and assume the Shinobi stance");
    }
}