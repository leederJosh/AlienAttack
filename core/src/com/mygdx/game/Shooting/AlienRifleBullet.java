package com.mygdx.game.Shooting;

import com.mygdx.game.entities.Entity;

import java.util.ArrayList;

/**
 * Models the behaviour of the alien rifle bullet
 * @Author Josh Leeder
 * @Date 07/03/19
 */
public class AlienRifleBullet extends AbstractBullet{


    public AlienRifleBullet(float posX, float posY, BulletType bulletType) {
        super(posX, posY, bulletType);
        width = 13;
        height = 13;
        speed = 7;
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
        int negate = -1;

        //Move to the right
        float right = entityX + entityX;
        //Move to the left
        float left = entityX + entityX * negate;
        //Move up
        float up = entityY + entityY;
        //Move down
        float down = entityY + entityY * negate;

        ArrayList<AbstractBullet> rifleBullets = new ArrayList<AbstractBullet>();

        //Create the bullets
        for (int number = 0; number < 4; number++) {
            rifleBullets.add(new AlienRifleBullet(entityX, entityY, BulletType.PLAYER));
            System.out.print("\nMaking bullet");

            //Shoot to the right and up
            if (number == 0) {
                rifleBullets.get(0).calculateMovement(right, up);
                System.out.print("\nBullet 0 added");
            }
            // Shoot to the right and down
            else if (number == 1) {
                rifleBullets.get(1).calculateMovement(right, down);
                System.out.print("\nBullet 1 added");
            }
            // Shoot to the left and down
            else if (number == 2) {
                rifleBullets.get(2).calculateMovement(left, down);
                System.out.print("\nBullet 2 added");
            }
            // Shoot to the right left and up
            else if (number == 3) {
                rifleBullets.get(3).calculateMovement(left, up);
                System.out.print("\nBullet 3 added");
            }
        }
        return rifleBullets;
    }

    private void setX(float newX){
        bulletX = newX;
    }

    private void setY(float newY){
        bulletY = newY;
    }
}
