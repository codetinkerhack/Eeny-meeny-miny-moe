package com.codetinkerhack;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by evgeniys on 19/04/2017.
 */
abstract public class GameTest {
    
    protected Game game;

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidZeroArguments() {
        game.getSequence(0, 0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidNegativeArguments() {
        game.getSequence(-1, -1);
    }

    @Test
    public void testSixStepOne() {
        Integer[] testSequence = new Integer[] {1, 2, 3, 4, 5, 6};
        List<Integer> sequence = game.getSequence(6, 1);
        assertArrayEquals(testSequence, sequence.toArray(new Integer[0]));
    }


    @Test
    public void testSixStepThree() {
        Integer[] testSequence = new Integer[] {3, 6, 4, 2, 5, 1};
        List<Integer> sequence = game.getSequence(6, 3);
        assertArrayEquals(testSequence, sequence.toArray(new Integer[0]));
    }


    @Test
    public void testTenStepFour() {
        Integer[] testSequence = new Integer[] {4, 8, 2, 7, 3, 10, 9, 1, 6, 5};
        List<Integer> sequence = game.getSequence(10, 4);
        assertArrayEquals(testSequence, sequence.toArray(new Integer[0]));
    }

    @Test
    public void testElevenStepFour() {
        Integer[] testSequence = new Integer[] {4, 8, 1, 6, 11, 7, 3, 2, 5, 10, 9};
        List<Integer> sequence = game.getSequence(11, 4);
        assertArrayEquals(testSequence, sequence.toArray(new Integer[0]));
    }

    @Test
    public void testElevenStepThree() {
        Integer[] testSequence = new Integer[] {3, 6, 9, 1, 5, 10, 4, 11, 8, 2, 7};
        List<Integer> sequence= game.getSequence(11, 3);
        assertArrayEquals(testSequence, sequence.toArray(new Integer[0]));
    }

    @Test
    public void testElevenStepSeven() {
        Integer[] testSequence = new Integer[] {7, 3, 11, 9, 8, 10, 2, 6, 1, 4, 5};
        List<Integer> sequence = game.getSequence(11, 7);
        assertArrayEquals(testSequence, sequence.toArray(new Integer[0]));
    }

    @Test
    public void testFortyStepSeven() {
        Integer[] testSequence = new Integer[] {7, 14, 21, 28, 35, 2, 10, 18, 26, 34, 3, 12, 22, 31, 40, 11, 23, 33, 5, 17,
                30, 4, 19, 36, 9, 27, 6, 25, 8, 32, 16, 1, 38, 37, 39, 15, 29, 13, 20, 24
        };

        List<Integer> sequence = game.getSequence(40, 7);
        assertArrayEquals(testSequence, sequence.toArray(new Integer[0]));
    }


    @Test
    public void testThousandStepHundredCompletes() {
        Object result = game.getSequence(1000, 100);
        assertNotNull(result);
    }

}