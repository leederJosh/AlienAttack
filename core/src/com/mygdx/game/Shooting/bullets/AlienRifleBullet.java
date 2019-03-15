package com.mygdx.game.shooting.bullets;

import com.mygdx.game.entities.Entity;
import com.mygdx.game.shooting.AbstractBullet;
import com.mygdx.game.shooting.BulletType;

import java.util.ArrayList;

/**
 * Models the behaviour of the alien rifle bullet
 * @Author Josh Leeder
 * @Date 07/03/19
 */
public class AlienRifleBullet extends AbstractBullet {


    public AlienRifleBullet(float posX, float posY, BulletType bulletType) {
        super(posX, posY, bulletType);
        width = 13 / scale;
        height = 13 / scale;
        speed = 7 / scale;
        bulletDamage = 10;
    }

    @Override
    public void act() {
    }

    /**
     * Returns an ArrayList of 4 bullets to add to the BulletList
     * @param entity
     */
    public ArrayList<AbstractBullet> spawnExtraBullets(Entity entity) {

        float entityX = entity.getx();
        float entityY = entity.gety();

        // Way of making bullets travel in negative directions
        int negate = -1;

        //Move to the right
        float right = entityX + entityX;
        //Move to the left
        float left = entityX + entityX * negate;
        //Move up
        float up = entityY + entityY;
        //Move down
        float down = entityY + entityY * negate;

        //Temp array to store the bullets produces when killing ana enemy
        ArrayList<AbstractBullet> rifleBullets = new ArrayList<AbstractBullet>();

        //Create the bullets
        for (int number = 0; number < 4; number++) {
            rifleBullets.add(new AlienRifleBullet(entityX, entityY, BulletType.PLAYER));

            //Shoot to the right and up
            if (number == 0) {
                rifleBullets.get(0).calculateMovement(right, up);
            }
            // Shoot to the right and down
            else if (number == 1) {
                rifleBullets.get(1).calculateMovement(right, down);
            }
            // Shoot to the left and down
            else if (number == 2) {
                rifleBullets.get(2).calculateMovement(left, down);
            }
            // Shoot to the right left and up
            else if (number == 3) {
                rifleBullets.get(3).calculateMovement(left, up);
            }
        }
        return rifleBullets;
    }
}
