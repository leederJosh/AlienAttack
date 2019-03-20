package com.mygdx.game.shooting.bullets;

import com.mygdx.game.shooting.AbstractBullet;
import com.mygdx.game.shooting.BulletType;

import java.util.Random;

/**
 * Behavior for the ALIEN hand gun bullet
 * @author Joshua Leeder
 * @date 26/02/19
 */
public class AlienHandgunBullet  extends AbstractBullet {

    private Random generator;

    public AlienHandgunBullet(float posX, float posY, BulletType bulletType) {
        super(posX, posY, bulletType);
        width = 7 / scale;
        height = 7 / scale;
        speed = 7 / scale;
        bulletDamage = 5;
        generator = new Random();
    }

    /**
     * Alien handgun will decrease in damage over time
     */
    public void decreaseDamage() {

    }

    /**
     * Alien handgun bullet will increase in size over time
     */
    public void act() {
        if (generator.nextInt(5) == 1){
            width += 1 / scale;
            height += 1 /scale;
        }
    }
}
