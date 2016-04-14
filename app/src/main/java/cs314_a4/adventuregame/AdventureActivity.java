package cs314_a4.adventuregame;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

import cs314_A3.AdventureGameModelFacade;
import cs314_A3.Item;
import cs314_A3.LevelOneAdventureFactory;


public class AdventureActivity extends Activity {

    private AdventureGameModelFacade model;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //initialize connection to model and set inital view

        model = new AdventureGameModelFacade();


        TextView myView = (TextView) findViewById(R.id.roomView);
        myView.setText("\nExplore the cave system and see what you can find.\nDon't get lost! \n\n"
                            + model.getView());
    }

 // This method is called at button click because we assigned the name to the
 	// "On Click property" of the button
 	public void myClickHandler(View view) {
        TextView myView = (TextView) findViewById(R.id.roomView);
        String actionResult = "";

        switch (view.getId()) {
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
    // updates info displayed in GUI any time a button is pushed
    private void displayCurrentInfo(String result){
        TextView myView = (TextView) findViewById(R.id.roomView);
        String myViewStr = "\n" + model.getView() + "\n\n" + result;
        if(!model.roomEmpty()) myViewStr = myViewStr + "\nThe room contains";
        myView.setText(myViewStr);

        updateRoomItems();
        updateUserItems();
    }

    //updates room items comboBox to reflect what's currently in the room
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
        else return model.dropItem(itemNum+1);
    }

}