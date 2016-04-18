package cs314_a4.adventuregame;

import org.junit.Test;

import java.lang.Integer;

import cs314_A3.AdventureFactory;
import cs314_A3.AdventureGameModelFacade;
import cs314_A3.LevelZeroAdventureFactory;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestAdventureFactory {
    @Test
    public void TestLevelZeroAdventureFactory() throws Exception {
        AdventureFactory advLvl0 = new LevelZeroAdventureFactory();
        Room startRm = advLvl0.createAdventure();
        assertEquals(startRm.getRoomNum(),0);
        assertEquals(advLvl1.getWinningItem().getItemId(), 0)

    }

    @Test
    public void TestLevelOneAdventureFactory() throws Exception {
        AdventureFactory advLvl1 = new LevelOneAdventureFactory();
        Room startRm = advLvl1.createAdventure();
        assertEquals(startRm.getRoomNum(),0);
        assertEquals(advLvl1.getWinningItem().getItemId(), 3)
    }


}