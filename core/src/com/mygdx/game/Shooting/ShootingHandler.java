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

    /**
     * Holds the entities to remove from the Entity List
     */
    private ArrayList<Entity> deadEntities;

    public ShootingHandler() {
        deadEntities = new ArrayList<Entity>();
    }

    /**
     * Handles what happens when a bullet collides with an entity
     * Compares a given bullet list and a given entity list
     * Returns true if an entity has died
     *
     * @param entity
     */
    public boolean handleBullet(Entity entity) {

        boolean entityHasDied = false;

        for (AbstractBullet bullet : BulletList.getBulletList().getBullets()) {

            //AI for enemy bullets
            if(bullet.bulletType == BulletType.ALIEN){
                bullet.act();
            }

            // Handles bullets shot by aliens that hit the player
            // IF the bullet is an Alien bullet (shot by an alien)
            // AND the bullet has collided with the player
            // Reduce player health
            if(isAlienBullet(bullet) == true && bulletHasCollided(EntityList.getEntityList().getPlayer(), bullet) == true){
                EntityList.getEntityList().getPlayer().reduceHealth(15);
                bullet.setDamage(0);
                bullet.updateRemove();
            }
            else if(isAlienBullet(bullet) == false && bulletHasCollided(entity, bullet) && entity.getType().getId().equals("player") == false){

                // This handles the player bullets that will hit the enemies
                // So the player cannot shoot themselves
                if (entity.getType().getId().equals("player") == false) {

                    // Checks if a bullet has collided with an entity
                    if (bulletHasCollided(entity, bullet) == true) {

                        // Ensures that aliens cannot shoot each other
                        if (entity.getType().getId().equals("enemy") && isAlienBullet(bullet) == true) {

                        } else {

                            // Checks what type the entity is to alter humanity
                            entity.reduceHealth(bullet.getBulletDamage());

                            //This is necessary so entities are only affected by bullets once
                            bullet.setDamage(0);

                            // Reduce humanity when a friendly is hit
                            if (entity.getType().equals("friendly")) {
                                EntityList.getEntityList().getPlayer().getHumanity().decreaseHumanity(5);
                            }

                            // If the entity is dead add it to a dead entity list
                            if (entity.getHealth() <= 0) {

                                // If the entity is dead and an enemy then increase player humanity
                                if (isEnemy(entity) == true) {
                                    EntityList.getEntityList().getPlayer().getHumanity().decreaseHumanity(15);
                                }

                                deadEntities.add(entity);
                                entityHasDied = true;
                            }
                            bullet.updateRemove();
                        }
                    }
                }
            }
        }
        removeDeadEntities();
        return entityHasDied;
    }

    //TODO
    // The aliens can shoot themselves
    // Need to handle this (IE make the bullets)
    //

    /**
     * Remove all the entities in the dead list from the EntityList
     */
    public void removeDeadEntities() {
        for (Entity entity : deadEntities) {
            entity.dispose();
            EntityList.getEntityList().addToRemoval(entity);
        }
    }

    /**
     * Returns true if a given entity is of type enemy
     *
     * @param entity
     * @return boolean
     */
    private boolean isEnemy(Entity entity) {

        if (entity.getType().equals("enemy")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns true if the given bullet is of type alien (shot by an alien)
     *
     * @param bullet
     * @return
     */
    private boolean isAlienBullet(AbstractBullet bullet) {
        if (bullet.bulletType.getId().equals("alien")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns true when the hit box of a given bullet overlaps the hitbox of a given entity
     *
     * @param entity
     * @param bullet
     * @return boolean
     */
    private boolean bulletHasCollided(Entity entity, AbstractBullet bullet) {

        boolean hasCollided = false;

        //Bullet variables to use
        float bulletWidth = bullet.getWidth();
        float bulletHeight = bullet.getHeight();
        float bulletX = bullet.getBulletX();
        float bulletY = bullet.getBulletY();

        //Entity variables to use
        float entityWidth = entity.getWidth();
        float entityHeight = entity.getHeight();
        float entityX = entity.getx();
        float entityY = entity.gety();

        //Hit boxes (mostly to make the IFs easier to read
        float entityXHitBox = entityX + entityWidth;
        float entityYHitBox = entityY + entityHeight;
        float bulletXHitBox = bulletX + bulletWidth;
        float bulletYHitBox = bulletY + bulletHeight;

        // Shoot to the right
        if (bulletXHitBox > entityX && bulletXHitBox < entityXHitBox) {

            // Shoot up
            if (bulletYHitBox > entityY && bulletYHitBox < entityYHitBox) {

                hasCollided = true;
            }
            // Shoot down
            else if (bulletY < entityYHitBox && bulletY > entityY) {

                hasCollided = true;
            }

        }
        //Shoot to the left
        else if (bulletX < entityXHitBox && bulletX > entityX) {

            // Shoot up
            if (bulletYHitBox > entityY && bulletYHitBox < entityYHitBox) {

                hasCollided = true;
            }
            // Shoot down
            else if (bulletY < entityYHitBox && bulletY > entityY) {

                hasCollided = true;
            }
        }

        return hasCollided;
    }
}
