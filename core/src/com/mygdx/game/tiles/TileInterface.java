package com.mygdx.game.tiles;


/**
 * An interface for enforcing behaviour needed in all enums implementing this interface
 * @Author Nathan Batchelor
 * @Date 06/03/19
 */
public interface TileInterface {

    int TILE_SIZE = 16;

    int getId();

    boolean isCollidable();

    String getName();

    float getDamage();

}
