package cs314_a4.adventuregame;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import cs314_A3.AdventureGameModelFacade;
import cs314_A3.Item;

/**
 Adventure Game  Program Code
 filename: AdventureActivity.java
 purpose: contains android GUI code that connects to the
    Adventure game model facade
 Authors:
     Adrion Q Arkenberg
     Alex Day
     Ed Okvath
 **/

public class AdventureActivity extends Activity {

    private AdventureGameModelFacade model;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
    }

    // called when back button is pressed
    /** Called when the activity goes off-screen. */
    @Override
    public void onBackPressed() {
        onStop();
        setContentView(R.layout.start);
    }

    //called when android is moved out from main view on device
    @Override
    public void onStop() {
        super.onStop();
        saveGame();
    }

    // handles click of instruction button from start.xml screen

    //method to handle the 'instructions' activity that's connected
    //to the corresponding button on the 'start' activity
    public void myInstructionHandler(View view) {
        if (view.getId() == R.id.instructions) // send from 'start' to 'instructions'
            setContentView(R.layout.instructions);
    }

    // This method is called at button click because we assigned the name to the
 	// "On Click property" of the button
 	public void myClickHandler(View view) {
        String actionResult = "";

        switch (view.getId()) {
            case R.id.Lvl0Adventure:
                //initialize connection to model and set inital view
                model = new AdventureGameModelFacade(0);
                setGameView();
                break;
            case R.id.Lvl1Adventure:
                //initialize connection to model and set inital view
                model = new AdventureGameModelFacade(1);
                setGameView();
                break;
            case R.id.savedAdventure:
                //initialize connection to model and set inital view
                model = new AdventureGameModelFacade(loadGame());
                setGameView();
                break;
            case R.id.goUp:
                actionResult = model.goUp();
                break;
            case R.id.goDown:
                actionResult = model.goDown();
                break;
            case R.id.goNorth:
                actionResult = model.goNorth();
                break;
            case R.id.goSouth:
                actionResult = model.goSouth();
                break;
            case R.id.goEast:
                actionResult = model.goEast();
                break;
            case R.id.goWest:
                actionResult = model.goWest();
                break;
            case R.id.drop:
                actionResult = drop();
                break;
            case R.id.grab:
                actionResult = grab();
                break;
        }
        displayCurrentInfo(actionResult);
    }

    // private methods

    // fxn that sets the initial text of the game,
    // and switches the view to the main game view
    private void setGameView(){
        setContentView(R.layout.main);
        TextView myView = (TextView) findViewById(R.id.roomView);
        myView.setText("\n" + "Explore the cave system and see what you can find.\nDon't get lost! \n\n"
                + model.getView());
    }

    // check if level is complete to reset GUI connection appropriately
    private String updateLevel(){
        if(model.levelComplete()){
            model = new AdventureGameModelFacade(model.getLevel());
            return "Level complete, onto a new adventure!\n\n";}
        else
            return "";
    }

    // updates info displayed in GUI any time a button is pushed
    private void displayCurrentInfo(String result){
        TextView myView = (TextView) findViewById(R.id.roomView);
        String myViewStr = "\n" + updateLevel() + model.getView() + "\n\n" + result;
        if(!model.roomEmpty()) myViewStr = myViewStr + "\nThe room contains";
        myView.setText(myViewStr);

        updateRoomItems();
        updateUserItems();
        useFlashLight(); //uses flashlight if held
    }

    // updates room items comboBox to reflect what's currently in the room
    @SuppressWarnings("unchecked")
    private void updateRoomItems(){
        ListView roomItemsList = (ListView) findViewById(R.id.roomItemSelector);

        Item[] itemList = model.getRoomContents();
        ArrayList<String> roomItems = new ArrayList<String>();
        for(int i = 0; i < itemList.length; i++)
            roomItems.add(itemList[i].getDesc());

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, roomItems);
        roomItemsList.setAdapter(itemsAdapter);
    }

    //updates room items comboBox to reflect what's currently in the room
    @SuppressWarnings("unchecked")
    private void updateUserItems(){
        ListView userItemsList = (ListView) findViewById(R.id.userItemSelector);

        Item[] itemList = model.getPlayerItems();
        ArrayList<String> playerItems = new ArrayList<String>();
        for(int i = 0; i < model.getPlayerNumOfItemsCarried(); i++)
                playerItems.add(itemList[i].getDesc());

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, playerItems);
        userItemsList.setAdapter(itemsAdapter);
    }

    //fxn that handles picking items out of room
    private String grab() {
        ListView roomItemsList = (ListView) findViewById(R.id.roomItemSelector);

        //get items currently in room and which item the user selected
        Item[] itemList = model.getRoomContents();
        int itemNum = roomItemsList.getCheckedItemPosition();

        //check if room has anything in it to grab
        //itemNum == -1 if room is empty
        if(itemList.length == 0) return "The room is empty.";
        else if(itemNum < 0) return "Select an item to grab.";
        else return model.grabItem(itemList[itemNum]);
    }

    //fxn that handles user dropping item into room
    private String drop() {
        ListView userItemsList = (ListView) findViewById(R.id.userItemSelector);
        int itemNum = userItemsList.getCheckedItemPosition();
        if(model.getPlayerNumOfItemsCarried() == 0) return "You have no items to drop.";
        else if(itemNum < 0) return "Select an item to drop.";
        else return model.dropItem(itemNum + 1);
    }

    //fxn checks if user is carrying the flashlight
    //if they are: changes button text to indicate path in cave
    //else: sets button text color to black (default)
    private void useFlashLight(){
        ArrayList<Integer> ableToGoDir = model.useFlashLight();

        // Checks if the player has the flashlight, if they do the direction button text colors will be set
        // colors that indicate if the direction has wall, opening or door.
        for(int i = 0; i < 6; i++){
            if(ableToGoDir.size() == 0) {
                setButtonColor(i, 3);
            }else
                setButtonColor(i, ableToGoDir.get(i));

        }

    }

    //auxiliary helper fxn for 'useFlashlight' fxn
    //changes the text color for a given button to the given color
    private void setButtonColor(int i, int colorIndicator){
        switch(i) {
            case 0:
                Button north = (Button) findViewById(R.id.goNorth);
                if (colorIndicator == 0) {
                    north.setTextColor(getResources().getColor(R.color.colorWall));
                } else if (colorIndicator == 1) {
                    north.setTextColor(getResources().getColor(R.color.colorRoom));
                } else if (colorIndicator == 2) {
                    north.setTextColor(getResources().getColor(R.color.colorDoor));
                }else if(colorIndicator == 3){
                    north.setTextColor(getResources().getColor(R.color.colorDefault));
                }
                break;
            case 1:
                Button south = (Button) findViewById(R.id.goSouth);
                if (colorIndicator == 0) {
                    south.setTextColor(getResources().getColor(R.color.colorWall));
                } else if (colorIndicator == 1) {
                    south.setTextColor(getResources().getColor(R.color.colorRoom));
                } else if (colorIndicator == 2) {
                    south.setTextColor(getResources().getColor(R.color.colorDoor));
                }else if(colorIndicator == 3){
                    south.setTextColor(getResources().getColor(R.color.colorDefault));
                }
                break;
            case 2:
                Button east = (Button) findViewById(R.id.goEast);
                if (colorIndicator == 0) {
                    east.setTextColor(getResources().getColor(R.color.colorWall));
                } else if (colorIndicator == 1) {
                    east.setTextColor(getResources().getColor(R.color.colorRoom));
                } else if (colorIndicator == 2) {
                    east.setTextColor(getResources().getColor(R.color.colorDoor));
                }else if(colorIndicator == 3){
                    east.setTextColor(getResources().getColor(R.color.colorDefault));
                }
                break;
            case 3:
                Button west = (Button) findViewById(R.id.goWest);
                if (colorIndicator == 0) {
                    west.setTextColor(getResources().getColor(R.color.colorWall));
                } else if (colorIndicator == 1) {
                    west.setTextColor(getResources().getColor(R.color.colorRoom));
                } else if (colorIndicator == 2) {
                    west.setTextColor(getResources().getColor(R.color.colorDoor));
                }else if(colorIndicator == 3){
                    west.setTextColor(getResources().getColor(R.color.colorDefault));
                }
                break;
            case 4:
                Button up = (Button) findViewById(R.id.goUp);
                if (colorIndicator == 0) {
                    up.setTextColor(getResources().getColor(R.color.colorWall));
                } else if (colorIndicator == 1) {
                    up.setTextColor(getResources().getColor(R.color.colorRoom));
                } else if (colorIndicator == 2) {
                    up.setTextColor(getResources().getColor(R.color.colorDoor));
                }else if(colorIndicator == 3){
                    up.setTextColor(getResources().getColor(R.color.colorDefault));
                }
                break;
            case 5:
                Button down = (Button) findViewById(R.id.goDown);
                if (colorIndicator == 0) {
                    down.setTextColor(getResources().getColor(R.color.colorWall));
                } else if (colorIndicator == 1) {
                    down.setTextColor(getResources().getColor(R.color.colorRoom));
                } else if (colorIndicator == 2) {
                    down.setTextColor(getResources().getColor(R.color.colorDoor));
                }else if(colorIndicator == 3){
                    down.setTextColor(getResources().getColor(R.color.colorDefault));
                }
                break;
        }
    }

    //fxn called when the game stops. [ie AUTOSAVES]
    /*
    SAVES: level#, playerRoom#, itemRoomNum# (foreach item in itemsOnLvl)
    ITEM ORDER:
        0: treasure, key
        1: key, wrongKey, flashlight, treasure
     */
    public void saveGame(){
        //get data to save
        int gameLvl = model.getLevel();
        int playerRoom = model.getPlayerRoomNum();
        //open sharedPref
        SharedPreferences saveFile = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = saveFile.edit(); //create preferences editor
        //store level
        editor.putInt(getString(R.string.lvl), gameLvl);
        //store player room
        editor.putInt(getString(R.string.player), playerRoom);

        //get lvl so itemList contents are known
        //get saveItemList
        ArrayList<Integer> items = model.getSaveItemList();

        if(gameLvl == 0){
            //add treasure
            editor.putInt(getString(R.string.treasure), items.get(0));
            //add key
            editor.putInt(getString(R.string.key), items.get(1));
        }else{
            //add key
            editor.putInt(getString(R.string.key), items.get(0));
            //add key
            editor.putInt(getString(R.string.wrongKey), items.get(1));
            //add treasure
            editor.putInt(getString(R.string.flash), items.get(2));
            //add treasure
            editor.putInt(getString(R.string.treasure), items.get(3));
        }

        //commit save
        editor.commit();
    }

    //fxn called when the game reloads.
    /*
    LOADS: level#, playerRoom#, itemRoomNum# (foreach item in itemsOnLvl)
    ITEM KEYS:
        0: Treasure, Key
        1: Key, WrongKey, Flashlight, Treasure
    RETURNS: an arraylist of ints containing all loaded data
    DEFAULT: returns items in their starting places for each level (if no file exists)
     */
    public ArrayList<Integer> loadGame(){
        ArrayList<Integer> settingsList = new ArrayList<Integer>();
        //open shared preferences
        SharedPreferences loadFile = this.getPreferences(Context.MODE_PRIVATE);
        //get lvl and playerRoom#, default to beginning of lvl0
        int gameLvl = loadFile.getInt(getString(R.string.lvl), 0);
        int playerRoom = loadFile.getInt(getString(R.string.player), 0);
        //add info to settings list
        settingsList.add(gameLvl);
        settingsList.add(playerRoom);
        //add item info to settings list
        if(gameLvl == 0){
            int keyRoom = loadFile.getInt(getString(R.string.key), 6);
            int treasureRoom = loadFile.getInt(getString(R.string.treasure), 11);
            //add treasure & add key
            settingsList.add(treasureRoom); //index 2
            settingsList.add(keyRoom); //index 3
        }else{
            int keyRoom = loadFile.getInt(getString(R.string.key), 5);
            int wrongKeyRoom = loadFile.getInt(getString(R.string.wrongKey), 3);
            int flashRoom = loadFile.getInt(getString(R.string.flash), 7);
            int treasureRoom = loadFile.getInt(getString(R.string.treasure), 11);
            //add treasure & add key
            settingsList.add(keyRoom); // index 2
            settingsList.add(wrongKeyRoom); //index 3
            settingsList.add(flashRoom); //index 4
            settingsList.add(treasureRoom); //index 5
        }

        //return arraylist of all items loaded
        return settingsList;
    }

}