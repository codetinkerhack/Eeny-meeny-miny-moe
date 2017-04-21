package com.codetinkerhack;

import org.junit.Before;

/**
 * Created by evgeniys on 21/04/2017.
 */
public class GameSimpleImplTest extends GameTest {

    @Before
    public void init() {
        game = new GameSimpleImpl();
    }
}
