package cs314_A3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**  Adventure Game  Program Code
     Copyright (c) 1999 James M. Bieman
     filename: Player.java
     purpose: contains code for Player class including methods for navigating, and checking inventory. Also,
     			contains vars for location, inventory, and item count.
	Authors:
     	Adrion Q Arkenberg
     	Alex Day
     	Ed Okvath


------UPDATES------
 Update February 2016: Adrion Q Arkenberg
  updated compile/run instructions to use correct package name cs314_A1
  added filename to top of file
  added file purpose


 **/

/* Class Player
 * contains all the abilities the player can use as well as some method and booleans
 * to provide functionality
 *
 *
 *[FIXED] Defect 1: Defect found with test BBtestPickUpItemInDifferentRoom().
 *					Fixed by ensuring that the room the player is in is the same
 *					as the room that the item is in.
 *
 *
 **/

public class Player {

	// Room used for player location
	private Room myLoc;

	private int playerRoomNum = -1; //set default roomNum to invalid

	private Item[] myThings = new Item[2];

	private int itemCount = 0;

    private int health = 100;

    // How much damage the player does to enemies per attack
    private int damage = 20;

    private int accuracy=70;

	//private HashMap<String,Integer> stateHolder = new HashMap<>();

	/* Method for functionality, not something the player directly activates
   For when the player changes rooms*/
	public void setRoom(Room r){
		myLoc = r;
		playerRoomNum = r.getRoomNum();
	}

	/* Method for the Player to check what is around them using getDesc() from the Room class
   to get the description of the location */
	public String look() {
		return myLoc.getDesc();
	}

	/* For player movement. Calls exit() from the room class which then calls 
  enter() from the same class to put the player in the correct location in the
  direction that they moved
	 */
	public String go(int direction){
		//player's room number is set in Room's 'enter' method when Player.setRoom is called
		return myLoc.exit(direction, this);
	}

	/* For picking up items. Players are limited to 2 items so this does nothing 
	 * if they have 2 or more items. If they have less than 2 items it stores
	 * Item i into the proper location of myThings aka the Player inventory.
	 * removeItem() is then called to remove the item from the room
	 *  */
	public void pickUp(Item i){
		ArrayList<Item> roomItems = new ArrayList<Item>(Arrays.asList(myLoc.getRoomContents()));
		//ensure that the item is in the room that the player is in and it is not null
		if(roomItems.contains(i) && (i != null)){
			if (itemCount < 2) {
				myThings[itemCount] = i;
				i.setCurrRoom(-1); //update to 'player' value (ie -1)
				itemCount++;
				myLoc.removeItem(i);
					}
		}
	}
	// Getter for items
	public Item[] getItems(){
		return myThings;
	}

	/* Checks if a player has an item by looping through myThings
	 * returns true if they do and false otherwise
	 *  */
	public boolean haveItem(Item itemToFind){
		for (int n = 0; n < itemCount ; n++)
			if (myThings[n] == itemToFind) return true;
		return false;
	}

	/* drops item in the room at the Player's location
	 * checks if the Player has items and that itemNum is valid
	 * it then drops whatever item is being dropped based on the item number */
	public void drop(int itemNum){
		if (itemNum > 0 & itemNum <= itemCount){
			switch(itemNum){
				case 1: {
					myLoc.addItem(myThings[0]);
					myThings[0].setCurrRoom(playerRoomNum); //set items room# to player's room
					myThings[0]=myThings[1];
					itemCount--;
					break;
				}
				case 2: {
					myLoc.addItem(myThings[1]);
					myThings[1].setCurrRoom(playerRoomNum); //set items room# to player's room
					itemCount--;
					break;
				} 
			}
		}
	}

	// get location
	public Room getLoc(){return myLoc;}

	/* Shows what the Player is currently holding
	 * it does this by looping through the array myThings and calling getDesc() from the Room class
	 * */
	public String showMyThings(){
		String outString = "";
		for (int n = 0; n < itemCount ; n++)
			outString = outString + Integer.toString(n+1) + ": " 
					+ myThings[n].getDesc() + "\n";
		return outString;
	} 

	// Boolean that checks if Player has filled myThings by checking itemCount
	public boolean handsFull(){return itemCount==2;}

	// Boolean that checks if the Player is carrying anything
	public boolean handsEmpty(){return itemCount==0;}

	// Returns how many items the Player is carrying
	public int numItemsCarried(){return itemCount;}

	//Returns hashmap containing state of game
	//public HashMap<String,Integer> getGameState(){return stateHolder;}

	//get player's room number
	public int getPlayerRoomNum(){return playerRoomNum;}

    public int getHealth(){
        return health;
    }

    public void setHealth(int h){
        health = h;
    }

    public String attack(Enemy enemy){

        Random rng = new Random();

        int hitOrMiss = rng.nextInt(100) + 1;

        // When the player attacks they have a chance at missing if the hitOrMiss variable is less than accuracy
        // This if statement should make it so the player hits accuracy% of the time.If it is a hit the health of the enemy
        // is reduced by the damage the player deals

        if(hitOrMiss <= accuracy){
            enemy.setHealth(enemy.getHealth() - damage);

            return "Hit " + enemy.getName() + " for " + damage + " damage\n";
        }

        return "Attack missed\n";

    }

}

