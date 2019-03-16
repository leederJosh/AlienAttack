
package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.mygdx.game.assets.AssetHandler;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.entities.EntityList;
import com.mygdx.game.entities.Player;

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


    @SuppressWarnings("unchecked")
    public MyInputProcessor(Camera camera) {
        this.camera = camera;
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
            if (camera == null) {
                camera = new OrthographicCamera();
            }
            final float mappedMouseX = (rawMouseX - Gdx.graphics.getWidth()/2 + camera.position.x);
            final float mappedMouseY = (Gdx.graphics.getHeight()/2 - rawMouseY + camera.position.y);

            // This handles the player shooting
            EntityList.getEntityList().getPlayer().shoot(mappedMouseX, mappedMouseY); // This is now null after I change the players spawn to a map object

        }
        return false;
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setCamera (OrthographicCamera camera) {
        this.camera = camera;
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
}