package cs314_a4.adventuregame;

import org.junit.Test;

import java.lang.Integer;

import cs314_A3.AdventureGameModelFacade;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class UseFlashLightTest {

    //test to ensure that flashlight is in correct room and that its
    // 'useFlashlight' method is working as it should by checking it in its
    // default room.
    @Test
    public void useFlashLight_isCorrect() throws Exception {
        AdventureGameModelFacade AGMF = new AdventureGameModelFacade(1);

        assertEquals(new ArrayList<Integer>, AGMF.useFlashLight());

        AGMF.goEast();
        AGMF.goDown();
        AGMF.goEast();
        AGMF.goNorth();
        AGMF.goEast();

        AGMF.grabItem(AGMF.getRoomContents()[0]);

        // This is what useFlashLight should noew return in r7
        ArrayList<Integer> testKey = new ArrayList<Integer>();
        testKey.add(0);
        testKey.add(2);
        testKey.add(1);
        testKey.add(1);
        testKey.add(0);
        testKey.add(0);

        assertEquals(testKey, AGMF.useFlashLight());
    }
}