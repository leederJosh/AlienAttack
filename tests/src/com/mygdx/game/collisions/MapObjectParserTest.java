package com.mygdx.game.collisions;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.levels.AbstractLevel;
import com.mygdx.game.levels.AlleyWayLevel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MapObjectParserTest {

    AbstractLevel abstractLevel = mock(AlleyWayLevel.class);
    World world = abstractLevel.getWorld();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void parseFloorObjectLayerTest() {

        // Need to see if the world has any objects in it (needs to be null)
        // Then call the parse object layer
        // See if the world has objects in it
        assertEquals(0, world.getBodyCount());
    }

    @Test
    public void parsePlayerSpawnPoint() {
    }

    @Test
    public void parseEntitySpawnPoints() {
    }

    @Test
    public void parseBossSpawn() {
    }

    @Test
    public void parseTransitionObjects() {
    }

    @Test
    public void parseBoundaryObjects() {
    }
}