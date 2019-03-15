package com.mygdx.game.shooting.bullets;

import com.mygdx.game.shooting.AbstractBullet;
import com.mygdx.game.shooting.BulletType;

/**
 * Behavior for the hand gun bullet
 * @author Joshua Leeder
 * @date 12/02/19
 */
public class HandGunBullet extends AbstractBullet {


    public HandGunBullet(float posX, float posY, BulletType type){
        super(posX, posY, type);
        width = 15 / scale;
        height = 15 / scale;
        speed = 5 / scale;
        bulletDamage = 25;
    }

    @Override
    public void act() {

    }
}
