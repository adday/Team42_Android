package cs314_a4.adventuregame;

import org.junit.Test;

import java.lang.Integer;
import java.util.ArrayList;

import cs314_A3.AdventureGameModelFacade;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class saveLoadTest {

    @Test
    public void initializeSaveItemListTest0() throws Exception {
        AdventureGameModelFacade model = new cs314_A3.AdventureGameModelFacade(0);
        ArrayList<Integer> a = model.getSaveItemList();
        assertEquals(a.get(0), 11);
        assertEquals(a.get(1), 6);
    }

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