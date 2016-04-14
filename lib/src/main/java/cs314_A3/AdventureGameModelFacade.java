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

public class AdventureGameModelFacade {
	private Player thePlayer;
	private Room startRm;
    private AdventureFactory adventureFactory;
    private static int level = 0;

  public AdventureGameModelFacade() {
      adventureFactory = getAdventureFactory();
	  thePlayer = new Player();
	  startRm = adventureFactory.createAdventure();
	  thePlayer.setRoom(startRm);
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
		 (thePlayer.getLoc()).removeItem(item);
		 return "Item picked up.";
		}
  }
  
  public String dropItem(int itemToToss){
	  	if(thePlayer.handsEmpty())
			return "You have nothing to drop.";
		else {
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
  public Room getRoom(){
	  return thePlayer.getLoc();
  }

  public Player getPlayer(){
	  return thePlayer;
  }
  
}
