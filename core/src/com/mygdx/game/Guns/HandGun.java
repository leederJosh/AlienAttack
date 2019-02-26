package com.mygdx.game.Guns;

import com.mygdx.game.Shooting.AbstractBullet;
import com.mygdx.game.Shooting.BulletList;
import com.mygdx.game.Shooting.HandGunBullet;
import com.mygdx.game.entities.EntityList;

/**
 * Concrete handgun behaviour class
 * Handles the creation of a HandGun object and adds it to the bulletList
 * @Author Josh Leeder
 * @Date 25/02/19
 */
public class HandGun implements GunInterface{



    @Override
    public void shoot(float mappedMouseX, float mappedMouseY) {

        //Get the position of the player to shoot in the right direction
        float playerX = EntityList.getEntities().get(0).getx();
        float playerY = EntityList.getEntities().get(0).gety();
        int direction = 10;

        //Check which way to spawn bullet
        //By default spawn to the right, if the player x is greater than the mouse do this to spawn to the left
        if(playerX >= mappedMouseX){

            direction = direction * -1;
        }

        // Make the bullet, calculate how it should move per tick and add it to the bullet list
        AbstractBullet bullet = new HandGunBullet(playerX + direction, playerY);
        bullet.calculateMovement(mappedMouseX, mappedMouseY);
        BulletList.getBulletList().addBullet(bullet);
    }

        // In input processor
        // Add a way to switch weapons (numbers?)
        // this should call the switch gun method in player
    

        //Also need to add an object of each gun in player
}
