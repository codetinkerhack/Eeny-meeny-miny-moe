package com.codetinkerhack;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by evgeniys on 21/04/2017.
 */
public class GameTreeListImplTest extends GameTest {

    @Before
    public void init() {
        game = new GameTreeListImpl();
    }

    // 21,474,836 Children with K = 1 runs around 22 seconds
    @Test
    public void testLargeNumberStepOneCompletes() {
        Object result = game.getSequence(Integer.MAX_VALUE / 100, 1);
        assertNotNull(result);
    }

    // 21,474,836 Children with K = 10 runs around 25 seconds
    @Test
    public void testLargeNumberStepTenCompletes() {
        Object result = game.getSequence(Integer.MAX_VALUE / 100, 10);
        assertNotNull(result);
    }

    // 21,474,836 Children with K = 100 runs around 35 seconds
    @Test
    public void testLargeNumberStepHundredCompletes() {
        Object result = game.getSequence(Integer.MAX_VALUE / 100, 100);
        assertNotNull(result);
    }

    // 21,474,836 Children with K = 1000 runs around 49
    @Test
    public void testLargeNumberStepThousandCompletes() {
        Object result = game.getSequence(Integer.MAX_VALUE / 100, 1000);
        assertNotNull(result);
    }

    // 21,474,836 Children with K = 10000 runs around 1m 4s
    @Test
    public void testLargeNumberStepTenThousandCompletes() {
        Object result = game.getSequence(Integer.MAX_VALUE / 100, 10000);
        assertNotNull(result);
    }

    // 21,474,836 Children with K = 100000 runs around 1m 1s
    @Test
    public void testLargeNumberStepHundredThousandCompletes() {
        Object result = game.getSequence(Integer.MAX_VALUE / 100, 100000);
        assertNotNull(result);
    }
}
