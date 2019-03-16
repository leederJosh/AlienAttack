package com.mygdx.game.guns;

import com.mygdx.game.game.AlienGame;
import com.mygdx.game.shooting.*;
import com.mygdx.game.entities.EntityList;
import com.mygdx.game.shooting.bullets.ShotGunBullet;

import java.util.Random;

/**
 * Concrete shotgun behaviour class
 * Handles the creation of a ShotGun object(s) and adds it to the bulletList
 * @Author Josh Leeder
 * @Date 26/02/19
 */
public class ShotGun implements GunInterface{

    Random generator;

    // The amount X of the player the bullet will spawn
    private float direction = 10 / AlienGame.ppm;

    @Override
    public void shoot(float mappedMouseX, float mappedMouseY) {

        generator = new Random();

        //Get the position of the player to shoot in the right direction
        float playerX = EntityList.getEntities().get(0).getx();
        float playerY = EntityList.getEntities().get(0).gety();

        //Check which way to spawn bullet
        //By default spawn to the right, if the player x is greater than the mouse do this to spawn to the left
        if(playerX >= mappedMouseX){

            direction = direction * -1;
        }


        // The amount the bullets will spread (to simulate shotgun bullet spread)
        int degreeOfSpread = 0;

        //This will loop through 3 times making 3 different bullets with different spreads (one with normal y, one with positive y, one with negative y)
        for(int index = 0; index <= 2; index++){

            if(index == 1){
                degreeOfSpread = generateSpread() * -1;
            }
            else if (index == 2){
                degreeOfSpread = generateSpread();
            }

            // Make the bullets, calculate how they should move per tick and add them to the bullet list
            AbstractBullet bullet = new ShotGunBullet(playerX + direction, playerY, BulletType.PLAYER);
            bullet.calculateMovement(mappedMouseX, mappedMouseY + degreeOfSpread);
            BulletList.getBulletList().addBullet(bullet);

        }
    }

    /**
     * Generate a random spread value for the shotgun bullet between two bounds
     * @return spread
     */
    private int generateSpread(){
        generator = new Random();
        int lowerBound = 15;
        int upperBound = 39;
        int spread = generator.nextInt(upperBound-lowerBound) + lowerBound;

        return spread;
    }
}
