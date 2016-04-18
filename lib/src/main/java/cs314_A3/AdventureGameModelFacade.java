package cs314_A3;
/* Adventure Game  Program Code
 * filename: AdventureGameModelFacade.java
 * Authors:
 * 		Adrion Q Arkenberg
 *    	Alex Day
 *     	Ed Okvath
 * 
 * 
 * 
 * main routine is in AdventureGameView.java
 */

import java.util.ArrayList;

public class AdventureGameModelFacade {
    //private class variables
	private Player thePlayer;
	private Room startRm;
    private AdventureFactory adventureFactory;
    private static int level;
    private ArrayList<Integer> saveItemList;

  //constructor for creating a given level
  //of the adventure game, and setting the player in that room
  //CALLED WHEN A NEW GAME IS STARTED ON EITHER LEVEL
  public AdventureGameModelFacade(int level) {
      this.level = level;
      initializeSaveItemList(level);
      adventureFactory = getAdventureFactory();
	  thePlayer = new Player();
	  startRm = adventureFactory.createAdventure();
	  thePlayer.setRoom(startRm);
  }

  /*
   constructor for creating a loaded game
   the loaded data from the save file is passed in
   as the arraylist
   */
  public AdventureGameModelFacade(ArrayList<Integer> savedGameState) {
        level = savedGameState.get(0);
        initializeLoadItemList(savedGameState);
        adventureFactory = getAdventureFactory();
        thePlayer = new Player();
        startRm = adventureFactory.createSavedAdventure(savedGameState);
        thePlayer.setRoom(startRm);
        playerPickUpInitialItems(savedGameState);
    }

  /*
   auxiliary method for the constructor that loads a saved game
   picks up all the items in the savedGame arraylist that are
   marked '-1' indicating they're on the player.
     NOTE: THE ITEMS ARE ADDED TO THE PLAYERS ROOM IN THE LEVEL CONSTRUCTORS
   */
  private void playerPickUpInitialItems(ArrayList<Integer> savedGameState){
      for(int i=2;i<savedGameState.size();i++) {
          if(savedGameState.get(i) == -1) {
              for(int j=0;j<getRoomContents().length;j++){
                  if(getRoomContents()[j].getItemId() == (i-2))
                      thePlayer.pickUp(getRoomContents()[j]);
              }
          }
      }
  }

  //method that returns the locations of the items when
  //the game is first created. List will be used to save
  //game, so must be updated when items move
  private void initializeSaveItemList(int lvl){
      saveItemList = new ArrayList<Integer>();
      if(lvl == 1){
      saveItemList.add(6);//add 'theKey' room#
      saveItemList.add(3);//add 'wrongKey' room#
      saveItemList.add(7);//add 'flashlight' room#
      saveItemList.add(11);//add 'theTreasure' room#
    }
    else if (lvl == 0){
      saveItemList.add(11);//add 'theTreasure' room#
      saveItemList.add(6);//add 'theKey' room#
    }
    else return;
  }

    //method that returns the locations of the items when
    //the game is first LOADED FROM A SAVE. List will be used to save
    //game, so must be updated when items move
    private void initializeLoadItemList(ArrayList<Integer> loadList){
        //initialize saveItemList
        saveItemList = new ArrayList<Integer>();
        //load data from saved game into list
        for(int i = 2; i < loadList.size(); i++)
            saveItemList.add(loadList.get(i));
    }

    //method called from the constructor
    //calls the single level constructors depending on the
    //state of 'level' static variable
  private AdventureFactory getAdventureFactory() {
    if(level == 0)
        return new LevelZeroAdventureFactory();
    else
        return new LevelOneAdventureFactory();
  }

    //method to check whether the player has brought
    //the winning item outside
    //if so: increments level and returns true
    //else returns false
  public boolean levelComplete(){
    if(thePlayer.haveItem(adventureFactory.getWinningItem()) && thePlayer.getLoc().getRoomNum() == 0){
      level = (level+1) % 2;
      return true;}
    else
      return false;
  }

  //ALL 'go' methods simply use numbers corresponding to directions in original code
  //contained in AdventureGame.java
  public String goUp(){return thePlayer.go(4);}

  public String goDown(){
	  return thePlayer.go(5);
    }

  public String goNorth(){
	  return thePlayer.go(0);
    }
      
  public String goSouth(){
	  return thePlayer.go(1);
    }

  public String goEast(){
	  return thePlayer.go(2);
    }
      
  public String goWest(){
	  return thePlayer.go(3);
    }

  public String getView(){ return thePlayer.look();}

  //new grab method to also update itemlist used for saving
  //as well as grab item from room
  public String grabItem(Item item){
	  if (thePlayer.handsFull())
		  return "Your hands are full.";
	  else {
		 thePlayer.pickUp(item);
          saveItemList.set(item.getItemId(), -1);
		 (thePlayer.getLoc()).removeItem(item);
		 return "Item picked up.";
		}
  }

  //new drop method to also update itemlist used for saving
  //as well as drop item into room
  public String dropItem(int itemToToss){
    if(thePlayer.handsEmpty())
			return "You have nothing to drop.";
    else {
        //update saveItemList
        Item droppingItem = thePlayer.getItems()[itemToToss-1];
        int currRoom = thePlayer.getPlayerRoomNum();
        saveItemList.set(droppingItem.getItemId(), currRoom);
          thePlayer.drop(itemToToss);
          return "Item dropped.";
    }
  }
  
  public Item[] getRoomContents(){
	  return thePlayer.getLoc().getRoomContents();
  }
  
  public Item[] getPlayerItems(){
	  return thePlayer.getItems();
  }

  public boolean roomEmpty(){return thePlayer.getLoc().roomEmpty();}

  public int getPlayerNumOfItemsCarried(){
	  return thePlayer.numItemsCarried();
  }

  //The following methods aid in testing and should not be called from AdventureGameView.java
  public Room getRoom(){ return thePlayer.getLoc(); }

  public int getPlayerRoomNum(){
    return thePlayer.getLoc().getRoomNum();
  }

  public Player getPlayer(){
	  return thePlayer;
  }

  public int getLevel() { return  level;}

  public ArrayList<Integer> getSaveItemList(){
    return saveItemList;
  }

   //method to check whether the player is currently
   //carrying a flashlight obj
  public ArrayList<Integer> useFlashLight(){
    //get vars
    boolean hasFlashLight = false;
    FlashLight f = new FlashLight();
    Item[] playerItems = thePlayer.getItems();

    // Loop through the player's items to check if they do have a flashlight
    for(int i = 0; i < playerItems.length; i++){

      if(playerItems[i] instanceof FlashLight) {
        hasFlashLight = true;
        f = (FlashLight)playerItems[i];
      }

    }

    // If the player does have a FlashLight, call checkRoom and return that list
    if(hasFlashLight){
        return f.checkRoom(thePlayer.getLoc());
    }
    //if the player DOESN'T havea FlashLight, dont call checkRoom and return an empty list
    return new ArrayList<Integer>();

  }


}
