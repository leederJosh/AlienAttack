package com.mygdx.game.Shooting;

import com.mygdx.game.entities.Entity;

import java.util.Random;

/**
 * Models the behaviour of the alien rifle bullet
 * @Author Josh Leeder
 * @Date 07/03/19
 */
public class AlienRifleBullet extends AbstractBullet{

    private Random random;
    private int chance;

    public AlienRifleBullet(float posX, float posY, BulletType bulletType) {
        super(posX, posY, bulletType);
        width = 10;
        height = 10;
        speed = 5;
        bulletDamage = 5;
    }

    @Override
    public void act() {

    }


    /**
     * Spawns 4 miniature bullets that fly from a point when an entity dies
     * @param entity
     */
    public void act(Entity entity) {

        float movementX;
        float movementY;
        float entityX = entity.getx();
        float entityY = entity.gety();


        //TODO
        // Need to spawn 4 bullets from the entities location
        // Need to go in 4 differnt directions
        // Gonna use a factor param that will be 1 or -1 to make the results negative or positive
        // THIS IS GONNA TAKE A BIT OF THINKING BUT SHOULD WORK FOR THE MOST PART
        // COULD DO THIS:
        //      AN IF STATEMENT IN THE MOVE CALCULATOR THAT INVERTS THE VALUE OF GIVEN FACTOR SO I CAN USE A POSITIVE AND A NEGATIVE (don't think this will work)

        // Gonna do:
        // X * 2 and Y * 2 to go to the right and up
        // X * 2 and Y * 2 * -1 to go right and down
        // X * 2 * -1 and Y * 2 -1 to go left and down
        // X * 2 * -1 and y * 2 to go left and up


//        if(random.nextInt(chance) == 1){
//
//            AlienRifleBullet rifleBullet = new AlienRifleBullet(width, height, BulletType.PLAYER);
//
//            //Shooting to the bottom left
//            movementX = entityX
//            rifleBullet.calculateMovement();
//
//        }
    }

//    //COULD RETURN AN ARRAY OF 2 VALUES
//    private float miniBulletMoveCalc(float originX, float originY, int factor){
//
//        float xDifference = originX - bulletX;
//        float yDifference = originY - bulletY;
//        float hypo = (float)Math.sqrt((xDifference * xDifference) + (yDifference * yDifference));
//
//        moveX = (xDifference/hypo) * speed;
//        moveY = (yDifference/hypo) * speed + degreeOfSpread;
//    }

    private void setX(float newX){
        bulletX = newX;
    }

    private void setY(float newY){
        bulletY = newY;
    }
}
