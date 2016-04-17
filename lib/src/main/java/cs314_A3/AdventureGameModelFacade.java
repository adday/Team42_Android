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
    private static int level = 0;
    private ArrayList<Integer> saveItemList;

  public AdventureGameModelFacade() {
      adventureFactory = getAdventureFactory();
	  thePlayer = new Player();
	  startRm = adventureFactory.createAdventure();
	  thePlayer.setRoom(startRm);
  }

  //method that returns the locations of the items when
  //the game is first created. List will be used to save
  //game, so must be updated when items move
  private void initializeSaveItemList(int lvl){
    if(lvl == 1){
      saveItemList.add(6);//add 'theKey' room#
      saveItemList.add(3);//add 'wrongKey' room#
      saveItemList.add(7);//add 'flashlight' room#
      saveItemList.add(11);//add 'theTreasure' room#
    }
    else if (lvl == 0){
      saveItemList.add(6);//add 'theKey' room#
      saveItemList.add(11);//add 'theTreasure' room#
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
    if(thePlayer.haveItem(adventureFactory.getWinningItem()) && thePlayer.getLoc() == startRm){
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

  public String showItems(){
     return thePlayer.showMyThings();
     }
  
  public String grabItem(Item item){
	  if (thePlayer.handsFull())
		  return "Your hands are full.";
	  else {
		 thePlayer.pickUp(item);
         //update saveItemList
         if(level == 0) {
            switch (item.getItemId()){
              case 0: saveItemList.set(0, -1); break;
              case 1: saveItemList.set(1, -1); break;
            }
         }
         else {
           switch (item.getItemId()) {
             case 0:
               saveItemList.set(0, -1);
               break;
             case 1:
               saveItemList.set(1, -1);
               break;
             case 2:
               saveItemList.set(2, -1);
               break;
             case 3:
               saveItemList.set(3, -1);
               break;
           }
         }//END SAVEITEMLIST UPDATING
		 (thePlayer.getLoc()).removeItem(item);
		 return "Item picked up.";
		}
  }
  
  public String dropItem(int itemToToss){
    if(thePlayer.handsEmpty())
			return "You have nothing to drop.";
    else {
        //update saveItemList
        Item droppingItem = thePlayer.getItems()[itemToToss];
        int currRoom = thePlayer.getPlayerRoomNum();
        if(level == 0) {
            switch (droppingItem.getItemId()){
              case 0: saveItemList.set(0, currRoom); break;
              case 1: saveItemList.set(1, currRoom); break;
            }
        }
        else {
            switch (droppingItem.getItemId()) {
                case 0:
                  saveItemList.set(0, currRoom);
                  break;
                case 1:
                  saveItemList.set(1, currRoom);
                  break;
                case 2:
                  saveItemList.set(2, currRoom);
                  break;
                case 3:
                  saveItemList.set(3, currRoom);
                  break;
            }
        }//END SAVEITEMLIST UPDATING
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

  public void updateSaveItemList(int index, int locNum){
    saveItemList.set(index, locNum);
  }

  public ArrayList<Integer> getSaveItemList(){
    return saveItemList;
  }
}
