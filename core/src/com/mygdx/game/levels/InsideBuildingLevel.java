package com.mygdx.game.levels;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ai.AIHandler;
import com.mygdx.game.assets.AssetHandler;
import com.mygdx.game.collisions.MapObjectParser;
import com.mygdx.game.collisions.MyContactListener;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.EntityList;

/**
 * Handles level 2.
 */
public class InsideBuildingLevel extends AbstractLevel {


    public InsideBuildingLevel() {
        super();
        aiHandler = new AIHandler(this);
        tiledMap = AssetHandler.getAssetHandler().loadLevel("Level Two/InsideBuilding.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1  / scale);
        world = new World(new Vector2(0f, -9.81f), false);
        mapObjectParser = new MapObjectParser(world, tiledMap);
        mapObjectParser.parseFloorObjectLayer();
        entitiesToSpawn = mapObjectParser.parseEntitySpawnPoints();
        levelEnd = mapObjectParser.parseTransitionObjects();
        mapObjectParser.parseBoundaryObjects();
        aiHandler = new AIHandler(this); // DON'T THINK WE NEED THIS, THEY ACT WITHOUT IT SOMEWHERE
        world.setContactListener(new MyContactListener());
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
    public void spawnPlayer() {
        mapObjectParser.parsePlayerSpawnPoint(world);
    }

    @Override
    public void refreshEntities() {
        entitiesToSpawn.clear();
        entitiesToSpawn = mapObjectParser.parseEntitySpawnPoints();
    }

    @Override
    public void stopMusic() {

    }

    @Override
    public void playMusic() {

    }
}
