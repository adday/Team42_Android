package cs314_a4.adventuregame;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import cs314_A3.AdventureGameModelFacade;
import cs314_A3.Item;


public class AdventureActivity extends Activity {

    private AdventureGameModelFacade model;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //initialize connection to model
        model = new AdventureGameModelFacade();

        displayCurrentInfo("");
    }

 // This method is called at button click because we assigned the name to the
 	// "On Click property" of the button
 	public void myClickHandler(View view) {
        TextView myView = (TextView) findViewById(R.id.roomView);
        TextView myItems = (TextView) findViewById(R.id.myItems);
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
        myView.setText(model.getView() + '\n' + result);

        TextView myItems = (TextView) findViewById(R.id.myItems);
        myItems.setText(model.showItems());

        updateRoomItems();
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


    private String grab() {
        ListView roomItemsList = (ListView) findViewById(R.id.roomItemSelector);

        //get items currently in room and which item the user selected
        Item[] itemList = model.getRoomContents();
        int itemNum = roomItemsList.getCheckedItemPosition();

        //check if room has anything in it to grab
        //itemNum == -1 if room is empty
        if(itemNum < 0) return "The room is empty.";
        else return model.grabItem(itemList[itemNum]);
    }

    private String drop() {
        int itemNum;
        ToggleButton itemSelector = (ToggleButton) findViewById(R.id.playerItemSelector);
        if(itemSelector.isChecked())itemNum = 2;
        else itemNum = 1;
        return model.dropItem(itemNum);
    }

}