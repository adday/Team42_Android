package cs314_a4.adventuregame;

import org.junit.Test;

import java.lang.Integer;
import java.util.ArrayList;

import cs314_A3.AdventureGameModelFacade;

import static org.junit.Assert.*;

/**
 Adventure Game  Program Code
 filename: saveLoadTest.java
 purpose: tests saving and loading fxnality of Adventure Game
 Authors:
    Adrion Q Arkenberg

 **/

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class saveLoadTest {

    //test to make sure that default item list for lvl 0
    // used to save game is being created correctly
    @Test
    public void initializeSaveItemListTest0() throws Exception {
        AdventureGameModelFacade model = new cs314_A3.AdventureGameModelFacade(0);
        ArrayList<Integer> a = model.getSaveItemList();
        assertEquals(a.get(0), 11);
        assertEquals(a.get(1), 6);
    }

    //test to make sure that default loaded item list for lvl 1
    // used to save game is being loaded correctly
    @Test
    public void initializeSaveItemListTest1() throws Exception {
        AdventureGameModelFacade model = new cs314_A3.AdventureGameModelFacade(1);
        ArrayList<Integer> a = model.getSaveItemList();
        assertEquals(a.get(0), 5);
        assertEquals(a.get(1), 3);
        assertEquals(a.get(2), 7);
        assertEquals(a.get(3), 11);

    }
}