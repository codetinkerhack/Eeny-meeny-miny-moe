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
    public void testInvalidZeroArgumentsForN() {
        game.runSimulation(0, 1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidZeroArgumentsForK() {
        game.runSimulation(1, 0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidNegativeArgumentsForN() {
        game.runSimulation(-1, 1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidNegativeArgumentsForK() {
        game.runSimulation(1, -1);
    }

    @Test
    public void testSixStepOne() {
        Integer[] testSequence = new Integer[] {1, 2, 3, 4, 5, 6};
        Result result = game.runSimulation(6, 1);

        List<Integer> sequence = result.getEliminationSequence();
        sequence.add(result.getWinner());

        assertArrayEquals(testSequence, sequence.toArray(new Integer[0]));
    }


    @Test
    public void testSixStepThree() {
        Integer[] testSequence = new Integer[] {3, 6, 4, 2, 5, 1};
        Result result = game.runSimulation(6, 3);

        List<Integer> sequence = result.getEliminationSequence();
        sequence.add(result.getWinner());

        assertArrayEquals(testSequence, sequence.toArray(new Integer[0]));
    }


    @Test
    public void testTenStepFour() {
        Integer[] testSequence = new Integer[] {4, 8, 2, 7, 3, 10, 9, 1, 6, 5};
        Result result = game.runSimulation(10, 4);

        List<Integer> sequence = result.getEliminationSequence();
        sequence.add(result.getWinner());

        assertArrayEquals(testSequence, sequence.toArray(new Integer[0]));
    }

    @Test
    public void testElevenStepFour() {
        Integer[] testSequence = new Integer[] {4, 8, 1, 6, 11, 7, 3, 2, 5, 10, 9};
        Result result = game.runSimulation(11, 4);

        List<Integer> sequence = result.getEliminationSequence();
        sequence.add(result.getWinner());

        assertArrayEquals(testSequence, sequence.toArray(new Integer[0]));
    }

    @Test
    public void testElevenStepThree() {
        Integer[] testSequence = new Integer[] {3, 6, 9, 1, 5, 10, 4, 11, 8, 2, 7};
        Result result = game.runSimulation(11, 3);

        List<Integer> sequence = result.getEliminationSequence();
        sequence.add(result.getWinner());

        assertArrayEquals(testSequence, sequence.toArray(new Integer[0]));
    }

    @Test
    public void testElevenStepSeven() {
        Integer[] testSequence = new Integer[] {7, 3, 11, 9, 8, 10, 2, 6, 1, 4, 5};
        Result result = game.runSimulation(11, 7);

        List<Integer> sequence = result.getEliminationSequence();
        sequence.add(result.getWinner());

        assertArrayEquals(testSequence, sequence.toArray(new Integer[0]));
    }

    @Test
    public void testFortyStepSeven() {
        Integer[] testSequence = new Integer[] {7, 14, 21, 28, 35, 2, 10, 18, 26, 34, 3, 12, 22, 31, 40, 11, 23, 33, 5, 17,
                30, 4, 19, 36, 9, 27, 6, 25, 8, 32, 16, 1, 38, 37, 39, 15, 29, 13, 20, 24
        };

        Result result = game.runSimulation(40, 7);

        List<Integer> sequence = result.getEliminationSequence();
        sequence.add(result.getWinner());

        assertArrayEquals(testSequence, sequence.toArray(new Integer[0]));
    }


    @Test
    public void testThousandStepHundredCompletes() {
        Object result = game.runSimulation(1000, 100);
        assertNotNull(result);
    }

}