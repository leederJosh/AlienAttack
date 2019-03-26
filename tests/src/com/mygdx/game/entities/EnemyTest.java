package com.mygdx.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class EnemyTest {

    Enemy enemy;

    @Before
    public void setUp() {
        World world = new World(new Vector2(0f, -9.81f), false);
        Enemy enemy = new Enemy(10, 10, world);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void reduceHealth() {
        int startHealth = enemy.getHealth();
        enemy.reduceHealth(10);
        assertEquals(startHealth - 10, enemy.getHealth());
    }
}
