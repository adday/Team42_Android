package cs314_A3;

import java.util.ArrayList;

/**
 *
 */
public interface AdventureFactory {
    Item getWinningItem();
    Room createAdventure();
    Room createSavedAdventure(ArrayList<Integer> savedGameState);
}
