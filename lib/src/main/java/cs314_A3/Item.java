package cs314_A3;

/**  Adventure Game  Program Code
     Copyright (c) 1999 James M. Bieman
     filename: Item.java
     purpose: contains code for item class. Has a var for the item's description,
     			and 2 methods for setting and getting an item's description
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


// class Item
// contains a description (string) of items and it can be set
// made as a parent class

public class Item {

	private String description;

	private int currRoom = -2;

	private int itemId;

	//desc setters and getters
	public void setDesc(String d){
		description = d;
	}

	public String getDesc(){
		return description;
	}

	//item id setters and getters
	public void setItemId(int id) { itemId = id;}

	public int getItemId(){ return itemId;}

	//room# setters and getters
	public int getCurrRoom(){return currRoom;}

	public void setCurrRoom(int roomNum){
		currRoom = roomNum;
	}

}

