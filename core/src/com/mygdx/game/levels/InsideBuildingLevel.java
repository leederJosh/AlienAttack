package com.mygdx.game.levels;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.assets.AssetHandler;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.EntityList;

/**
 * Handles level 2.
 */
public class InsideBuildingLevel extends AbstractLevel {

    public InsideBuildingLevel() {
        super();
        tiledMap = AssetHandler.getAssetHandler().loadLevel("InsideBuilding1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        //mapObjectParser = new MapObjectParser(tiledMap);
        //entitiesToSpawn = mapObjectParser.parseEnemySpawnPoints();
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
    public Rectangle getLevelEnd() {
        return levelEnd;
    }

    @Override
    public boolean hasPlayerFinished() {
        return false;
    }
}
