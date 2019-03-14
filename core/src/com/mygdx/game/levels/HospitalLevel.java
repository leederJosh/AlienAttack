package com.mygdx.game.levels;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.assets.AssetHandler;
import com.mygdx.game.collisions.MapObjectParser;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.EntityList;
import com.mygdx.game.entities.Player;

/**
 * Level 3
 */
public class HospitalLevel extends AbstractLevel {

    /** The world for the levels Box2D objects */
    private World world;

    public HospitalLevel() {
        super();
        tiledMap = AssetHandler.getAssetHandler().loadLevel("Level Four/Hospital.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1  / scale);
        world = new World(new Vector2(0f, -9.81f), false);
        mapObjectParser = new MapObjectParser(world, tiledMap);
        mapObjectParser.parseFloorObjectLayer();
        entitiesToSpawn = mapObjectParser.parseEntitySpawnPoints();
        levelEnd = mapObjectParser.parseTransitionObjects();
    }

    @Override
    public void spawnEntities() {
        for(Entity entity : entitiesToSpawn){
            EntityList.updateEntityList(entity);
        }
    }

    @Override
    public void render(OrthographicCamera camera, SpriteBatch batch) {
        //Set the camera/view, tells the renderer what camera to use
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        //this is to render the entities
        //this is so the game renders based on how the cameras want the game to render
        batch.setProjectionMatrix(camera.combined);
        super.render(camera, batch);
        camera.update();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void dispose() {
        tiledMap.dispose();
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public Rectangle getLevelEnd() {
        return levelEnd;
    }

    @Override
    public boolean hasPlayerFinished() {
        boolean hasPlayerFinished = false;

        Player player = EntityList.getEntityList().getPlayer();
        float playerX = player.getx();
        float playerY = player.gety();
        float playerWidth = player.getWidth();
        float playerHeight = player.getHeight();


        if(playerX + playerWidth > levelEnd.getX() && playerX < levelEnd.getX() + levelEnd.getWidth()){

            if(playerY > levelEnd.getY() && playerY + playerHeight < levelEnd.getY() + levelEnd.getHeight()){
                hasPlayerFinished = true;
            }
        }
        return hasPlayerFinished;
    }

    @Override
    public void spawnPlayer() {
        mapObjectParser.parsePlayerSpawnPoint(world);
    }


}

