package com.mygdx.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.awt.*;

import static org.junit.Assert.*;

public class AssetHandlerTest {

    @Before
    public void setUp() throws Exception {
        AssetHandler.getAssetHandler();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDefaultImage(){

        AssetHandler.getAssetHandler().getAssetManager().load("badlogic.jpg", Texture.class);
        assertNotNull(AssetHandler.getAssetHandler().getTexture("badlogic.jpg"));
    }

    @Test
    public void testAssetManagerNotNull(){
        assertNotNull(AssetHandler.getAssetHandler().getAssetManager());
    }

    @Test
    public void loadLevel() {
        TiledMap tiledMap = AssetHandler.getAssetHandler().loadLevel("Level One/FirstLevel.tmx");
        assertNotNull(tiledMap);
    }

    @Test
    public void getTexture() {
    }

    @Test
    public void getTextureAtlas() {
    }

    @Test
    public void resolveJson() {
    }

    @Test
    public void handGunSound() {
    }
}