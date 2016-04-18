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
	private Player thePlayer;
	private Room startRm;
    private AdventureFactory adventureFactory;
    private static int level;
    private ArrayList<Integer> saveItemList;

  public AdventureGameModelFacade(int level) {
      this.level = level;
      initializeSaveItemList(level);
      adventureFactory = getAdventureFactory();
	  thePlayer = new Player();
	  startRm = adventureFactory.createAdventure();
	  thePlayer.setRoom(startRm);
  }

  public AdventureGameModelFacade(ArrayList<Integer> savedGameState) {
        level = savedGameState.get(0);
        initializeSaveItemList(level);
        adventureFactory = getAdventureFactory();
        thePlayer = new Player();
        startRm = adventureFactory.createSavedAdventure(savedGameState);
        thePlayer.setRoom(startRm);
        playerPickUpInitialItems(savedGameState);
    }

  private void playerPickUpInitialItems(ArrayList<Integer> savedGameState){
      for(int i=2;i<savedGameState.size();i++){
          if(savedGameState.get(i) == -1)
              thePlayer.pickUp(getRoomContents()[0]);
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

  private AdventureFactory getAdventureFactory() {
    if(level == 0)
        return new LevelZeroAdventureFactory();
    else
        return new LevelOneAdventureFactory();
  }

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

  public String getView(){ 
     return thePlayer.look();
     }

  public String grabItem(Item item){
	  if (thePlayer.handsFull())
		  return "Your hands are full.";
	  else {
		 thePlayer.pickUp(item);
         //update saveItemList
         saveItemList.set(item.getItemId(), -1);
        }
		 (thePlayer.getLoc()).removeItem(item);
		 return "Item picked up.";
     }

  
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

  public ArrayList<Integer> useFlashLight(){

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

    // If the player does have a FlashLight, call checkRoom and return that list, if not then return an empty list
    if(hasFlashLight){return f.checkRoom(thePlayer.getLoc());}

    return new ArrayList<Integer>();

  }


}
