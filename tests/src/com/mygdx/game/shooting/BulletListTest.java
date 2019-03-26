package com.mygdx.game.shooting;

import com.mygdx.game.shooting.bullets.AlienHandgunBullet;
import com.mygdx.game.shooting.bullets.AlienRifleBullet;
import com.mygdx.game.shooting.bullets.HandGunBullet;
import com.mygdx.game.shooting.bullets.ShotGunBullet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BulletListTest {

    @Mock
    HandGunBullet bullet1 = mock(HandGunBullet.class);
    ShotGunBullet bullet2 = mock(ShotGunBullet.class);
    AlienRifleBullet bullet3 = mock(AlienRifleBullet.class);
    AlienHandgunBullet bullet4 = mock(AlienHandgunBullet.class);


    @Before
    public void setUp() throws Exception {
        BulletList.getBulletList();
    }

    @After
    public void tearDown() throws Exception {
        BulletList.getBulletList().clearBulletList();
    }

    @Test
    public void checkOnlyCorrectBulletsAreRemoved() {

        BulletList.getBulletList().addBullet(bullet1);
        BulletList.getBulletList().addBullet(bullet2);
        BulletList.getBulletList().addBullet(bullet3);
        BulletList.getBulletList().addBullet(bullet4);

        assertEquals(4, BulletList.getBulletList().getBullets().size());

        BulletList.getBulletList().addBulletToRemove(bullet1);
        BulletList.getBulletList().addBulletToRemove(bullet2);

        BulletList.getBulletList().removeBullets();
        assertTrue(BulletList.getBulletList().getBullets().size() == 2);

        assertTrue(bullet3 instanceof AlienRifleBullet);
        assertTrue(bullet4 instanceof AlienHandgunBullet);
    }

    @Test
    public void checkDifferentTypeOfBulletsCanBeAdded(){

        assertTrue(BulletList.getBulletList().getBullets().size() == 0);

        BulletList.getBulletList().addBullet(bullet1);
        BulletList.getBulletList().addBullet(bullet4);
        assertTrue(bullet1 instanceof HandGunBullet);
        assertTrue(bullet4 instanceof AlienHandgunBullet);

        assertTrue(BulletList.getBulletList().getBullets().size() == 2);
    }
}