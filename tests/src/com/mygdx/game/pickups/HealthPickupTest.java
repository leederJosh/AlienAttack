package com.mygdx.game.pickups;

import com.mygdx.game.entities.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class HealthPickupTest {

    HealthPickup healthPickup = mock(HealthPickup.class);
    Player player = mock(Player.class);

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void pickUpIncreasesPlayerHealth() {

        int health = 10;
        when(player.getHealth()).thenReturn(health);
        assertEquals(10, player.getHealth());




    }
}