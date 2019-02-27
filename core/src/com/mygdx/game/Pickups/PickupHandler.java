package com.mygdx.game.Pickups;

import java.util.ArrayList;

/**
 * Handles the collision with pickups and the actions to take when collisions occure
 * @author Josh Leeder
 * @date 1/02/19
 */
public class PickupHandler {

    /** A list of the pickups on the screen */
    private ArrayList<AbstractPickup> activePickups;

    /** A list of pickups to remove */
    private ArrayList<AbstractPickup> pickupsToRemove;


    public PickupHandler() {
        activePickups = new ArrayList<AbstractPickup>();
        pickupsToRemove = new ArrayList<AbstractPickup>();
    }

    /**
     * Add to active pickups list
     * @param pickup
     */
    public void addPickUp(AbstractPickup pickup){
        activePickups.add(pickup);
    }

    /**
     * Add a pickup to the list for removal
     * @param pickup
     */
    public void pickupRemoval(AbstractPickup pickup){
        pickupsToRemove.add(pickup);
    }

    /**
     * Clears the pickups appropriate to remove from the active pickups list
     */
    public void clearPickups(){
        for(AbstractPickup pickup: pickupsToRemove){
            activePickups.remove(pickup);
        }
        pickupsToRemove.clear();
    }

    public ArrayList<AbstractPickup> getActivePickups(){
        return activePickups;
    }

    //Will need some for of collision detection here where we iterate through the active list and test hte x and ys of all of the pickups
    // againsts the player entity in position 0 of the entity list
    // Then do some action like pickup.act when that does occur
    // THis will allow it to be general for all pickups
}
