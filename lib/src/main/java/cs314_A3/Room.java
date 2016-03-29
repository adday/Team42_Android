package cs314_A3;

/**  Adventure Game  Program Code
     Copyright (c) 1999 James M. Bieman
     filename: Room.java
     purpose: contains Room class as well as data for room description, 
     			sides (for navigating through adjacent rooms), and contents.
     Authors:
     	Adrion Q Arkenberg
     	Alex Day
     	Ed Okvath
     The main routine is AdventureGame.main

------UPDATES--------
     Update August 2010: refactored Vector contents into ArrayList<Item> contents.
      This gets rid of the use of obsolete Vector and makes it type safe.

     Update February 2016: Adrion Q Arkenberg
      updated compile/run instructions to use correct package name cs314_A1
      added filename to top of file
      added file purpose				    
 **/

// class Room


import java.util.ArrayList;
import java.util.ListIterator;

/* Room class
 * provides functionality for locations in the game and
 * gives descriptions of items 
 * */

public class Room implements CaveSite {

	private String description;

	private CaveSite[] side = new CaveSite[6];

	private ArrayList<Item> contents = new ArrayList<Item>();

	// Constructors creates 6 new walls and stores them in the CaveSite array side
	public Room() {
		side[0] = new Wall();
		side[1] = new Wall();
		side[2] = new Wall();
		side[3] = new Wall();
		side[4] = new Wall();
		side[5] = new Wall();
	}

	// sets description
	public void setDesc(String d){
		description = d;
	}

	// sets side of the Room using direction and CaveSite given
	public void setSide(int direction, CaveSite m){
		side[direction] = m;
	}
	//returns side given direction- used for testing setSide
	public CaveSite getSide(int direction){
		return side[direction];
	}

	// adds item to contents of the Room like when a player drops an item
	public void addItem(Item theItem){
		//ensure that the item is not in the room already
		if(!contents.contains(theItem) && (theItem != null))
			contents.add(theItem);
	}

	// removes item from contents of the Room like when a player picks up an item
	public void removeItem(Item theItem){
		//use a while loop to remove all copies of the item
		while(contents.contains(theItem))
			contents.remove(theItem);
	}

	// true if the Room has no contents
	public boolean roomEmpty(){
		return contents.isEmpty();
	}

	// returns an array of all the contents in the ArrayList named contents
	public Item[] getRoomContents(){
		Item[] contentsArray = new Item[contents.size()];
		contentsArray = contents.toArray(contentsArray);
		return contentsArray;
	}

	// for setting the location of the Player in the Room like when they enter it
	public String enter(Player p) {
		p.setRoom(this); 
		return "";
	}

	// for Player movement by using the walls and calling enter to set location
	public String exit(int direction, Player p){
		return side[direction].enter(p);
	}

	/* returns a description of the room by looping through the contents of the Room 
	 * and progessively adding to the String to be returned and then returning it
	 * */
	public String getDesc(){
		return description;
	}

}

