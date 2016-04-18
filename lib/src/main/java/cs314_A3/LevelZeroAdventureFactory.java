package cs314_A3;

import java.util.ArrayList;

/**
     Adventure Game  Program Code
     filename: LevelZeroAdventureFactory.java
     purpose: contains code for creating a new level0 cave,
        a loaded level0 cave, and set the winning item for level0
     Authors:
         Adrion Q Arkenberg
         Alex Day
         Ed Okvath
 **/
public class LevelZeroAdventureFactory implements AdventureFactory {
    Item winningItem;

    @Override
    public Room createAdventure() {
      // The outside:
      Room  outside = new Room();
      outside.setRoomNum(0);
      outside.setDesc(
        "You are standing outside, on the edge of a cliff;\n"
	    + "A creek runs alongside the cliff.\n"
	    + "a cave opens straight down (outside).");

   // Room 1:
      Room r1 = new Room();
      r1.setRoomNum(1);
      r1.setDesc(
              "The darkness is pierced by a bright light overhead.\n"
                      + "There is a narrow, dark passage to the east (r1).");

   // Room 2:
      Room r2 = new Room();
      r2.setRoomNum(2);
      r2.setDesc(
	"You are in a gloomy oval shaped room with grey walls.\n" +
	 "There is a dim light to the west, and a narrow\n" +
	 "dark hole to the east only about 18 inches high (r2).");

  // Room 3:
     Room r3 = new Room();
     r3.setRoomNum(3);
     r3.setDesc("You really need your flashlight here.\n"+
		"There is a wide passage that quickly narrows\n"
		+"to the west, a bright opening to the east,\n"
		+ "and a deep hole that appears to have no bottom\n"
		+ "in the middle of the room (r3).");

        // Room 4:
        Room r4 = new Room();
        r4.setRoomNum(4);
        r4.setDesc("There is what looks like a giant grizzly bear\n"
                + "skull in a corner.  A passage leads to the west,\n"
                + "another one to the north, and a slippery route\n"
                + "goes down steeply. You can hear the shrieks of bats (r4).");

        // Room 5:
        Room r5 = new Room();
        r5.setRoomNum(5);
        r5.setDesc("There is a dim light from above and the shrieks\n"
                + "are clearly coming from a passageway to the east (r5).");

        // Room 6:
        Room r6 = new Room();
        r6.setRoomNum(6);
        r6.setDesc("The ceiling is full of bats.\n"
                + "You should put your hat on your head (r6).");

        // Room 7:
        Room r7 = new Room();
        r7.setRoomNum(7);
        r7.setDesc("This room is very damp. There are puddles on the floor\n" +
                "and a steady dripping from above (r7).");
        // Room 8:
        Room r8 = new Room();
        r8.setRoomNum(8);
        r8.setDesc("A lizard scampers past you, or is it a snake?\n" +
                "a narrow passage runs to the east and an even narrower one\n" +
                "runs to the west (r8).");

        // Room 9:
        Room r9 = new Room();
        r9.setRoomNum(9);
        r9.setDesc("It looks pretty boring here.\n"
                + "There is a narrow passage to the east\n (r9)");

        // Room 10:
        Room r10 = new Room();
        r10.setRoomNum(10);
        r10.setDesc("It looks like someone has been here.\n" +
                "There is a pile of candy wrappers on the floor,\n" +
                "and maybe something else. \n" +
                "Wait, there is a trap door on the floor,\n" +
                "but it is locked (r10).");
        // Room 11:
        Room r11 = new Room();
        r11.setRoomNum(11);
        r11.setDesc("This room is very dark. You can just barely see (r11).");

     outside.setSide(5,r1);
     r1.setSide(4,outside);
     r1.setSide(2,r2);

     r2.setSide(3,r1);
     r2.setSide(2,r3);

     r3.setSide(3, r2);
     r3.setSide(2,r4);
     r3.setSide(5,r5);

     r4.setSide(3,r3);
     r4.setSide(5,r7);
     r4.setSide(0,r8);


     r5.setSide(4,r3);
     r5.setSide(2,r6);

     r6.setSide(3,r5);

     r7.setSide(4, r4);

     r8.setSide(1,r4);
     r8.setSide(3,r9);
     r8.setSide(2,r10);
     r9.setSide(2,r8);
     r10.setSide(3,r8);

 // Create a key and put it in r6:
    Key theKey = new Key();
    theKey.setItemId(1); //set item id for saveList
    theKey.setDesc("A shiny gold key.");
    r6.addItem(theKey);

 // We add a door between r10 and r11:
    Door theDoor = new Door(r10,r11,theKey);
    r10.setSide(5, theDoor);
    r11.setSide(4, theDoor);

    // Now return the entrance:
        Treasure theTreasure = new Treasure();
        theTreasure.setItemId(0); //set item id for saveList
        theTreasure.setDesc("A bag filled with gold bars.");
        r11.addItem(theTreasure);
        winningItem = theTreasure;

        // Now return the entrance:

        return outside;
    }

