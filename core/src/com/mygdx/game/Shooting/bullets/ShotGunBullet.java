package com.mygdx.game.shooting.bullets;

import com.mygdx.game.shooting.AbstractBullet;
import com.mygdx.game.shooting.BulletType;

/**
 * Behavior for the Shotgun bullet
 * @author Joshua Leeder
 * @date 26/02/19
 */
public class ShotGunBullet extends AbstractBullet {

    public ShotGunBullet(float posX, float posY, BulletType type){
        super(posX, posY, type);
        width = 10 / scale;
        height = 10 / scale;
        speed = 6 / scale;
        bulletDamage = 15;
    }


    @Override
    public void act() {

    }
}
