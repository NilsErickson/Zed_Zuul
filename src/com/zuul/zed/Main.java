package com.zuul.zed;

/**
 * This class initiates the game by:
 * Creating a new game state,
 * instantiating the play() method to begin loop
 * @ Author: Nils Erickson
 * @ Version: 2017-05-23
 */
public class Main {
    public static void main(String[] args){
        Game game = new Game();
        game.play();    //initiates play loop, see Game class
    }
}
