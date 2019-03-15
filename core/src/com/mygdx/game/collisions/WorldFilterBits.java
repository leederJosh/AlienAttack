package com.mygdx.game.collisions;

/**
 * Holds constant short values used to decide what world objects can collide with each other
 * @Author Josh Leeder
 * @Date 15/03/19
 */
public class WorldFilterBits {

    /** Defines collidable bodies like the floor */
    public static final short COLLIDABLE_OBJECT = 0x1;
    /** Defines non-collidable bodies like the entities */
    public static final short ENTITY_OBJECT = 0x1 << 1;

}
