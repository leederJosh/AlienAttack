package com.mygdx.game.Shooting;

import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.EntityList;

/**
 * Handles bullet collision between bullets and entities
 * @Author Josh Leeder
 * @Date 19/02/19
 */
public class ShootingHandler {

    //To Do
    // Get all the bullets in the current bullet list
    // Compare the x and y plus the width and height to each entity
    // If the bullet is over the entity then do things (will depend on the entity type and stuff)

    /**
     * Handles what happens when a bullet collides with an entity
     * @param bulletList
     * @param entityList
     */
    public void handleBullet(BulletList bulletList, EntityList entityList){


        for(AbstractBullet bullet: bulletList.getBullets()){
            for(Entity entity: entityList.getEntities()) {



                if (entity.getType().getId().equals("player") == false) {

                    /**
                     * The problems are that the bullet spawns and despawns imediately
                     * Like it's hitting the player and despawning or something
                     * The bullet shoots and moves as normal if we comment out the removeBullet method
                     */
                    // Checks the collisions to the left of the player
//                    if (bullet.getBulletX() == (entity.getx() + entity.getWidth())){
//                        //Reduces the health of the entity
//                        entity.reduceHealth(bullet.getBulletDamage());
//                        System.out.print("Before bullet removed\n");
//                        bullet.updateRemove();
//                        System.out.print("Reducing health\n");
//                        // Need to handle humanity and stuff here too
//                    }

                    //Checks the right of the player
                   if(((bullet.getBulletX()+bullet.getWidth())>=entity.getx())&&(((bullet.getBulletY()+bullet.getHeight())>=entity.gety()))||(bullet.getBulletY()<=(entity.gety()+entity.getHeight()))){
                       //entity.reduceHealth(bullet.getBulletDamage());
                        System.out.print("Before bullet removed\n");
                        //bullet.updateRemove();
                        System.out.print("Reducing health\n");
                   }
                }
            }
            }

        }

    }

