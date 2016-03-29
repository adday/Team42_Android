package cs314_a4.adventuregame;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import cs314_A3.AdventureGameModelFacade;



public class AdventureActivity extends Activity {

    private AdventureGameModelFacade model;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //initialize connection to model
        model = new AdventureGameModelFacade();
        String viewText = model.getView();

        // Get initial room view, and see my items.
        TextView myView = (TextView) findViewById(R.id.roomView);
        myView.setText(viewText);

        TextView myItems = (TextView) findViewById(R.id.myItems);
        myItems.setText(model.showItems());


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
                myItems.setText(model.showItems());
                break;
            case R.id.grab:
                myItems.setText(model.showItems());
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
        updateUserItems();
    }

    //updates room items comboBox to reflect what's currently in the room
    @SuppressWarnings("unchecked")
    private void updateRoomItems(){
        roomItems.removeAllItems();
        Item[] itemList = model.getRoomContents();
        for(int i = 0; i < itemList.length; i++)
            roomItems.addItem(itemList[i].getDesc());
    }

    //updates user items comboBox to reflect what's currently in the user's inventory
    @SuppressWarnings("unchecked")
    private void updateUserItems(){
        userItems.removeAllItems();
        Item[] itemList = model.getPlayerItems();
        int numItems = model.getPlayerNumOfItemsCarried();
        for(int i = 0; i < numItems; i++)
            userItems.addItem(itemList[i].getDesc());
    }


    private String grab() {
        //get items currently in room and which item the user selected
        Item[] itemList = model.getRoomContents();
        int itemNum = roomItems.getSelectedIndex();

        //check if room has anything in it to grab
        //itemNum == -1 if room is empty
        if(itemNum < 0) return "The room is empty.";
        else return model.grabItem(itemList[itemNum]);
    }

    private String drop() {
        //  combobox index because dropItem takes an int for item array index
        //  add 1 to account for 1-based indexing
        return model.dropItem(userItems.getSelectedIndex()+1);

    }

}