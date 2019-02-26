package com.mygdx.game.Shooting;

/**
 * Behavior for the Shotgun bullet
 * @author Joshua Leeder
 * @date 26/02/19
 */
public class ShotGunBullet extends AbstractBullet {

    public ShotGunBullet(float posX, float posY){
        super(posX, posY);
        width = 10;
        height = 10;
        speed = 6;
        bulletDamage = 15;
    }
}
