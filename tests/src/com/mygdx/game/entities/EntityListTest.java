package com.mygdx.game.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.TreeMap;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * https://github.com/TomGrill/gdx-testing
 * Above is the source for the default testing setup,
 * Credit goes to: TomGrill
 */



public class EntityListTest {

    Player player = mock(Player.class);
    Enemy enemy = mock(Enemy.class);
    Friendly friendly = mock(Friendly.class);

    @Before
    public void setUp() throws Exception {
        EntityList.getEntityList();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void compareLists() {
        EntityList.updateEntityList(player);
        EntityList.updateEntityList(enemy);
        EntityList.updateEntityList(friendly);
        ArrayList<Entity> expected = new ArrayList<Entity>();
        expected.add(player);
        expected.add(enemy);
        expected.add(friendly);

        assertEquals(expected, EntityList.getListEntities());
    }

    @Test
    public void getEntityList() {
    }

    @Test
    public void checkDeadEntityRemoval() {
        assertEquals(0, EntityList.getListEntities().size());
        assertEquals(0, EntityList.getMapEntities().size());
        EntityList.updateEntityList(enemy);

        assertEquals(1, EntityList.getListEntities().size());
        assertEquals(1, EntityList.getMapEntities().size());

        EntityList.getEntityList().addToRemoval(enemy);
        EntityList.getEntityList().removeDeadEntities();

        assertEquals(0, EntityList.getListEntities().size());
        assertEquals(0, EntityList.getMapEntities().size());

    }

    @Test
    public void checkPlayerPersistsInEntityListAfterPurge() {
        EntityList.updateEntityList(player);
        EntityList.updateEntityList(enemy);
        EntityList.updateEntityList(friendly);

        assertEquals(3, EntityList.getListEntities().size());
        assertEquals(3, EntityList.getMapEntities().size());

        EntityList.purge();
        assertEquals(1, EntityList.getListEntities().size());
        assertEquals(1, EntityList.getMapEntities().size());
        assertEquals(player, EntityList.getListEntities().get(0));

        TreeMap<Double, Entity> mapTest = EntityList.getMapEntities();
        assertTrue(mapTest.containsValue(player));
    }

}