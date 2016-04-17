package cs314_a4.adventuregame;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import cs314_A3.AdventureGameModelFacade;
import cs314_A3.Item;


public class AdventureActivity extends Activity {

    private AdventureGameModelFacade model;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
    }

    @Override
    public void onDestroy() {
        saveGame2();
        super.onDestroy();
    }


 // This method is called at button click because we assigned the name to the
 	// "On Click property" of the button
 	public void myClickHandler(View view) {
        String actionResult = "";

        switch (view.getId()) {
            case R.id.newLvl0Adventure:
                //initialize connection to model and set inital view
                model = new AdventureGameModelFacade(0);
                setContentView(R.layout.main);
                TextView myView = (TextView) findViewById(R.id.roomView);
                myView.setText("\n" + "Explore the cave system and see what you can find.\nDon't get lost! \n\n"
                        + model.getView());
                break;
            case R.id.newLvl1Adventure:
                //initialize connection to model and set inital view
                model = new AdventureGameModelFacade(1);
                setContentView(R.layout.main);
                TextView myView0 = (TextView) findViewById(R.id.roomView);
                myView0.setText("\n" + "Explore the cave system and see what you can find.\nDon't get lost! \n\n"
                        + model.getView());
                break;
            case R.id.savedAdventure:
                //initialize connection to model and set inital view
                model = new AdventureGameModelFacade(loadGame());
                setContentView(R.layout.main);
                TextView myView1 = (TextView) findViewById(R.id.roomView);
                myView1.setText("\n" + "Explore the cave system and see what you can find.\nDon't get lost! \n\n"
                        + model.getView());
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
        useFlashLight();
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

    private String drop() {
        ListView userItemsList = (ListView) findViewById(R.id.userItemSelector);
        int itemNum = userItemsList.getCheckedItemPosition();
        if(model.getPlayerNumOfItemsCarried() == 0) return "You have no items to drop.";
        else if(itemNum < 0) return "Select an item to drop.";
        else return model.dropItem(itemNum + 1);
    }

    private void useFlashLight(){
        ArrayList<Integer> ableToGoDir = model.useFlashLight();

        for(int i = 0; i < 6; i++){
            if(ableToGoDir.size() == 0) {
                setButtonColor(i, 3);
            }else
                setButtonColor(i, ableToGoDir.get(i));

        }

    }

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

    //fxn to save game state in internal storage
    //stores: lvl#, playerRoom#, & (item0 - itemN)room#
    public void saveGame(View view){
        String filename = "adventureSave.txt";
        String lvl = "level";
        String plyr = "player";
        //open file stream
        try {
            FileOutputStream outStream = openFileOutput(filename, MODE_PRIVATE);
            outStream.write(model.getLevel()); //write level out
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void saveGame2(){
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
        editor.commit();

    }

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


        return settingsList;
    }

}