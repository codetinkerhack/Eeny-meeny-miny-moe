package com.codetinkerhack;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by evgeniys on 21/04/2017.
 */
public class GameCircularListImplTest extends GameTest {

    @Before
    public void init() {
        game = new GameCircularListImpl();
    }

    // 21,474,836 Children with K = 1 runs around 17 seconds
    @Ignore
    @Test
    public void testLargeNumberStepOneCompletes() {
        Object result = game.getSequence(Integer.MAX_VALUE / 100, 1);
        assertNotNull(result);
    }

    // 21,474,836 Children with K = 10 runs around 23 seconds
    @Ignore
    @Test
    public void testLargeNumberStepTenCompletes() {
        Object result = game.getSequence(Integer.MAX_VALUE / 100, 10);
        assertNotNull(result);
    }

    // 21,474,836 Children with K = 100 runs around 1m 40 seconds
    @Ignore
    @Test
    public void testLargeNumberStepHundredCompletes() {
        Object result = game.getSequence(Integer.MAX_VALUE / 100, 100);
        assertNotNull(result);
    }

    // 21,474,836 Children with K = 1000 runs around 15m
    @Ignore
    @Test
    public void testLargeNumberStepHThousandCompletes() {
        Object result = game.getSequence(Integer.MAX_VALUE / 100, 1000);
        assertNotNull(result);
    }
}
