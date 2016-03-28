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

        switch (view.getId()) {
            case R.id.goUp:
                model.goUp();
                myView.setText(model.getView());
                break;
            case R.id.goDown:
                model.goDown();
                myView.setText(model.getView());
                break;
            case R.id.goNorth:
                model.goNorth();
                myView.setText(model.getView());
                break;
            case R.id.goSouth:
                model.goSouth();
                myView.setText(model.getView());
                break;
            case R.id.goEast:
                model.goEast();
                myView.setText(model.getView());
                break;
            case R.id.goWest:
                model.goWest();
                myView.setText(model.getView());
                break;
            case R.id.drop:

                myItems.setText(model.showItems());
                break;
            case R.id.grab:

                myItems.setText(model.showItems());
                break;
        }
    }

}