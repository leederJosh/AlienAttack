package com.mygdx.game.pickups;
import com.mygdx.game.game.AlienGame;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.EntityList;

import java.util.ArrayList;
import static com.mygdx.game.pickups.AbstractPickup.gen;

/**
 * Handles the collision with pickups and the actions to take when collisions occur
 * @author Josh Leeder
 * @date 1/02/19
 */
public class PickupHandler {


    /** A list of the pickups on the screen */
    private ArrayList<AbstractPickup> activePickups;

    /** A list of pickups to remove */
    private ArrayList<AbstractPickup> pickupsToRemove;

    /** Whether a pickup should drop or not */
    protected boolean toDrop;

    public void setToDrop(boolean toDrop) {
        this.toDrop = toDrop;
    }

   public boolean hasCollided;


    public PickupHandler() {
        activePickups = new ArrayList<AbstractPickup>();
        pickupsToRemove = new ArrayList<AbstractPickup>();
    }


    /**
     * Detects when a player walks over a pickup and calls the appropriate act method from the pickup
     */
    public boolean hasCollidedWithPickUp() {


        for (AbstractPickup pickup : activePickups) {
            hasCollided = false;
            //Pickup variables to use
            float pickupWidth = pickup.getWIDTH() / AlienGame.ppm;
            float pickupHeight = pickup.getHEIGHT()/ AlienGame.ppm;
            float pickupX = pickup.getPickupX();
            float pickupY = pickup.getPickupY();

            //Entity variables to use
            float playerWidth = EntityList.getEntityList().getPlayer().getWidth();
            float playerHeight = EntityList.getEntityList().getPlayer().getHeight();
            float playerX = EntityList.getEntityList().getPlayer().getx();
            float playerY = EntityList.getEntityList().getPlayer().gety();

            //Hit boxes (mostly to make the IFs easier to read
            float playerXHitBox = playerX + playerWidth;
            float playerYHitBox = playerY + playerHeight;
            float pickupXHitBox = pickupX + pickupWidth;
            float pickupYHitBox = pickupY + pickupHeight;

            // Shoot to the right
            if (pickupXHitBox > playerX && pickupXHitBox < playerXHitBox) {

                // Shoot up
                if (pickupYHitBox > playerY && pickupYHitBox < playerYHitBox) {

                    hasCollided = true;
                }
                // Shoot down
                else if (playerY < playerYHitBox && pickupY > playerY) {

                    hasCollided = true;
                }

            }
            //Shoot to the left
            else if (pickupX < playerXHitBox && pickupX > playerX) {

                // Shoot up
                if (pickupYHitBox > playerY && pickupYHitBox < playerYHitBox) {

                    hasCollided = true;
                }
                // Shoot down
                else if (pickupY < playerYHitBox && pickupY > playerY) {

                    hasCollided = true;
                }
            }

            // If the player has collided with a pickup this will add the effect to player
            if (hasCollided == true){
                pickup.act();
                pickup.setPickupValue(0);
                addPickUpsToRemove(pickup);

            }
        }


        clearPickups();
        return hasCollided;
    }

    /**
     * Add a pickup to the list for removal
     * @param pickup
     */
    private void addPickUpsToRemove(AbstractPickup pickup) {
        pickupsToRemove.add(pickup);
    }

    /**
     * Clears the pickups appropriate to remove from the active pickups list
     */
    public void clearPickups() {
        for (AbstractPickup pickup : pickupsToRemove) {
            activePickups.remove(pickup);

        }
        pickupsToRemove.clear();

    }

    /**
     * Returns a list of all pickups on the map
     */
    public ArrayList<AbstractPickup> getActivePickups() {
        return activePickups;
    }

    /**
     * The random chance that the pickup will drop
     * @return toDrop
     */
    private boolean dropItem() {

        toDrop = false;
        // 1 in 4 chance to drop the pickup
        // The next int is between 0 and n where n is exclusive
        if (gen.nextInt(4) == 3) {
            toDrop = true;
        }
        return toDrop;
    }

    /**
     * Randomly decided which item to drop
     * @return
     */
    public int pickupToDrop() {
        //At the moment this is 0 to always spawn a health pickup
        return gen.nextInt(3);

    }

    /**
     * Add to active pickups list
     * @param entity
     */
    public void addPickUp(Entity entity) {

        if (dropItem() == true) {

            //IF pickupToDrop returns 0 then spawn a HEALTH pickup
            if (pickupToDrop() == 0) {
                activePickups.add(new HealthPickup(entity.getx(), entity.gety()));
            }

            if (pickupToDrop() == 1){
                activePickups.add(new CluePickup(entity.getx(), entity.gety()));
            }

          //  if (pickupToDrop() == 2){
          //      activePickups.add(new WeaponPickup(entity.getx(), entity.gety()));
            }


        }

    }



