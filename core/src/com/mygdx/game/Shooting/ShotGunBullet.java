package com.mygdx.game.Shooting;

/**
 * Behavior for the Shotgun bullet
 * @author Joshua Leeder
 * @date 26/02/19
 */
public class ShotGunBullet extends AbstractBullet {

    public ShotGunBullet(float posX, float posY, BulletType type){
        super(posX, posY, type);
        width = 10;
        height = 10;
        speed = 6;
        bulletDamage = 15;
    }


    @Override
    public void act() {

    }
}
