package com.mygdx.game.ai;

import com.mygdx.game.entities.Entity;
import com.mygdx.game.levels.AbstractLevel;

/**
 * Standard Artificial Intelligence behaviour
 * Currently will apply movement to all entities
 * @Author Josh Leeder
 * @Date 27/02/19
 */
public abstract class AI {

    /**
     * The level the entity ill move in
     */
    private AbstractLevel level;


    public AI(AbstractLevel level) {
        this.level = level;
    }


    public abstract void act(Entity entity);
}