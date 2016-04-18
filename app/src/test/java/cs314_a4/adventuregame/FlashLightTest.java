package cs314_a4.adventuregame;

import org.junit.Test;

import java.lang.Integer;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class FlashLightTest {
    @Test
    public void FlashLight_isCorrect() throws Exception {
        FlashLight f = new FlashLight();
        Room loc = new Room();
        Room open = new Room();
        Room closed = new Room();

        Door theDoor = new Door(loc,closed,new Key());
        loc.setSide(1,open);
        loc.setSide(2, theDoor);

        ArrayList<Inetger> sidesList = f.checkRoom(loc);

        // This is what sidesList should end up being
        ArrayList<Integer> testKey = new ArrayList<Integer>();
        testKey.add(0);
        testKey.add(1);
        testKey.add(2);
        testKey.add(0);
        testKey.add(0);
        testKey.add(0);

        assertEquals(testKey, sidesList);
    }
}