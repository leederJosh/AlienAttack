package com.mygdx.game;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.entities.Enemy;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.EntityList;
import com.mygdx.game.entities.EntityType;
import com.mygdx.game.entities.Friendly;
import com.mygdx.game.entities.Player;

//TODO: For melee enemies, check to see if on top of enemy by using the enemy with hypotenuse 0.
public class MyInputProcessor<T> implements Comparable, InputProcessor {

    private
    HashMap<Entity, Double> bulletMap;
    Camera camera;
    Player player;
    //SoundManager soundManager;
    Sound sound;
    String path;

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
        //this.soundManager = new SoundManager();
        this.path = AlienGame.PROJECT_PATH.replace("desktop", "core/assets");
    }

    public MyInputProcessor(Camera camera) {
        this.camera = camera;
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
        EntityType entityType;
        Entity e = null;
        float playerX = EntityList.getListEntities().get(0).getx();
        float playerY = EntityList.getListEntities().get(0).gety();
        ///If the left mouse button is pressed...
        System.out.println("touch down");
        if (Gdx.input.isKeyPressed(Keys.P) || button == Input.Keys.RIGHT || button == Input.Buttons.LEFT) {

            //Then run the shooting mechanics
            //playerX = player.getX();
            //playerY = player.gety();
            //get mouseX and mouseY
            //for loop, iterating through each entity...
            //entityX = entity.getX();
            //entityY = entity.getY();
            //if  entityX < mouseX < entityX + width
            // && entityY < mouseY < entityY + height then...
            //entity.reduceHealth();

            //Play a gunshot sound effect
            sound = Gdx.audio.newSound(Gdx.files.internal(path + "/pistol.mp3"));
            sound.play();
            //Sound effect from http://soundbible.com/2120-9mm-Gunshot.html

            System.out.println("fire gun");

            Double i = (double) 1;
            boolean entityFound = false;
            Object[] keys = EntityList.getMapEntities().keySet().toArray();

            System.out.println(keys.toString());

            System.out.println(camera.position);

            while ((i <= EntityList.getListEntities().size()-1) && entityFound == false) {

                e = EntityList.getMapEntities().get(keys[i.intValue()]);
                float entityX = e.getx();
                float entityY = e.gety();

                final float rawMouseX = Gdx.input.getX();
                final float rawMouseY = Gdx.input.getY();

                final float mappedMouseX = rawMouseX - Gdx.graphics.getWidth()/2 + camera.position.x;
                final float mappedMouseY = Gdx.graphics.getHeight()/2 - rawMouseY + camera.position.y;
                System.out.println(String.format("Mapped mouse coords: (%f, %f)", mappedMouseX, mappedMouseY));

                //Aiming to the right
                if ((entityX < mappedMouseX) && (mappedMouseX > entityX + e.getType().getWidth())) {
                    if (playerX < entityX) {
                        if ((entityY < mappedMouseY && (mappedMouseY < entityY + e.getType().getHeight()))) {
                            entityType = e.getType();
                            e.reduceHealth(10);
                            System.out.print("Health: " + e.getHealth());
                            System.out.println(entityType + " " + e);
                            entityFound = true;
                        }
                    }
                }

                //Aiming to the left
                else if ((entityX > mappedMouseX) && (mappedMouseX < entityX + e.getType().getWidth())) {
                    if  (playerX > entityX) {
                        if ((entityY < mappedMouseY && (mappedMouseY < entityY + e.getType().getHeight()))) {
                            entityType = e.getType();
                            e.reduceHealth(10);
                            System.out.print("Health: " + e.getHealth());
                            System.out.println(entityType + " " + e);
                            entityFound = true;
                        }
                    }
                }

                System.out.println("PlayerX: " + playerX + " PlayerY: " + playerY);
                System.out.println("EntityX: " + entityX + " EntityY: " + entityY);
                i++;
            }
        }

        //Only increase humanity when an enemy dies
        if (e.isDead() == true && e instanceof Enemy) {
            player.decreaseHumanity(-10);

            //Remove the entity from the EntityList
            ArrayList<Entity> tempList = EntityList.getListEntities();
            tempList.remove(e);
            EntityList.clear();

            //Add player to element 0
            //Open for loop with i at 1
            //Add entities from temp list to concrete list
            //Update map via the list



            EntityList.setListEntities(tempList);
            //EntityList.checkCollections();

            System.out.println(EntityList.getListEntities());
            System.out.println(EntityList.getMapEntities());
        }

        //Decrease humanity every time a friendly is hit
        if (e instanceof Friendly) {
            player.decreaseHumanity(10);

            if (e.isDead() == true) {
                //Remove entity from the EntityList
                ArrayList<Entity> tempList = EntityList.getListEntities();
                tempList.remove(e);
                EntityList.setListEntities(tempList);
            }

            System.out.println("Humanity: " + player.getHumanity());
            return true;
        }
        else {
            System.out.println("Humanity: " + player.getHumanity());
            return false;
        }
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

    public void addToBulletMap(Entity e, Double hyp) {
        bulletMap.put(e, hyp);
    }

    public void clearBulletMap() {
        bulletMap.clear();
    }

    public HashMap<Entity, Double> getBulletMap(){
        return bulletMap;
    }
}