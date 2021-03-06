package cs314_A3;

import java.util.ArrayList;

/**
 Adventure Game  Program Code
 filename: FlashLight.java
 purpose: contains code for flashlight item class
 Author: Created by eokvath on 4/17/16.
 **/

public class FlashLight extends Item{

    //method that checks what the current room's walls are.
    //returns an arraylist of integers pertaining to what the walls
    //of the room are
    public ArrayList<Integer> checkRoom(Room playerLocation){

    // ArrayList of ints that indicate whether the player is able to go that direction (ableToGoDir)
        ArrayList<Integer> ableToGoDir = new ArrayList<Integer>();

        for(int i = 0; i<6; i++){

            // checks the sides of the room at the player location
            // If the side is a Wall then the ableToGoDir is 0.
            // If the side is an opening then ableToGoDir is 1
            // If the side is a Door then ableToGoDir is 2
            if(playerLocation.getSide(i) instanceof Wall){
            ableToGoDir.add(0);
            }else if(playerLocation.getSide(i) instanceof Door){
                ableToGoDir.add(2);
            }else{
                ableToGoDir.add(1);
            }

        }

        return ableToGoDir;

    }


}
