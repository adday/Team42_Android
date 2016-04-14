package cs314_A3;

/**
 *
 */
public class LevelOneAdventureFactory implements AdventureFactory{
    Item winningItem;

    @Override
    public Room createAdventure() {

        //THIS IS CURRENTLY LEVEL ONE- NEEDS TO BE CHANGED

        // The outside:
        Room  outside = new Room();
        outside.setDesc(
                "You are standing outside in quiet, snowy mountains;\n" +
                        "A large boulder obscures a passage to the east.\n" +
                        "It appears a cave opens up inside. (outside)");

        // Room 1:
        Room r1 = new Room();
        r1.setDesc(
                "It is much warmer in here out of the snow.\n"
                        + "The cave is barely high enough to stand"
                        + "A small ladder descends into darkness in the center of the cave. (r1)" );

        // Connect the outside to Room 1:
        outside.setSide(2,r1);
        r1.setSide(3,outside);

        // Room 2:
        Room r2 = new Room();
        r2.setDesc(
                "It is very damp down here and there are markings\n" +
                        "on the wall. There is a large opening to the west,\n" +
                        "and a eerie, dark tunnel to the east, and" +
                        "a ladder ascending above. (r2)");

        // Room 3:
        Room r3 = new Room();
        r3.setDesc("Ice has formed on the walls and it is very chilly.\n"
                +"To the east looks like the only way out of here.\n"
                + "It appears someone took refuge here recently. (r3)");

        // Connect Rooms 1, 2, & 3:
        r1.setSide(5,r2);
        r2.setSide(4,r1);
        r2.setSide(3,r3);
        r3.setSide(2, r2);

        // Room 4:
        Room r4 = new Room();
        r4.setDesc("The tunnel narrows and you are forced to crawl.\n"
                + "It continues to the west, but seems to narrow.\n"
                + "Another room opens to the north. You can see a small"
                + "skull in the corner. (r4)");

        // Room 5:
        Room r5 = new Room();
        r5.setDesc("There is a locked door to the north. Someone has carved their name\n"
                + "into it, 'Karl'. Looks like there is another cave explorer around. (r5)");

        // Room 6:
        Room r6 = new Room();
        r6.setDesc("Light shines in from outside from an opening above.\n"
                + "Unfortunately there is no way to get up there."
                + "Sounds are coming from the east. (r6)");

        // Room 7:
        Room r7 = new Room();
        r7.setDesc("OH NO! You are not alone!\n" +
                    "You have found Karl, fight him or run away. (r7)");

        // Connect rooms 3, 4, 5, 6, & 7.
        r2.setSide(2,r4);
        r4.setSide(3,r2);
        r4.setSide(0,r6);
        r4.setSide(2,r5);
        r5.setSide(3,r4);
        r6.setSide(1,r4);
        r7.setSide(3,r6);

        // We add a door between r7 and r5:
        Key theWrongKey = new Key();
        theWrongKey.setDesc("An old rusty key.");
        Door theWrongDoor = new Door(r7,r5,theWrongKey);
        r5.setSide(0,theWrongDoor);
        r7.setSide(1, theWrongDoor);

        // Room 8:
        Room r8 = new Room();
        r8.setDesc("It looks like someone has been camping in here." +
                "A wide opening descends below, scrambling is the only way down. \n" +
                "The room to the west looks holds memories.(r8)");

        // Room 9:
        Room r9 = new Room();
        r9.setDesc("It looks like a small rodent has made a home in the corner.\n"
                + "There is a narrow passage to the east covered by spider webs. (r9)\n");

        // Room 10:
        Room r10 = new Room();
        r10.setDesc("It doesn't look like people have been through here.\n" +
                "Wait, there is a small trap door on the ceiling,\n" +
                "but it is locked. (r10)");

        // Room 11:
        Room r11 = new Room();
        r11.setDesc("This room is very bright as there is an opening to the side" +
                "of the mountain. There is writing on the wall, 'BEWARE!'." +
                "It looks like someone has hidden something in the corner. (r11).");

        Treasure theTreasure = new Treasure();
        theTreasure.setDesc("Karl's hidden gold.");
        r11.addItem(theTreasure);

        // Lets connect them:
        r7.setSide(2,r8);


        r4.setSide(0,r8);
        r8.setSide(1,r4);
        r8.setSide(3,r9);
        r8.setSide(2,r10);
        r9.setSide(2,r8);
        r10.setSide(3,r8);

        // Create a key and put it in r6:
        Key theKey = new Key();
        theKey.setDesc("A shiny gold key.");
        r6.addItem(theKey);

        // We add a door between r10 and r11:
        Door theDoor = new Door(r10,r11,theKey);
        r10.setSide(5,theDoor);
        r11.setSide(4,theDoor);

        // Now set winning item and return the entrance:
        winningItem = theTreasure;
        return outside;
    }

    public Item getWinningItem(){
        return winningItem;
    }
}
