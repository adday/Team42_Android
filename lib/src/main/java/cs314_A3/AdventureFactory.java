package cs314_A3;

import java.util.ArrayList;

/**
     Adventure Game  Program Code
     filename: AdventureFactory.java
     purpose: contains interface for abstract factory which
        creates the levels of the adventure game
     Authors:
         Adrion Q Arkenberg
         Alex Day
         Ed Okvath
 **/
public interface AdventureFactory {
    Item getWinningItem();
    Room createAdventure();
    Room createSavedAdventure(ArrayList<Integer> savedGameState);
}
