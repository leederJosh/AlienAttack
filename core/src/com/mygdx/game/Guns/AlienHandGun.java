package com.mygdx.game.Guns;

import com.mygdx.game.Shooting.AbstractBullet;
import com.mygdx.game.Shooting.AlienHandgunBullet;
import com.mygdx.game.Shooting.BulletList;
import com.mygdx.game.Shooting.HandGunBullet;
import com.mygdx.game.entities.EntityList;

/**
 * Creates an AlienHandGun bullet and adds it to the BulletList
 * @Autor Josh Leeder
 * @Date 26/02/19
 */
public class AlienHandGun implements GunInterface {



    @Override
    public void shoot(float mappedMouseX, float mappedMouseY) {

        //Get the position of the player to shoot in the right direction
        float playerX = EntityList.getEntities().get(0).getx();
        float playerY = EntityList.getEntities().get(0).gety();

        // The amount X of the player the bullet will spawn
        int direction = 10;

        //Check which way to spawn bullet
        //By default spawn to the right, if the player x is greater than the mouse do this to spawn to the left
        if(playerX >= mappedMouseX){

            direction = direction * -1;
        }

        // Make the bullet, calculate how it should move per tick and add it to the bullet list
        AbstractBullet bullet = new AlienHandgunBullet(playerX + direction, playerY);
        bullet.calculateMovement(mappedMouseX, mappedMouseY);
        BulletList.getBulletList().addBullet(bullet);
    }
}
