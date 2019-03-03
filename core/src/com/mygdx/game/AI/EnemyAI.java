package com.mygdx.game.AI;

import com.mygdx.game.Shooting.*;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.EntityList;
import org.omg.CORBA.ORB;

import java.util.Random;

/**
 * Enemy character behaviours
 * @Author Josh Leeder
 * @Date 27/02/19
 */
public class EnemyAI extends AI {

    /** How far the enemy can be away in the X from the player to shoot a bullet */
    private int xBoundary;
    /** How far the enemy can be away in the Y from the player to shoot a bullet */
    private int yBoundary;
    /** The chance the enemy has of shooting, if you set this to 4 the chance is 1 in 5 as the generator includes 0 */
    private  int shootingChance;

    public EnemyAI(){
        xBoundary = 125;
        yBoundary = 125;
        shootingChance = 60;
    }

    @Override
    public void moveRight() {

    }

    @Override
    public void moveLeft() {

    }

    /**
     * Spawns a bullet that is aimed towards the player
     * @param entity
     */
    @Override
    public void act(Entity entity) {

        //Gets all the variables this methods needs
        float entityX = entity.getx();
        float entityY = entity.gety();
        float playerX = EntityList.getEntityList().getPlayer().getx();
        float playerY = EntityList.getEntityList().getPlayer().gety();
        int direction = 15; //Spawn the bullet to the right of the entity by default
        Random generator = new Random();

        if(entity.getType().equals("player") == false){

        }
        // Breakdown
        // IF player X is greater than the Entity X AND less than the entity X + X boundary RIGHT SIDE
        // OR
        // IF Player X is less than entity X AND greater than the entity X - X boundary LEFT SIDE
        if(playerX >= entityX && playerX <= entityX + xBoundary || playerX <= entityX && playerX >= entityX - xBoundary){


            // IF the player Y is greater than the entity Y AND the player Y is less than entity Y + Y boundary TOP SIDE
            // OR
            // IF the player Y is less than the entity Y AND the player Y is greater than the Entity Y - Y boundary BOTTOM SIDE
            if(playerY >= entityY && playerY <= entityY + yBoundary || playerY <= entityY && playerY >= entityY - yBoundary){

                // IF the random int is equal to the condition spawn a bullet and add it to the bullet list
                if (generator.nextInt(shootingChance) == 1) {

                    // Spawn a bullet to LEFT of the entity
                    if(playerX < entityX){
                        direction *= -1;
                    }

                    AbstractBullet enemyBullet = new HandGunBullet(entityX + direction, entityY, BulletType.ALIEN);
                    enemyBullet.calculateMovement(playerX, playerY);
                    BulletList.getBulletList().addBullet(enemyBullet);
                }
            }
        }

    }
}
