package cs314_A3;

/**  Adventure Game  Program Code
     Copyright (c) 1999 James M. Bieman
     filename: Wall.java
     purpose: contains code for the wall class, namely the code for the event that the user runs into a wall.
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

// class Wall

// If Player enters a wall the game says they get hurt

public class Wall implements CaveSite {

	public String enter(Player p)
	{
		return "Ouch! That hurts.";
	}

}

