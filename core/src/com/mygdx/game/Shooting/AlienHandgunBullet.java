package com.mygdx.game.Shooting;

/**
 * Behavior for the ALIEN hand gun bullet
 * @author Joshua Leeder
 * @date 26/02/19
 */
public class AlienHandgunBullet  extends AbstractBullet{


    public AlienHandgunBullet(float posX, float posY) {
        super(posX, posY);
        width = 2;
        height = 2;
        speed = 5;
        bulletDamage = 25;
    }

    /**
     * Alien handgun will decrease in damage over time
     */
    public void decreaseDamage(){

    }

    /**
     * Alien handgun bullet will increase in size over time
     */
    public void increaseSize(){

        if(width <= 20 && height <= 20){
            width++;
            height++;
        }
    }
}
