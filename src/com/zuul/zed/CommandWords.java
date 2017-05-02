package com.zuul.zed;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

/**
 * Edit by Nils Erickson:
 *
 * This class is a part of "World of Ninja: Zed" application.
 * World of Ninja: Zed is a text based adventure much like world of zuul.
 *
 * There are no changes in this class.
 *
 * @author Nils Erickson
 * @version 2017.04.09
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
            "go", "quit", "help", "look", "meditate"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word.
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
    /**
     * Print all valid commands to System.out.
     */
    public String getCommandList()
    {
        String c = "";

        for (String command : validCommands){
            c += command + ' ';
        }
        return c;
    }
}
