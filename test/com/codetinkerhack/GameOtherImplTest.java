package com.codetinkerhack;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by evgeniys on 21/04/2017.
 */
public class GameOtherImplTest extends GameTest {

    @Before
    public void init() {
        game = new GameOtherImpl();
    }

    @Test
    public void testLargeNumberStepHundredCompletes() {
        GameSimpleImpl game = new GameSimpleImpl();
        game.getSequence(Integer.MAX_VALUE / 10, 100);
    }
}
