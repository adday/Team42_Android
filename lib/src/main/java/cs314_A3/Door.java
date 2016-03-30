package cs314_A3;

/**  Adventure Game  Program Code
     Copyright (c) 1999 James M. Bieman
     filename: Door.java
     purpose: contains code for doors in cave. Doors are locked by default, and contains a method
     			for checking if user has the necessary key to open.
     Authors:
     	Adrion Q Arkenberg
     	Alex Day
     	Ed Okvath


     To compile: javac cs314_A1.AdventureGame.java
     To run:     java cs314_A1.AdventureGame

     The main routine is AdventureGame.main

------UPDATES------
 Update February 2016: Adrion Q Arkenberg
  updated compile/run instructions to use correct package name cs314_A1
  added filename to top of file
  added file purpose

 **/

// class Door
//[FIXED] Defect 2: Originally able to enter a door that goes between two rooms 
	//					even if you are not in either of the rooms. Now there is a check
	//					to ensure that you must be in one of the two rooms.
	

public class Door implements CaveSite {
	/** In this implementation doors are always locked.
      A player must have the correct key to get through
      a door.  Doors automatically lock after a player
      passes through. */

	private Key myKey;

	/** The door's location. */
	private CaveSite outSite;
	private CaveSite inSite;

	/** We can construct a door at the site. */
	public Door(CaveSite out, CaveSite in, Key k){
		//ensure key is not null and that the cavesites are not doors themselves
		if(k != null && (out.getClass() != Door.class) && (in.getClass() != Door.class)){
			outSite = out;
			inSite = in;
			myKey = k;
		}
	}
 
	/** A player will need the correct key to enter. */
	public String enter(Player p){
		String keyString;
			if (p.haveItem(myKey)) {
				keyString = "Your key works! The door creaks open,\nand slams behind you after you pass through.";
				if (p.getLoc() == outSite) 
					return keyString + inSite.enter(p);
				else if (p.getLoc() == inSite) 
					return keyString + outSite.enter(p); 
				else keyString = "You are not in a room that is adjacent to the door.";
			}
			else 
				keyString = "You don't have the key for this door!\nSorry.";		
		return keyString;
	}

}

