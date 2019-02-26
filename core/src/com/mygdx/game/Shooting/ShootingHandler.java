package com.mygdx.game.Shooting;

import com.mygdx.game.Pickkups.AbstractPickup;
import com.mygdx.game.Pickkups.HealthPickup;
import com.mygdx.game.entities.Enemy;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.EntityList;
import com.mygdx.game.entities.Friendly;

import java.util.ArrayList;

/**
 * Handles bullet collision between bullets and entities
 * @Author Josh Leeder
 * @Date 19/02/19
 */
public class ShootingHandler {

    /** Holds the entities to remove from the Entity List */
    private ArrayList<Entity> deadEntities;

    public ShootingHandler(){
        deadEntities = new ArrayList<Entity>();
    }

    /**
     * Handles what happens when a bullet collides with an entity
     *
     * @param bulletList
     * @param entityList
     */
    public void handleBullet(BulletList bulletList, EntityList entityList) {


        for (Entity entity : entityList.getEntities()) {
            for (AbstractBullet bullet : bulletList.getBullets()) {
                if(bullet.bulletType == BulletType.ALIEN){
                    bullet.act();
                }
                    if (entity.getType().getId().equals("player") == false && entity.getx() > bullet.getBulletX()) {

                        /**
                         * It seems to be looping through 2 times for every entity to the left of the player
                         */
                        // Shooting in all directions
                        // Now this is confusing so here is a breakdown:
                        // Check the bullets X plus bullet width is greater than the X of the entity / 2 (so it hits the middle of the entity in all directions)
                        // AND
                        // check the bullet Y plus the bullet height is greater than the entity Y
                        // AND
                        // Check the bullet Y plus bullet height is less than the entity Y plus entity height
                        // This is to effectively draw a box around the entity using it's height, it's Y and it's X
                        // This means that if the bullet hits in this box collision will occur, if not then no collision
                        // Without the last part it registers a hit if a bullet travels anywhere above the entity
                        if (bullet.getBulletX() + bullet.getWidth() > entity.getx() + entity.getWidth() / 2
                                && bullet.getBulletY() + bullet.getHeight() > entity.gety()
                                && bullet.getBulletY() + bullet.getHeight() < entity.gety() + entity.getHeight()) {


                            System.out.print("\nHealth before " + entity.getHealth());
                            entity.reduceHealth(bullet.getBulletDamage());
                            //This is necessary so entities are only affected by bullets once
                            bullet.setDamage(0);

                            /** Things that need to be added here:
                             * Handling the drop in humanity depending on the entity type
                             * How are we going to get the player here? Use index 0 of the list you passed in?
                             * There is a bug with how the entities despawn on death
                             * They leave a black rectangle that only goes away after clicking so it is linked to when this method is called and how often
                             */

                            if(entity.getHealth() <= 0){
                                deadEntities.add(entity);
                            }
                            System.out.print("\nHealth After " + entity.getHealth());
                            bullet.updateRemove();
                        }
                    }
            }
        }
        removeDeadEntities();
    }

    /**
     * Remove all the entities in the dead list from the EntityList
     */
    public void removeDeadEntities(){
        for(Entity entity : deadEntities){
            entity.dispose();
            EntityList.getEntityList().removeDeadEntity(entity);
        }
    }
}

