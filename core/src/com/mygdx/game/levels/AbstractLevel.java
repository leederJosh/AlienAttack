package com.mygdx.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ai.AIHandler;
import com.mygdx.game.collisions.MapObjectParser;
import com.mygdx.game.collisions.MyContactListener;
import com.mygdx.game.game.AlienGame;
import com.mygdx.game.game.MyInputProcessor;
import com.mygdx.game.pickups.AbstractPickup;
import com.mygdx.game.pickups.PickupHandler;
import com.mygdx.game.shooting.AbstractBullet;
import com.mygdx.game.shooting.BulletList;
import com.mygdx.game.shooting.ShootingHandler;
import com.mygdx.game.assets.AssetHandler;
import com.mygdx.game.entities.*;
import world.DialogNode;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * An abstract template for general level behaviour
 */
public abstract class AbstractLevel {

    /** Map rendering */
    protected TiledMap tiledMap;
    protected OrthogonalTiledMapRenderer tiledMapRenderer;

    /** HUD elements */
    protected ShapeRenderer sr;
    protected Texture weapon;
    protected SpriteBatch text;
    protected SpriteBatch imageBatch;
    protected BitmapFont font;
    private int playerHealth;
    private int playerHumanity;

    //Set all ui elements to draw a certain amount down the window.
    private double windowHeight = Gdx.graphics.getHeight();
    private double percentageChangeBars = (windowHeight * 0.05);
    private double drawHeightBars = windowHeight - percentageChangeBars;
    private double drawHeightText = windowHeight - (percentageChangeBars / 4);

    /** Handlers */
    protected ShootingHandler shootingHandler;
    protected PickupHandler pickupHandler;
    protected AIHandler aiHandler;
    private DialogNode currentDialog;
    protected BulletList bulletList;
    protected MapObjectParser mapObjectParser;

    /** Camera */
    protected Camera cam;
    protected MyInputProcessor inputProcessor;

    /** Entities for a given map */
    protected ArrayList<Entity> entitiesToSpawn;

    /** The end point of a map */
    protected Rectangle levelEnd;

    /** The world for the levels Box2D objects */
    protected World world;

    /** The scale of the level */
    protected float scale = AlienGame.ppm;


