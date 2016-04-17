package cs314_A3;

import java.util.ArrayList;

/**
 *
 */
public class LevelOneAdventureFactory implements AdventureFactory{
    Item winningItem;

    @Override
    public Room createAdventure() {

        // The outside:
        Room  outside = new Room();
        outside.setRoomNum(0);
        outside.setDesc(
                "You are standing outside in quiet, snowy mountains;\n" +
                        "A large boulder obscures a passage to the east.\n" +
                        "It appears a cave opens up inside. (outside)");

        // Room 1:
        Room r1 = new Room();
        r1.setRoomNum(1);
        r1.setDesc(
                "It is much warmer in here out of the snow.\n"
                        + "The cave is barely high enough to stand. "
                        + "A small ladder descends into darkness in the center of the cave. (r1)" );

        // Room 2:
        Room r2 = new Room();
        r2.setRoomNum(2);
        r2.setDesc(
                "It is very damp down here and there are markings\n" +
                        "on the wall. There is a large opening to the west,\n" +
                        "an eerie, dark tunnel to the east, and \n" +
                        "a ladder ascending above. (r2)");

        // Room 3:
        Room r3 = new Room();
        r3.setRoomNum(3);
        r3.setDesc("Ice has formed on the walls and it is very chilly.\n"
                +"To the east looks like the only way out of here.\n"
                + "It appears someone took refuge here recently. (r3)");

        // Room 4:
        Room r4 = new Room();
        r4.setRoomNum(4);
        r4.setDesc("The tunnel narrows and you are forced to crawl.\n"
                + "It continues to the east, but is very damp.\n"
                + "Another room opens to the north. You can see a small\n"
                + "skull in the corner. (r4)");

        // Room 5:
        Room r5 = new Room();
        r5.setRoomNum(5);
        r5.setDesc("There is a locked door to the north. Someone has \n"
                + "carved their name into it, 'Karl'. Looks like there\n "
                + "is another cave explorer around. (r5)");

        // Room 6:
        Room r6 = new Room();
        r6.setRoomNum(6);
        r6.setDesc("Light shines in from outside from an opening above.\n"
                + "Unfortunately there is no way to get up there."
                + "Sounds are coming from the east. (r6)");

        // Room 7:
        Room r7 = new Room();
        r7.setRoomNum(7);
        r7.setDesc("It is pitch black.");

        // Room 8:
        Room r8 = new Room();
        r8.setRoomNum(8);
        r8.setDesc("It looks like someone has been camping in here. " +
                "A wide opening descends below, scrambling is the only way down. \n" +
                "The room to the west looks is very dark.(r8)");

        // Room 9:
        Room r9 = new Room();
        r9.setRoomNum(9);
        r9.setDesc("It looks like a small rodent has made a home in the corner.\n"
                + "There is a narrow passage to the east covered by spider webs. (r9)");

        // Room 10:
        Room r10 = new Room();
        r10.setRoomNum(10);
        r10.setDesc("It doesn't look like people have been through here.\n" +
                "Wait, there is a small trap door on the ceiling,\n" +
                "but it is locked. (r10)");

        // Room 11:
        Room r11 = new Room();
        r11.setRoomNum(11);
        r11.setDesc("This room is very bright as there is an opening to the side" +
                "of the mountain. There is writing on the wall, 'BEWARE!'." +
                "It looks like someone has hidden something in the corner. (r11).");


        // Connect the outside to Room 1:
        outside.setSide(2,r1);

        r1.setSide(3,outside);
        r1.setSide(5,r2);

        r2.setSide(4,r1);
        r2.setSide(3,r3);
        r2.setSide(2,r4);

        r3.setSide(2, r2);

        r4.setSide(3,r2);
        r4.setSide(0, r6);
        r4.setSide(2,r5);

        r5.setSide(3,r4);

        r6.setSide(1, r4);
        r6.setSide(2, r7);

        r7.setSide(3, r6);
        r7.setSide(2,r8);

        r8.setSide(3, r7);
        r8.setSide(5, r9);

        r9.setSide(4, r8);
        r9.setSide(2,r10);

        r10.setSide(3, r9);

        // setup the wrong key and wrong door
        Key theWrongKey = new Key();
        theWrongKey.setItemId(1);//set item id for saveList
        theWrongKey.setDesc("An old rusty key.");
        r3.addItem(theWrongKey);

        Door theWrongDoor = new Door(r7,r5,theWrongKey);
        r5.setSide(0, theWrongDoor);
        r7.setSide(1, theWrongDoor);

        //create the flashlight
        FlashLight theFlashlight = new FlashLight();
        theFlashlight.setItemId(2);
        theFlashlight.setDesc("A flashlight.");
        r7.addItem(theFlashlight);

        // create the key and door that hold the winning item
        Key theKey = new Key();
        theKey.setItemId(0);//set item id for saveList
        theKey.setDesc("A shiny gold key.");
        r5.addItem(theKey);
        r2.setSide(2,r4);

        Door theDoor = new Door(r10,r11,theKey);
        r10.setSide(4, theDoor);
        r11.setSide(5, theDoor);

        // create the winning item
        Treasure theTreasure = new Treasure();
        theTreasure.setItemId(3);//set item id for saveList
        theTreasure.setDesc("Karl's forgotten gold.");
        r11.addItem(theTreasure);
        winningItem = theTreasure;

        // Now return the entrance:
        winningItem = theTreasure;
        return outside;
    }

