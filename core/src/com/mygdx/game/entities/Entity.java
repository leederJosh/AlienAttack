package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.game.AlienGame;

public abstract class Entity {

    protected Vector2 pos;
    protected EntityType type;
    protected int health = 100;
    protected final int
            MAX_HEALTH = 100,
            MIN_HEALTH = 0;

    private float xOrigin;
    private float xDestination;
    private float xBoundary = 20 / AlienGame.ppm;
    protected Texture image;

    /** For collision */
    //protected World world = AlienGame.world;
    protected Body b2body;
    protected float scale = AlienGame.ppm;
    protected BodyDef bdef;

    /** Speed of entity */
    protected final float MAX_SPEED = 1.5f;
    protected static final float speed = 1;

    /** Movement */
    protected boolean moveRight;

    /** Collision */
    protected World world;
    protected Fixture fixture;

    public Entity(float x, float y, EntityType type, World world) {
        this.pos = new Vector2(x, y);
        this.type = type;
        this.world = world;
        xOrigin = x;
        xDestination = x + xBoundary;
        moveRight = true;

    }


    public abstract void render(SpriteBatch batch);

    public void update (float deltaTime){
        moveRight(moveRight);
    }

    public void moveRight(boolean moveRight){
        // Move right by default
        int direction = 1;
        if(moveRight != true){
            direction = -1;
        }
        b2body.applyLinearImpulse(new Vector2((3.8f * direction) / scale, 0f), b2body.getWorldCenter(), true);
    }

    public void disposeOfBox2d(){
        world.destroyBody(b2body);
    }

    /** Getters and Setters */


    public float getx () {
        return pos.x;
    }

    public float gety() {
        return pos.y;
    }

    public void setx(int i) {
        this.pos.x = i;
    }

    public void sety(int i) {
        this.pos.y = i;
    }

    public EntityType getType() {
        return type;
    }

    public float getWidth() {
        return type.getWidth();
    }

    public float getHeight() {
        return type.getHeight();
    }

    public int getHealth() {
        return health;
    }

    public void reduceHealth(int amount) {
        health -= amount;
    }

    //Is this appropriate to be here or should it be in player?
    public void increaseHealth(int amount) {
        health += amount;
    }

    public Texture getTexture(){
        return image;
    }

    public float getxOrigin(){
        return xOrigin;
    }

    public float getxDestination(){
        return xDestination;
    }

    public abstract void defineEntityBox2D(float xPos, float yPos);

    public Body getB2body(){
        return b2body;
    }

    public abstract float getSpeed();

    public BodyDef getBdef() {
        return bdef;
    }

    public World getWorld(){
        return world;
    }

    public boolean getMoveRight(){
        return moveRight;
    }

    public void setMoveRight(boolean b){
        moveRight = b;
    }

    public abstract void reverseMovement();

}