    public AbstractLevel() {

        //create an input processor
        inputProcessor = new MyInputProcessor(cam);
        Gdx.input.setInputProcessor(inputProcessor);

        // Handlers
        pickupHandler = new PickupHandler();
        shootingHandler = new ShootingHandler();

        //Currently equipped weapon for the top right of screen
        weapon = AssetHandler.getAssetHandler().getTexture("Pistol.png");

        // HUD elements
        sr = new ShapeRenderer();
        imageBatch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal( "core/assets/8bit9.fnt"));
        text = new SpriteBatch();
    }


    public abstract void spawnEntities();

    //made so we can call the methods in alien game
    //orthogonal camera since it is side on
    public void render (OrthographicCamera camera, SpriteBatch batch) {

        //loop through the different entities and render them
        for (Entity entity : EntityList.getListEntities()) {
            entity.render(batch);
        }

        // Render all pickups
        for (AbstractPickup pickup: pickupHandler.getActivePickups()){
            pickup.render(batch, pickup.getPickupX(), pickup.getPickupY());
        }

        //If the bulletList contains a bullet it will be rendered here
        if(BulletList.getBulletList().getBullets().size() != 0){
            for(AbstractBullet bullet: BulletList.getBulletList().getBullets()){
                batch.begin();
                batch.draw(bullet.getBulletTex(), bullet.getBulletX() , bullet.getBulletY(), bullet.getWidth(), bullet.getHeight());
                batch.end();
            }
        }

        batch.begin();

        //Get player's health and humanity values for the ui bars.
        playerHealth = EntityList.getEntityList().getPlayer().getHealth();
        playerHumanity = EntityList.getEntityList().getPlayer().getHumanity().getHumanityValue();

        //draw the bars here
        sr.begin(ShapeRenderer.ShapeType.Filled);

        //health
        sr.setColor(Color.RED); //uses gdx.color
        sr.rect(15,	(int) drawHeightBars, 100, 10);
        sr.setColor(Color.GREEN);
        sr.rect(15, (int) drawHeightBars, playerHealth, 10);

        //humanity
        sr.setColor(Color.GRAY);
        sr.rect(275, (int) drawHeightBars, 100, 10);
        sr.setColor(Color.ORANGE);
        sr.rect(275, (int) drawHeightBars, playerHumanity, 10);
        sr.end();

        //outlines (weapon and bars)
        sr.begin(ShapeRenderer.ShapeType.Line);

        //weapon
        sr.setColor(Color.GREEN);
        sr.rect(575, ((int) drawHeightBars - 25), 50, 50);
        batch.draw(weapon, 575, 425);

        //health
        sr.setColor(Color.BLACK);
        sr.rect(15, (int) drawHeightBars, 100, 10);

        //humanity
        sr.rect(275, (int) drawHeightBars, 100, 10);
        sr.end();
        batch.end();

        //drawing the pistol in top right
        imageBatch.begin();
        imageBatch.draw(weapon, 450, ((int) drawHeightBars - 30), 50, 50);
        imageBatch.end();

        //Text labels
        text.begin();
        font.draw(text, "HP:", 15, (float) drawHeightText);
        font.draw(text, "HUMANITY:", 275, (float) drawHeightText);
        text.end();

        //drawing the dialog box
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.WHITE);
        sr.rect(10, 5, 500, 100);
        sr.end();
        text.begin();
        font.draw(text, "DIALOG", 10, 115);

        //TODO: Add dialog changes here.
        TreeMap<Double, Entity> entities = EntityList.getMapEntities();

        //Iterate through the tree map to find the closest entity.
        for(Map.Entry<Double,Entity> entry : entities.entrySet()) {
            Entity e = entry.getValue();

            //The dialog will be different depending on which entity is closest
            if (e instanceof Friendly) {
                //Add friendly dialog to the dialog box
                font.draw(text, ((Friendly) e).getHitDialog().getText(), 15, 90);
                break;
            } else if (e instanceof Enemy) {
                //Add enemy dialog to the dialog box
                font.draw(text, ((Enemy) e).getDialog().getText(), 15, 90);
                break;
            }
        }
        text.end();
    }
    public void update (float delta) {
        //To remove the dead entities
        EntityList.getEntityList().removeDeadEntities();

        // This loop gets all entities and performs various actions on them within the level
        for(Entity entity: EntityList.getListEntities()) {

            entity.update(delta);

            //Handles the bullet collisions
            if(shootingHandler.handleBullet(entity) == true){
                //Has a chance to add a pickup to the pickUp handler if an entity has dies
                pickupHandler.addPickUp(entity);
            }

            if (entity.getType().getId().equals("player") == false) {
                aiHandler.makeEntityAct(entity);
            }
        }

        //To remove the bullets
        BulletList.getBulletList().removeBullets();

        // Checks to see which bullets in the bullet list needs to be removed and makes bullets move
        if(BulletList.getBulletList().getBullets().size() != 0) {
            for (AbstractBullet bullet : BulletList.getBulletList().getBullets()) {

                if (bullet.toRemove() == true) {
                    BulletList.getBulletList().addBulletToRemove(bullet);
                }
                bullet.bulletMovement();
            }
        }

        //Handles any collision between player and pickup
        pickupHandler.hasCollidedWithPickUp();
    }

    public abstract void dispose ();

    public abstract World getWorld();

    /**
     * Returns true if the player is within the level transition box
     * @return
     */
    public boolean hasPlayerFinished(){
        boolean hasPlayerFinished = false;

        Player player = EntityList.getEntityList().getPlayer();
        float playerX = player.getx();
        float playerY = player.gety();
        float playerWidth = player.getWidth();
        float playerHeight = player.getHeight();


        if(playerX + playerWidth > levelEnd.getX() && playerX < levelEnd.getX() + levelEnd.getWidth()){

            if(playerY + playerHeight > levelEnd.getY() && playerY + playerHeight < levelEnd.getY() + levelEnd.getHeight()){
                hasPlayerFinished = true;
            }
        }
        return hasPlayerFinished;
    }

    public abstract void spawnPlayer();

}