package com.mygdx.game.entities;

import com.mygdx.game.game.AlienGame;

public enum EntityType {
    //creating all entity types
    PLAYER("player", 14, 32, 40), ENEMY("enemy", 14, 32, 40), FRIENDLY("friendly", 14, 32, 40), BOSS("enemy", 60, 64, 40);
    private String id;
    private float width, height;
    private float weight;

    private EntityType(String id, float width, float height, float weight) {
        this.id = id;
        this.width = width  / AlienGame.ppm;
        this.height = height  / AlienGame.ppm;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }


}