package cs314_A3;

/**
 * Created by eokvath on 4/17/16.
 */
public class FlashLight extends Item{

    public int[] checkRoom(Room playerLocation){
    // Array of int that indicate whether the player is able to go that direction (ableToGoDir) / whether that direction is a door
    int[] ableToGoDir = new int[6];

        for(int i = 0; i<6; i++){

            // checks the sides of the room at the player location
            // If the side is a Wall then the ableToGoDir is 0.
            // If the side is an opening then ableToGoDir is 1
            // If the side is a Door then ableToGoDir is 2
            if(playerLocation.getSide(i) instanceof Wall){
            ableToGoDir[i] = 0;
            }else if(playerLocation.getSide(i) instanceof Door){
                ableToGoDir[i] = 2;
            }else{
                ableToGoDir[i] = 1;
            }

        }

        return ableToGoDir;

    }


}
