package com.mygdx.game.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Holds data and behaviour for level 3 of the game
 * @Author Josh Leeder
 * @Date 03/03/19
 */
public class SideWalkRiverLevel extends AbstractLevel {

    public SideWalkRiverLevel() {
        super();
        tiledMap = AssetHandler.getAssetHandler().loadLevel("SidewalkRiverTiles/SidewalkRiver.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

    }

    @Override
    public void spawnEnemies() {

    }

    @Override
    public void spawnFriendlies() {

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
}
