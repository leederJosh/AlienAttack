package com.mygdx.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.AI.AIHandler;
import com.mygdx.game.Game.MyInputProcessor;
import com.mygdx.game.Pickups.AbstractPickup;
import com.mygdx.game.Pickups.PickupHandler;
import com.mygdx.game.Shooting.AbstractBullet;
import com.mygdx.game.Shooting.BulletList;
import com.mygdx.game.Shooting.ShootingHandler;
import com.mygdx.game.entities.*;
import com.mygdx.game.tiles.Tile;
import world.DialogNode;

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
    //Set all UI elements to draw a certain amount down the window.
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

    /** Camera */
    protected Camera cam;
    protected MyInputProcessor inputProcessor;
    private Player player; // Should be moved to game


    public AbstractLevel() {
//        player = new Player(25, 400, this);
//        //Spawn player, add to the EntityList
//        EntityList.updateEntityList(player);
//        EntityList.setPlayer(player);

        //create an input processor
        inputProcessor = new MyInputProcessor(cam, player );
        Gdx.input.setInputProcessor(inputProcessor);

        // Handlers
        pickupHandler = new PickupHandler();
        shootingHandler = new ShootingHandler();
        aiHandler = new AIHandler();

        //Currently equipped weapon for the top right of screen
        weapon = AssetHandler.getAssetHandler().getTexture("Pistol.png");

        // HUD elements
        sr = new ShapeRenderer();
        imageBatch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal( "core/assets/8bit9.fnt"));
        text = new SpriteBatch();


    }

    //TODO
    // Add These methods:
    // spawnEnemies
    // spawnFriendlies
    // spawnBoss
    // Put the logic for spawning the entities in here and call them in the constructor
    // Could move the collision detection to a collision handler?
    // Move the spawning of the player to the GAME class
    // Add a way to transition from level to level:
    // This will likely be an X and Y box somewhere on the map
    // Will also need a way to change the screen I think?
    // Should we move the levels to their own package?

    public abstract void spawnEnemies();
    public abstract void spawnFriendlies();

    //made so we can call the methods in alien game
    //orthogonal camera since it is side on
    public void render (OrthographicCamera camera, SpriteBatch batch) {

        //loop through the different entities and render them
        // This was iterative over the map using getListEntities
        for (Entity entity : EntityList.getListEntities()) {
            entity.render(batch);
        }

        //Just done to check the drawing of the health pickup works
        // This does indead work so huzzah
        /** Pretty sure this works logically but needs to be able to pass around the list of pickups? Should I use another singleton? Could have a pickupHandler object in each map?*/
        for (AbstractPickup pickup: pickupHandler.getActivePickups()){
            pickup.render(batch, pickup.getPickupX(), pickup.getPickupY());
        }

        //pickupHandler.render(batch, player.getx(), player.gety());

        //If the bulletList contains a bullet it will be rendered here
        //This will not be removed as of yet
        if(BulletList.getBulletList().getBullets().size() != 0){
            for(AbstractBullet bullet: BulletList.getBulletList().getBullets()){
                batch.begin();
                batch.draw(bullet.getBulletTex(), bullet.getBulletX() , bullet.getBulletY(), bullet.getWidth(), bullet.getHeight());
                batch.end();
            }

        }

        camera.position.x = EntityList.getListEntities().get(0).getx();
        camera.position.y = EntityList.getListEntities().get(0).gety();
        camera.update();

        batch.begin();

        //Get player's health and humanity values for the UI bars.
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

        //need to apply gravity (used earths gravity in this case)
        // This loop gets all entities and performs various actions on them within the level
        for(Entity entity: EntityList.getListEntities()) {
            entity.update(delta, -9.8f);


            // AI handler that acts on every entity except for player
            if(entity.getType().getId().equals("player") == false){
                aiHandler.makeEntityAct(entity);
            }

            //Handles the bullet collisions
            if(shootingHandler.handleBullet(entity) == true){
                //Has a chance to add a pickup to the pickUp handler if an entity has dies
                pickupHandler.addPickUp(entity);
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

    public boolean checkPlayerHasFinished() {
        if (player.getx() > 500) {
            return true;
        }
        else return false;
    }

    /**
     * gets a tile by pixel position within the game com.mygdx.game.world at a specified layer.
     * @param layer
     * @param x
     * @param y
     * @return
     */
    //called getTileByLocation on video tutorial
    public Tile getTileByid(int layer, float x, float y) {
        //gets the location on the screen (game com.mygdx.game.world) and divide it by the tile size
        //converts it to an integer, it gets the location within the array that the tile exists in
        //then calls the next method to get the tile from where it is.
        return this.getTileByCoordinate(layer, (int) (x/Tile.TILE_SIZE), (int) (y/Tile.TILE_SIZE), getTiledMap());
    }


    /**
     * Gets a tile at its coordinate wihtin the map at a specified layer
     * @param layer
     * @param col
     * @param row
     * @return
     */
    //allows us to get data based on the map
    public Tile getTileByCoordinate(int layer, int col, int row, TiledMap tiledMapParam) {
        //there is different cells in the tilemap which can have tiles within in
        //this method returns the tile type based on the coordinate of the cell (where in the map) (the tile position)
        TiledMapTileLayer.Cell cell = ((TiledMapTileLayer) tiledMapParam.getLayers().get(layer)).getCell(col, row);

        if (cell!= null) {
            //if it equals null then it means we are clicking on a tile outside the map or one that doesn't exist on that layer
            TiledMapTile tile = cell.getTile();

            if(tile != null) {
                int id = tile.getId();
                return Tile.getTileByid(id);
            }
        }
        return null;
    }

    public boolean doesRectCollideWithMap(float x, float y, int width, int height, TiledMap tiledMapParam) {
        //checking whether the player is outside the map (also checking the width of the player is inside the map)
        if (x < 0 || y < 0 || x + width > getPixelWidth()|| y + height > getPixelHeight()) {
            return true;
        }
        //triple embedded loop to check for each tile
        //math.ceil rounds numbers up to the next value, if you dont do this will have weird results if entities are same size as tiles
        //only check the squares around the entity
        for(int row = (int) (y / Tile.TILE_SIZE); row < Math.ceil((y + height) / Tile.TILE_SIZE); row++) {
            for(int col = (int) (x / Tile.TILE_SIZE); col < Math.ceil((x + width) / Tile.TILE_SIZE); col++) {
                for(int layer = 0; layer < getLayers(); layer++) {
                    Tile type = getTileByCoordinate(layer, col, row, getTiledMap());
                    if(type != null && type.isCollidable())
                        return true;
                    //tells the game there is a collision
                }
            }
        }
        return false;
    }

    /**
     * Gets the amount of layers in a given map
     * @return Num of layers in a given map
     */
    public int getLayers() {
        return tiledMap.getLayers().getCount();
    }

    //gets pixel width and height
    public int getPixelWidth() {
        return this.getWidth() * Tile.TILE_SIZE;
    }
    public int getPixelHeight() {
        return this.getHeight() * Tile.TILE_SIZE;
    }
    public int getWidth() {
        //given in width of tiles
        return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getWidth();
    }
    public int getHeight() {
        //given in height of tiles
        return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getHeight();
    }
    public void dispose (){
        tiledMap.dispose();
    }
    public TiledMap getTiledMap() {
        return tiledMap;
    }
}