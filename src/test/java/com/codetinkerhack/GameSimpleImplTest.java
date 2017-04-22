package com.codetinkerhack;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by evgeniys on 21/04/2017.
 */
public class GameSimpleImplTest extends GameTest {

    @Before
    public void init() {
        game = new GameSimpleImpl();
    }

    // Aborted. Takes more than 30 min and still running.
    @Ignore
    @Test
    public void testLargeNumberStepOneCompletes() {
        Object result = game.getSequence(Integer.MAX_VALUE / 100, 1);
        assertNotNull(result);
    }
}
