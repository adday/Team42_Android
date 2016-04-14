package cs314_A3;

import java.util.Random;

/**
 * Created by eokvath on 4/14/16.
 */
public class Enemy {

    // For returning enemy name
    private String name = "MissingNo";

    // What the enemy will drop if defeated
    private Item itemDrop;

    private int health;

    // How much damage the enemy does per attack
    private int damage;

    private int accuracy;

    private Room location;


    public Enemy(String enemyName, Item item, int enemyHealth, int enemyDamage, int enemyAccuracy, Room enemyLocation, int Difficulty){

        name = enemyName;
        itemDrop = item;
        health = enemyHealth*Difficulty/2;
        damage = enemyDamage*Difficulty/2;
        accuracy = enemyAccuracy*Difficulty/2;
        location = enemyLocation;

        // To make sure the enemy is not always hitting the Player so it is not completely impossible
        if(accuracy >= 100){
            accuracy = 95;
        }

    }

    public String attack(Player p){

        Random rng = new Random();

        int hitOrMiss = rng.nextInt(100) + 1;

        // When the enemy attacks they have a chance at missing if the hitOrMiss variable is less than accuracy
        // This if statement should make it so the player hits accuracy% of the time. If it is a hit the health of the player
        // is reduced by the damage the enemy deals

        if(hitOrMiss <= accuracy){
            p.setHealth(p.getHealth() - damage);

            return "Player hit for " + damage + " damage\n";
        }

        return name + " missed\n";

    }

    public String getName(){

        return name;

    }

    public int getHealth(){

        return health;

    }

    public int getDamage(){

        return damage;

    }

    public int getAccuracy(){

        return accuracy;

    }

    public Room getLocation(){

        return location;

    }

    public void setHealth(int h){

        health = h;

    }

    public void setDamage(int d){

        damage = d;

    }

    public void setAccuracy(int a){

        accuracy = a;

    }

    public void setLocation(Room room){

        location = room;

    }

}
