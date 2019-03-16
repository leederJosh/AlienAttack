package com.mygdx.game.shooting;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.game.AlienGame;
import com.mygdx.game.assets.AssetHandler;

/**
 * A class to encapsulate general bullet behavior
 * @author Joshua Leeder
 * @date 12/02/19
 */
public abstract class AbstractBullet {

    /** Bullets current x position */
    protected float bulletX;
    /** Bullets current y position */
    protected float bulletY;
    /** A factor to scale the bullet to depending on the gun */
    private double bulletSize;
    /** The damage a bullet will deal to another entity */
    protected int bulletDamage;
    /** The texture for the bullet */
    private Texture bulletTex;
    /** Whether or not hte bullet needs to be removed */
    private boolean remove;
    /** The speed the bullet travels at */
    protected float speed;
    /** The amount the bullet will move in the X per frame */
    private float moveX;
    /** The amount the bullet will move in the Y per frame */
    private float moveY;
    /** Keeps the bullets original X position so that it can be removed later */
    private float originX;
    /** Keeps the bullet original Y position so that it can be removed later */
    private float originY;
    /** The boundaries for the X and Y of the bullet */
    private static float boundary = 10000;
    /** Width of the bullet */
    protected float width;
    /** Width of the bullet */
    protected float height;
    /** How much the bullet will spread per tick */
    protected int degreeOfSpread;
    /** The type of the bullet */
    protected BulletType bulletType;
    /** The scale of bullet depending on the alien game PPM */
    protected float scale = AlienGame.ppm;


    public AbstractBullet(float posX, float posY, BulletType bulletType){
        bulletX = posX;
        bulletY = posY;
        originX = posX;
        originY = posY;

        this.bulletType = bulletType;
        bulletTex = AssetHandler.getAssetHandler().getTexture("bullet.png");
    }

    /**
     * Renders the bullet next to the players gun
     */
    public void render(){
        bulletTex = new Texture(("bullet.png"));
    }

    public void bulletMovement(){
        updateXPosition();
        updateYPosition();
        removeOffscreenBullet();
    }

    /**
     * Calculates the amount the bullet should move in the x and the y per frame
     * @param mouseX
     * @param mouseY
     */
    public void calculateMovement(float mouseX, float mouseY){

        // Will work out the values I need for the X coordinates to work out hypotenuse
        // This will be the mouseX - current bulletX, then squared and added to
        // the mouseY - current bulletY
        // The square root of the sum of these values squared is the hypotenuse hypo
        float xDifference = mouseX - bulletX;
        float yDifference = mouseY - bulletY;
        float hypo = (float)Math.sqrt((xDifference * xDifference) + (yDifference * yDifference));

        moveX = (xDifference/hypo) * speed;
        moveY = (yDifference/hypo) * speed + degreeOfSpread;
    }

    /** Checks whether a bullet is off the screen and if it is marks remove as true */
    private void removeOffscreenBullet(){

        // I the bullet X is less than 0 or greater than the graphics width then return remove as true
        // This should not be hardcoded, need a better way of doing this
        if(bulletX <  boundary * -1 || bulletX >  boundary){
            remove = true;
        }

        if(bulletY < boundary * -1 || bulletY > boundary){
            remove = true;
        }
    }

    public abstract void act();


    /** Getters and Setters */

    public float getBulletX(){
        return bulletX;
    }

    public float getBulletY(){
        return bulletY;
    }

    public int getBulletDamage(){
        return bulletDamage;
    }

    public void updateXPosition(){
        bulletX += moveX;
    }

    public void updateYPosition() {
        bulletY += moveY;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }

    public boolean toRemove(){
        return remove;
    }

    public Texture getBulletTex(){
        return bulletTex;
    }

    public void setDamage(int damage){
        bulletDamage = damage;
    }
}
