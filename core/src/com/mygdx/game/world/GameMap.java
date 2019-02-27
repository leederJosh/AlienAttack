package com.mygdx.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Game.MyInputProcessor;
import com.mygdx.game.Pickups.AbstractPickup;
import com.mygdx.game.Pickups.PickupHandler;
import com.mygdx.game.Shooting.AbstractBullet;
import com.mygdx.game.Shooting.BulletList;
import com.mygdx.game.Shooting.ShootingHandler;
import com.mygdx.game.entities.*;

public abstract class GameMap {


    protected Camera cam;
    protected MyInputProcessor inputProcessor;
    private Player player;

    protected PickupHandler pickupHandler;

    private ShootingHandler shootingHandler;

    /** Asset handler */
    private AssetHandler assetHandler;

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    public GameMap(SpriteBatch batch) {
        player = new Player(25, 400, this);
        //Spawn player, add to the EntityList
        EntityList.updateEntityList(player);

        //Spawn enemies, add them to the EntityList
        for (int x = 10; x < Gdx.graphics.getWidth() * 4; x = x + 190) {
            Enemy enemy = new Enemy(x, 400, this);
            EntityList.updateEntityList(enemy);
            //x += 60;
            System.out.println("Enemy added");
        }

        EntityList.updateEntityList(new Friendly(50, 400, this));
        EntityList.updateEntityList(new Friendly(2000, 400, this));

			/*EntityList.updateEntityList(new Enemy(10, 400, this));
			EntityList.updateEntityList(new Enemy(80, 400, this));
			EntityList.updateEntityList(new Enemy(100, 400, this));
			EntityList.updateEntityList(new Enemy(120, 400, this));
			EntityList.updateEntityList(new Enemy(160, 400, this));
			EntityList.updateEntityList(new Enemy(140, 400, this));*/

        System.out.println(EntityList.getMapEntities().size());



//			entities.add(new Enemy(80, 400, this));
//			entities.add(new Enemy(100, 400, this));
//			entities.add(new Enemy(120, 400, this));
//			entities.add(new Enemy(160, 400, this));
//			entities.add(new Enemy(140, 400, this));

        //create an input processor
        this.inputProcessor =
                new MyInputProcessor(cam, player);
        Gdx.input.setInputProcessor(inputProcessor);

        //I added this
        pickupHandler = new PickupHandler();

        // Handles the shooting
        shootingHandler = new ShootingHandler();

        //Create the assetHandler
        assetHandler.getAssetHandler();

    }
    //made so we can call the methods in alien game
    //orthogonal camera since it is side on
    public void render (OrthographicCamera camera, SpriteBatch batch) {
        //loop through the different entities and render them
        for(Entity entity: EntityList.getListEntities()) {
            entity.render(batch);
        }

        //Just done to check the drawing of the health pickup works
        // This does indead work so huzzah
        /** Pretty sure this works logically but needs to be able to pass around the list of pickups? Should I use another singleton? Could have a pickupHandler object in each map?*/
        for (AbstractPickup pickup: pickupHandler.getActivePickups()){
            pickup.render(batch, pickup.getPickupX(), pickup.getPickupY());
        }
        //pickup.render(batch, player.getx(), player.gety());

        //If the bulletList contains a bullet it will be rendered here
        //This will not be removed as of yet
        if(BulletList.getBulletList().getBullets().size() != 0){
            for(AbstractBullet bullet: BulletList.getBulletList().getBullets()){
                batch.draw(bullet.getBulletTex(), bullet.getBulletX() , bullet.getBulletY(), bullet.getWidth(), bullet.getHeight());
            }

        }

        camera.position.x = EntityList.getListEntities().get(0).getx();
        camera.position.y = EntityList.getListEntities().get(0).gety();
        camera.update();
    }
    public void update (float delta) {



        //need to apply gravity (used earths gravity in this case)
        for(Entity entity: EntityList.getListEntities()) {
            entity.update(delta, -9.8f);
        }

        if(BulletList.getBulletList().getBullets().size() != 0) {
            for (AbstractBullet bullet : BulletList.getBulletList().getBullets()) {

                if (bullet.toRemove() == true) {
                    BulletList.getBulletList().addBulletToRemove(bullet);
                }
                bullet.bulletMovement();
                // System.out.print("There is a bullet in the bullet list\n");
            }
        }
    }
    public abstract void dispose ();

    public abstract int getWidth();
    public abstract int getHeight();
    public abstract int getLayers();
    //gets the width, height and number of layers

    /**
     * gets a tile by pixel position within the game com.mygdx.game.world at a specified layer.
     * @param layer
     * @param x
     * @param y
     * @return
     */
    //called getTileByLocation on video tutorial
    public Tile getTileByid(int layer, float x, float y) {
        return this.getTileByCoordinate(layer, (int) (x/Tile.TILE_SIZE), (int) (y/Tile.TILE_SIZE));
    }
    //gets the location on the screen (game com.mygdx.game.world) and divide it by the tile size
    //converts it to an integer, it gets the location within the array that the tile exists in
    //then calls the next method to get the tile from where it is.

    /**
     *Gets a tile at its coordinate wihtin the map at a specified layer
     * @param layer
     * @param col
     * @param row
     * @return
     */
    public abstract Tile getTileByCoordinate(int layer, int col, int row);

    public boolean doesRectCollideWithMap(float x, float y, int width, int height) {
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
                    Tile type = getTileByCoordinate(layer, col, row);
                    if(type != null && type.isCollidable())
                        return true;
                    //tells the game there is a collision
                }
            }
        }
        return false;
    }

    //gets pixel width and height
    public int getPixelWidth() {
        return this.getWidth() * Tile.TILE_SIZE;
    }
    public int getPixelHeight() {
        return this.getHeight() * Tile.TILE_SIZE;
    }


}