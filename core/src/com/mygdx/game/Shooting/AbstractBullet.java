package com.mygdx.game.Shooting;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A class to encapsulate general bullet behavior
 * @author Joshua Leeder
 * @date 12/02/19
 */
public abstract class AbstractBullet {

    /** Bullets current x position */
    private float bulletX;
    /** Bullets current y position */
    private float bulletY;
    /** A factor to scale the bullet to depending on the gun */
    private double bulletSize;
    /** The damage a bullet will deal to another entity */
    protected int bulletDamage;
    /** The texture for the bullet */
    private Texture bulletTex;
    /** Whether or not hte bullet needs to be removed */
    private boolean remove;
    /** Asset manager to get the texture */
    AssetManager manager = new AssetManager();
    /** The speed the bullet travels at */
    protected float speed;
    /**The amount the bullet will move in the X per frame */
    private float moveX;
    /**The amount the bullet will move in the Y per frame */
    private float moveY;
    /** Keeps the bullets original X position so that it can be removed later */
    private float originX;
    /** Keeps the bullet original Y position so that it can be removed later */
    private float originY;
    /** The bundaries for the X and Y of the bullet */
    private static float boundary = 10000;
    /** Width of the bullet */
    protected float width;
    /** Width of the bullet */
    protected float height;
    /** How much the bullet will spread per tick */
    protected int degreeOfSpread;
    /** The type of the bullet */
    protected BulletType bulletType;


    //Constructor
    public AbstractBullet(float posX, float posY, BulletType bulletType){
        bulletX = posX;
        bulletY = posY;

        this.bulletType = bulletType;

        originX = posX;
        originY = posY;

        // manager.load("assets/bullet.png", Texture.class);
        // This is the problem at the moment
        bulletTex = new Texture ("assets/bullet.png");
    }

    /**
     * Updates the current X position of the bullet
     */
    public void updateXPosition(){
        bulletX += moveX;
    }

    /**
     * Updates the current Y position of the bullet
     */
    public void updateYPosition() {
        bulletY += moveY;
    }

    public int getBulletDamage(){
        return bulletDamage;
    }

    public void bulletMovement(){
        updateXPosition();
        updateYPosition();
        removeOffscreenBullet();
    }

    //get the X pos of the bullet
    public float getBulletX(){
        return bulletX;
    }

    //get the Y pos of the bullet
    public float getBulletY(){
        return bulletY;
    }

    //get the speed of the bullet
    public float getSpeed(){
        return speed;
    }

    public void updateRemove(){
        remove = true;
    }

    /**
     * Renders the bullet next to the players gun
     */
    public void render(SpriteBatch batch, float posX, float posY){
        //Not sure if this will work yet
        bulletTex = new Texture(Gdx.files.internal("/core/assets/bullet.png"));
        //batch.draw(bulletTex, posX, posY);
    }

    /**
     * Whether a bullet should be removed or not
     * @return remove
     */
    public boolean toRemove(){
        return remove;
    }

    /**
     * Returns the texture for the bullet
     * @return bulletTex
     */
    public Texture getBulletTex(){
        return bulletTex;
    }

    /**
     * Calculates the amount the bullet should move in the x and the y per frame
     * @param mouseX
     * @param mouseY
     */
    public void calculateMovement(float mouseX, float mouseY){

        // Will work out the values i need for the X coordinates to work out hypotenuse
        // This will be the mouseX - current bulletX, then squared and added to
        // the mouseY - current bulletY
        // The squareroot of the sum of these values squared is the hypotenuse hypo
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

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }

    // To Do

    // Then collision detection
    // Should be a case of getting the x of the bullet and comparing
    // the x and the x plus width with all the entities int he bullet list
    // Same idea with the y i think for now
    // Then if they collide do something, o health first then humanity

    // Then i can work on other guns like machine gun and shotgun
    // Think I may need to make states or strategies for this
}
