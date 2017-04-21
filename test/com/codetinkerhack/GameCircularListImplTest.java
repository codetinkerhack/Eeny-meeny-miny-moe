package com.codetinkerhack;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by evgeniys on 21/04/2017.
 */
public class GameCircularListImplTest extends GameTest {

    @Before
    public void init() {
        game = new GameCircularListImpl();
    }

    // 2,147,483 Children with K = 1 runs around 17 seconds
    @Ignore
    @Test
    public void testLargeNumberStepOneCompletes() {
        GameCircularListImpl game = new GameCircularListImpl();
        game.getSequence(Integer.MAX_VALUE / 100, 1);
    }

    // 2,147,483 Children with K = 10 runs around 23 seconds
    @Ignore
    @Test
    public void testLargeNumberStepTenCompletes() {
        GameCircularListImpl game = new GameCircularListImpl();
        game.getSequence(Integer.MAX_VALUE / 100, 10);
    }


    // 2,147,483 Children with K = 100 runs around 1m 40 seconds
    @Ignore
    @Test
    public void testLargeNumberStepHundredCompletes() {
        GameCircularListImpl game = new GameCircularListImpl();
        game.getSequence(Integer.MAX_VALUE / 100, 100);
    }

    // 2,147,483 Children with K = 1000 runs around 15m
    @Ignore
    @Test
    public void testLargeNumberStepHThousandCompletes() {
        GameCircularListImpl game = new GameCircularListImpl();
        game.getSequence(Integer.MAX_VALUE / 100, 1000);
    }
}