    public Item getWinningItem(){
        return winningItem;
    }

    @Override
    public Room createSavedAdventure(ArrayList<Integer> savedGameState) {
        // The outside:
        Room  outside = new Room();
        outside.setRoomNum(0);
        outside.setDesc(
                "You are standing outside in quiet, snowy mountains;\n" +
                        "A large boulder obscures a passage to the east.\n" +
                        "It appears a cave opens up inside. (outside)");

        // Room 1:
        Room r1 = new Room();
        r1.setRoomNum(1);
        r1.setDesc(
                "It is much warmer in here out of the snow.\n"
                        + "The cave is barely high enough to stand. "
                        + "A small ladder descends into darkness in the center of the cave. (r1)" );

        // Room 2:
        Room r2 = new Room();
        r2.setRoomNum(2);
        r2.setDesc(
                "It is very damp down here and there are markings\n" +
                        "on the wall. There is a large opening to the west,\n" +
                        "an eerie, dark tunnel to the east, and \n" +
                        "a ladder ascending above. (r2)");

        // Room 3:
        Room r3 = new Room();
        r3.setRoomNum(3);
        r3.setDesc("Ice has formed on the walls and it is very chilly.\n"
                +"To the east looks like the only way out of here.\n"
                + "It appears someone took refuge here recently. (r3)");

        // Room 4:
        Room r4 = new Room();
        r4.setRoomNum(4);
        r4.setDesc("The tunnel narrows and you are forced to crawl.\n"
                + "It continues to the east, but is very damp.\n"
                + "Another room opens to the north. You can see a small\n"
                + "skull in the corner. (r4)");

        // Room 5:
        Room r5 = new Room();
        r5.setRoomNum(5);
        r5.setDesc("There is a locked door to the north. Someone has \n"
                + "carved their name into it, 'Karl'. Looks like there\n "
                + "is another cave explorer around. (r5)");

        // Room 6:
        Room r6 = new Room();
        r6.setRoomNum(6);
        r6.setDesc("Light shines in from outside from an opening above.\n"
                + "Unfortunately there is no way to get up there."
                + "Sounds are coming from the east. (r6)");

        // Room 7:
        Room r7 = new Room();
        r7.setRoomNum(7);
        r7.setDesc("It is pitch black.");

        // Room 8:
        Room r8 = new Room();
        r8.setRoomNum(8);
        r8.setDesc("It looks like someone has been camping in here. " +
                "A wide opening descends below, scrambling is the only way down. \n" +
                "The room to the west looks is very dark.(r8)");

        // Room 9:
        Room r9 = new Room();
        r9.setRoomNum(9);
        r9.setDesc("It looks like a small rodent has made a home in the corner.\n"
                + "There is a narrow passage to the east covered by spider webs. (r9)");

        // Room 10:
        Room r10 = new Room();
        r10.setRoomNum(10);
        r10.setDesc("It doesn't look like people have been through here.\n" +
                "Wait, there is a small trap door on the ceiling,\n" +
                "but it is locked. (r10)");

        // Room 11:
        Room r11 = new Room();
        r11.setRoomNum(11);
        r11.setDesc("This room is very bright as there is an opening to the side" +
                "of the mountain. There is writing on the wall, 'BEWARE!'." +
                "It looks like someone has hidden something in the corner. (r11).");


        // Connect the outside to Room 1:
        outside.setSide(2,r1);

        r1.setSide(3,outside);
        r1.setSide(5,r2);

        r2.setSide(4,r1);
        r2.setSide(3,r3);
        r2.setSide(2,r4);

        r3.setSide(2, r2);

        r4.setSide(3,r2);
        r4.setSide(0, r6);
        r4.setSide(2,r5);

        r5.setSide(3,r4);

        r6.setSide(1, r4);
        r6.setSide(2, r7);

        r7.setSide(3, r6);
        r7.setSide(2,r8);

        r8.setSide(3, r7);
        r8.setSide(5, r9);

        r9.setSide(4, r8);
        r9.setSide(2,r10);

        r10.setSide(3, r9);

        // setup the wrong key and wrong door
        Key theWrongKey = new Key();
        theWrongKey.setItemId(1);//set item id for saveList
        theWrongKey.setDesc("An old rusty key.");

        Door theWrongDoor = new Door(r7,r5,theWrongKey);
        r5.setSide(0, theWrongDoor);
        r7.setSide(1, theWrongDoor);

        // create the key and door that hold the winning item
        Key theKey = new Key();
        theKey.setItemId(0);//set item id for saveList
        theKey.setDesc("A shiny gold key.");
        r2.setSide(2,r4);

        //create the flashlight
        FlashLight theFlashlight = new FlashLight();
        theFlashlight.setItemId(2);
        theFlashlight.setDesc("A flashlight.");

        Door theDoor = new Door(r10,r11,theKey);
        r10.setSide(4, theDoor);
        r11.setSide(5, theDoor);

        // create the winning item
        Treasure theTreasure = new Treasure();
        theTreasure.setItemId(3);//set item id for saveList
        theTreasure.setDesc("Karl's forgotten gold.");
        winningItem = theTreasure;

        //set player location and return
        Room playerLoc;
        switch(savedGameState.get(1)){
            case 1:
                playerLoc = r1;
                break;
            case 2:
                playerLoc = r2;
                break;
            case 3:
                playerLoc = r3;
                break;
            case 4:
                playerLoc = r4;
                break;
            case 5:
                playerLoc = r5;
                break;
            case 6:
                playerLoc = r6;
                break;
            case 7:
                playerLoc = r7;
                break;
            case 8:
                playerLoc = r8;
                break;
            case 9:
                playerLoc = r9;
                break;
            case 10:
                playerLoc = r10;
                break;
            case 11:
                playerLoc = r11;
                break;
            default:
                playerLoc = outside;
                break;
        }

        //set the treasure location
        switch(savedGameState.get(5)){
            case -1:
                playerLoc.addItem(theTreasure);
                break;
            case 0:
                outside.addItem(theTreasure);
                break;
            case 1:
                r1.addItem(theTreasure);
                break;
            case 2:
                r2.addItem(theTreasure);
                break;
            case 3:
                r3.addItem(theTreasure);
                break;
            case 4:
                r4.addItem(theTreasure);
                break;
            case 5:
                r5.addItem(theTreasure);
                break;
            case 6:
                r6.addItem(theTreasure);
                break;
            case 7:
                r7.addItem(theTreasure);
                break;
            case 8:
                r8.addItem(theTreasure);
                break;
            case 9:
                r9.addItem(theTreasure);
                break;
            case 10:
                r10.addItem(theTreasure);
                break;
            case 11:
                r11.addItem(theTreasure);
                break;
        }

        //set the key location
        switch(savedGameState.get(2)){
            case -1:
                playerLoc.addItem(theKey);
                break;
            case 0:
                outside.addItem(theKey);
                break;
            case 1:
                r1.addItem(theKey);
                break;
            case 2:
                r2.addItem(theKey);
                break;
            case 3:
                r3.addItem(theKey);
                break;
            case 4:
                r4.addItem(theKey);
                break;
            case 5:
                r5.addItem(theKey);
                break;
            case 6:
                r6.addItem(theKey);
                break;
            case 7:
                r7.addItem(theKey);
                break;
            case 8:
                r8.addItem(theKey);
                break;
            case 9:
                r9.addItem(theKey);
                break;
            case 10:
                r10.addItem(theKey);
                break;
            case 11:
                r11.addItem(theKey);
                break;
        }

        //set the flashlight location
        switch(savedGameState.get(4)){
            case -1:
                playerLoc.addItem(theFlashlight);
                break;
            case 0:
                outside.addItem(theFlashlight);
                break;
            case 1:
                r1.addItem(theFlashlight);
                break;
            case 2:
                r2.addItem(theFlashlight);
                break;
            case 3:
                r3.addItem(theFlashlight);
                break;
            case 4:
                r4.addItem(theFlashlight);
                break;
            case 5:
                r5.addItem(theFlashlight);
                break;
            case 6:
                r6.addItem(theFlashlight);
                break;
            case 7:
                r7.addItem(theFlashlight);
                break;
            case 8:
                r8.addItem(theFlashlight);
                break;
            case 9:
                r9.addItem(theFlashlight);
                break;
            case 10:
                r10.addItem(theFlashlight);
                break;
            case 11:
                r11.addItem(theFlashlight);
                break;
        }

        //set the key location
        switch(savedGameState.get(3)){
            case -1:
                playerLoc.addItem(theWrongKey);
                break;
            case 0:
                outside.addItem(theWrongKey);
                break;
            case 1:
                r1.addItem(theWrongKey);
                break;
            case 2:
                r2.addItem(theWrongKey);
                break;
            case 3:
                r3.addItem(theWrongKey);
                break;
            case 4:
                r4.addItem(theWrongKey);
                break;
            case 5:
                r5.addItem(theWrongKey);
                break;
            case 6:
                r6.addItem(theWrongKey);
                break;
            case 7:
                r7.addItem(theWrongKey);
                break;
            case 8:
                r8.addItem(theWrongKey);
                break;
            case 9:
                r9.addItem(theWrongKey);
                break;
            case 10:
                r10.addItem(theWrongKey);
                break;
            case 11:
                r11.addItem(theWrongKey);
                break;
        }

        return playerLoc;
    }
}
