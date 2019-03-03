package com.mygdx.game.Shooting;

import com.badlogic.gdx.physics.bullet.Bullet;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.EntityList;
import com.mygdx.game.entities.Player;

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
     * Compares a given bullet list and a given entity list
     * @param bulletList
     * @param entityList
     */
    public void handleBullet(BulletList bulletList, EntityList entityList) {

        for (Entity entity : entityList.getEntities()) {
            for (AbstractBullet bullet : BulletList.getBulletList().getBullets()) {

                //AI for the bullets
                if(bullet.bulletType == BulletType.ALIEN){
                    bullet.act();
                }

                // Handles bullets shot by aliens that hit the player
                // IF the bullet is an Alien bullet (shot by an alien)
                // AND the bullet has collided with the player
                // Reduce player health
                if(isAlienBullet(bullet) == true && bulletHasCollided(EntityList.getEntityList().getPlayer(), bullet)){
                    EntityList.getEntityList().getPlayer().reduceHealth(15);
                }

                // This handles the player bullets that will hit the enemies
                // So the player cannot shoot themselves
                if (entity.getType().getId().equals("player") == false) {

                    // Checks if a bullet has collided with an entity
                    if (bulletHasCollided(entity, bullet) == true) {

                        // Ensures that aliens cannot shoot each other
                        if(isEnemy(entity) == true && isAlienBullet(bullet) == true){

                        }
                        else {

                            // Checks what type the entity is to alter humanity
                            entity.reduceHealth(bullet.getBulletDamage());

                            //This is necessary so entities are only affected by bullets once
                            bullet.setDamage(0);

                            // Reduce humanity when a friendly is hit
                            if (entity.getType().equals("friendly")) {
                                EntityList.getEntityList().getPlayer().decreaseHumanity(5);
                            }

                            // If the entity is dead add it to a dead entity list
                            if (entity.getHealth() <= 0) {
                                deadEntities.add(entity);

                                // If the entity is dead and an enemy then increase player humanity
                                if (isEnemy(entity) == true) {
                                    EntityList.getEntityList().getPlayer().increaseHumanity(15);
                                }
                            }
                            bullet.updateRemove();
                        }
                    }
                }
            }
        }
        removeDeadEntities();
    }

    //TODO
    // The aliens can shoot themselves
    // Need to handle this (IE make the bullets)
    //

    /**
     * Remove all the entities in the dead list from the EntityList
     */
    public void removeDeadEntities(){
        for(Entity entity : deadEntities){
            entity.dispose();
            EntityList.getEntityList().addToRemoval(entity);
        }
    }

    /**
     * Returns true if a given entity is of type enemy
     * @param entity
     * @return boolean
     */
    private boolean isEnemy(Entity entity){

        if(entity.getType().equals("enemy")){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Returns true if the given bullet is of type alien (shot by an alien)
     * @param bullet
     * @return
     */
    private boolean isAlienBullet(AbstractBullet bullet){
        if(bullet.bulletType.getId().equals("alien")){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Returns true when the hit box of a given bullet overlaps the hitbox of a given entity
     * @param entity
     * @param bullet
     * @return boolean
     */
    private boolean bulletHasCollided(Entity entity, AbstractBullet bullet){

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
                && bullet.getBulletY() + bullet.getHeight() < entity.gety() + entity.getHeight()){

            return true;

        }
        else{
            return false;
        }
    }
}

