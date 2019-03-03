package com.mygdx.game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.mygdx.game.Pickups.AbstractPickup;
import com.mygdx.game.Pickups.HealthPickup;
import com.mygdx.game.Pickups.PickupHandler;
import com.mygdx.game.entities.*;
import com.mygdx.game.world.AssetHandler;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handles the inputs from the player
 * @Author Sam Robinson
 * @Date 05/12/18
 * @param <T>
 */
public class MyInputProcessor<T> implements Comparable, InputProcessor {

    private
    Camera camera;
    Player player;

    // PickupHandler
    private PickupHandler pickupHandler;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @SuppressWarnings("unchecked")
    public MyInputProcessor(Camera camera, Player player) {
        this.camera = camera;
        this.player = player;
        pickupHandler = new PickupHandler();
    }


    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (Gdx.input.isKeyPressed(Keys.P) || button == Input.Keys.RIGHT || button == Input.Buttons.LEFT) {

            AssetHandler.getAssetHandler().handGunSound();
            //Sound effect from http://soundbible.com/2120-9mm-Gunshot.html

            // World mouse X and Y
            final float rawMouseX = Gdx.input.getX();
            final float rawMouseY = Gdx.input.getY();

            // Map X and Y
            final float mappedMouseX = rawMouseX - Gdx.graphics.getWidth()/2 + camera.position.x;
            final float mappedMouseY = Gdx.graphics.getHeight()/2 - rawMouseY + camera.position.y;

            // This handles the player shooting
            player.shoot(mappedMouseX, mappedMouseY);

        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int compareTo(Object arg0) {
        // TODO Auto-generated method stub
        return 0;
    }
}