    public Item getWinningItem(){
        return winningItem;
    }

    @Override
    public Room createSavedAdventure(ArrayList<Integer> savedGameState){
// The outside:
        Room  outside = new Room();
        outside.setRoomNum(0);
        outside.setDesc(
                "You are standing outside, on the edge of a cliff;\n"
                        + "A creek runs alongside the cliff.\n"
                        + "a cave opens straight down (outside).");

        // Room 1:
        Room r1 = new Room();
        r1.setRoomNum(1);
        r1.setDesc(
                "The darkness is pierced by a bright light overhead.\n"
                        + "There is a narrow, dark passage to the east (r1).");

        // Room 2:
        Room r2 = new Room();
        r2.setRoomNum(2);
        r2.setDesc(
                "You are in a gloomy oval shaped room with grey walls.\n" +
                        "There is a dim light to the west, and a narrow\n" +
                        "dark hole to the east only about 18 inches high (r2).");

        // Room 3:
        Room r3 = new Room();
        r3.setRoomNum(3);
        r3.setDesc("You really need your flashlight here.\n"+
                "There is a wide passage that quickly narrows\n"
                +"to the west, a bright opening to the east,\n"
                + "and a deep hole that appears to have no bottom\n"
                + "in the middle of the room (r3).");

        // Room 4:
        Room r4 = new Room();
        r4.setRoomNum(4);
        r4.setDesc("There is what looks like a giant grizzly bear\n"
                + "skull in a corner.  A passage leads to the west,\n"
                + "another one to the north, and a slippery route\n"
                + "goes down steeply. You can hear the shrieks of bats (r4).");

        // Room 5:
        Room r5 = new Room();
        r5.setRoomNum(5);
        r5.setDesc("There is a dim light from above and the shrieks\n"
                + "are clearly coming from a passageway to the east (r5).");

        // Room 6:
        Room r6 = new Room();
        r6.setRoomNum(6);
        r6.setDesc("The ceiling is full of bats.\n"
                + "You should put your hat on your head (r6).");

        // Room 7:
        Room r7 = new Room();
        r7.setRoomNum(7);
        r7.setDesc("This room is very damp. There are puddles on the floor\n" +
                "and a steady dripping from above (r7).");
        // Room 8:
        Room r8 = new Room();
        r8.setRoomNum(8);
        r8.setDesc("A lizard scampers past you, or is it a snake?\n" +
                "a narrow passage runs to the east and an even narrower one\n" +
                "runs to the west (r8).");

        // Room 9:
        Room r9 = new Room();
        r9.setRoomNum(9);
        r9.setDesc("It looks pretty boring here.\n"
                + "There is a narrow passage to the east\n (r9)");

        // Room 10:
        Room r10 = new Room();
        r10.setRoomNum(10);
        r10.setDesc("It looks like someone has been here.\n" +
                "There is a pile of candy wrappers on the floor,\n" +
                "and maybe something else. \n" +
                "Wait, there is a trap door on the floor,\n" +
                "but it is locked (r10).");
        // Room 11:
        Room r11 = new Room();
        r11.setRoomNum(11);
        r11.setDesc("This room is very dark. You can just barely see (r11).");


        outside.setSide(5,r1);
        r1.setSide(4,outside);
        r1.setSide(2,r2);

        r2.setSide(3,r1);
        r2.setSide(2,r3);

        r3.setSide(3, r2);
        r3.setSide(2,r4);
        r3.setSide(5,r5);

        r4.setSide(3,r3);
        r4.setSide(5,r7);
        r4.setSide(0,r8);


        r5.setSide(4,r3);
        r5.setSide(2,r6);

        r6.setSide(3,r5);

        r7.setSide(4, r4);

        r8.setSide(1,r4);
        r8.setSide(3,r9);
        r8.setSide(2,r10);
        r9.setSide(2,r8);
        r10.setSide(3,r8);

        // Create a key and put it in r6:
        Key theKey = new Key();
        theKey.setItemId(1); //set item id for saveList
        theKey.setDesc("A shiny gold key.");

        // We add a door between r10 and r11:
        Door theDoor = new Door(r10,r11,theKey);
        r10.setSide(5, theDoor);
        r11.setSide(4, theDoor);

        // Now return the entrance:
        Treasure theTreasure = new Treasure();
        theTreasure.setItemId(0); //set item id for saveList
        theTreasure.setDesc("A bag filled with gold bars.");
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
        switch(savedGameState.get(2)){
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
        switch(savedGameState.get(3)){
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



        return playerLoc;
    }
}
