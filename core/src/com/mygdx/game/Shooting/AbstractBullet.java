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
    private int bulletDamage;
    /** The texture for the bullet */
    private Texture bulletTex;
    /** Whether or not hte bullet needs to be removed */
    private boolean remove;
    /** Asset managet to get the texture */
    AssetManager manager = new AssetManager();
    /** The speed the bullet travels at */
    protected float speed;


    //Constructor
    public AbstractBullet(float posX, float posY){
        bulletX = posX;
        bulletY = posY;
        // manager.load("assets/bullet.png", Texture.class);
        // This is the problem at the moment
        bulletTex = new Texture ("assets/bullet.png");
    }

    /**
     * Updates the current X position of the bullet
     * @param movement
     */
    public void updateXPosition(double movement){
        bulletX += bulletX + movement;
    }

    /**
     * Updates the current Y position of the bullet
     * @param movement
     */
    public void updateYPosition(double movement) {
        bulletY += bulletY + movement;
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

    /**
     * Renders the bullet next to the players gun
     */
    public void render(SpriteBatch batch, float posX, float posY){
        //Not sure if this will work yet
        bulletTex = new Texture(Gdx.files.internal("/core/assets/bullet.png"));
        //batch.draw(bulletTex, posX, posY);
    }

    /**
     *  Detects a collision between a bullet and an entity
     */
    public void checkBulletCollision(){
        //Need an if statement to check whether the x and y of the bullet plus it's size (size of asset) overlap with an entity in the entity list
    }

    /**
     * Whether a bullet should be removed or not
     * @return @remove
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

    //What do I need
    // A way to spawn bullets to the screen (Then probably add them to a bullet list)
    // Spawning bullets next to the players gun
    // Updating the bullets position
    // Disposing of the bullet
    // How am I going to handle angles? Like pythag for sure but shall I use an origin point

    // Could make this into an abstract class

    // Get bullet to spawn on click
    // Get bullet to spawn at the players gun
    // Get bullet to travel in x and y positions straigh line
    // Get bullets to travel in a direction
    // Add bullets to a bullet list
    // Collision detection for a single bullet
    // On collision reduce health of entity
    // Transfer this to a bullet list
}
