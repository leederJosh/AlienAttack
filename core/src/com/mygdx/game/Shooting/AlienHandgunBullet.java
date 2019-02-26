package com.mygdx.game.Shooting;

import com.badlogic.gdx.Gdx;

import java.util.Random;

/**
 * Behavior for the ALIEN hand gun bullet
 * @author Joshua Leeder
 * @date 26/02/19
 */
public class AlienHandgunBullet  extends AbstractBullet {

    private Random generator;


    public AlienHandgunBullet(float posX, float posY) {
        super(posX, posY, BulletType.ALIEN);
        width = 3;
        height = 3;
        speed = 5;
        bulletDamage = 25;
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
        if (generator.nextInt(15) == 5){
            width++;
            height++;
            System.out.print("Width" + width);
            System.out.print("Height" + height);
        }
    }
}
