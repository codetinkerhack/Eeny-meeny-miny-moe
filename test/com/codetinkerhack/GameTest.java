package com.codetinkerhack;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by evgeniys on 19/04/2017.
 */
public class GameTest {

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidZeroArguments() {
        GameSimpleImpl game = new GameSimpleImpl();
        game.getSequence(0, 0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidNegativeArguments() {
        GameSimpleImpl game = new GameSimpleImpl();
        game.getSequence(-1, -1);
    }

    @Test
    public void testSixStepOne() {
        Integer[] testSequence = new Integer[] {1, 2, 3, 4, 5, 6};
        GameSimpleImpl game = new GameSimpleImpl();
        Integer[] sequence = game.getSequence(6, 1);
        assertEqual(testSequence, sequence);
    }


    @Test
    public void testSixStepThree() {
        Integer[] testSequence = new Integer[] {3, 6, 4, 2, 5, 1};
        GameSimpleImpl game = new GameSimpleImpl();
        Integer[] sequence = game.getSequence(6, 3);
        assertEqual(testSequence, sequence);
    }


    @Test
    public void testTenStepFour() {
        Integer[] testSequence = new Integer[] {4, 8, 2, 7, 3, 10, 9, 1, 6, 5};
        GameSimpleImpl game = new GameSimpleImpl();
        Integer[] sequence = game.getSequence(10, 4);
        assertEqual(testSequence, sequence);
    }

    @Test
    public void testElevenStepFour() {
        Integer[] testSequence = new Integer[] {4, 8, 1, 6, 11, 7, 3, 2, 5, 10, 9};
        GameSimpleImpl game = new GameSimpleImpl();
        Integer[] sequence = game.getSequence(11, 4);
        assertEqual(testSequence, sequence);
    }

    @Test
    public void testElevenStepThree() {
        Integer[] testSequence = new Integer[] {3, 6, 9, 1, 5, 10, 4, 11, 8, 2, 7};
        GameSimpleImpl game = new GameSimpleImpl();
        Integer[] sequence = game.getSequence(11, 3);
        assertEqual(testSequence, sequence);
    }

    @Test
    public void testElevenStepSeven() {
        Integer[] testSequence = new Integer[] {7, 3, 11, 9, 8, 10, 2, 6, 1, 4, 5};
        GameSimpleImpl game = new GameSimpleImpl();
        Integer[] sequence = game.getSequence(11, 7);
        assertEqual(testSequence, sequence);
    }

    @Test
    public void testFourtyStepSeven() {
        Integer[] testSequence = new Integer[] {7, 14, 21, 28, 35, 2, 10, 18, 26, 34, 3, 12, 22, 31, 40, 11, 23, 33, 5, 17,
                30, 4, 19, 36, 9, 27, 6, 25, 8, 32, 16, 1, 38, 37, 39, 15, 29, 13, 20, 24
        };

        GameSimpleImpl game = new GameSimpleImpl();
        Integer[] sequence = game.getSequence(40, 7);
        assertEqual(testSequence, sequence);
    }


    @Test
    public void testThousandStepHundredCompletes() {
        GameSimpleImpl game = new GameSimpleImpl();
        game.getSequence(1000, 100);
    }


    // Ignoring as this test does not complete in reasonable time - something to consider.
    // Does our N need to be that large?
    @Ignore
    @Test
    public void testLargeNumberStepHundredCompletes() {
        GameSimpleImpl game = new GameSimpleImpl();
        game.getSequence(Integer.MAX_VALUE / 10, 100);
    }

    // Could have used Mockito but thought to leave it this way instead of bringing another dependency (until it is needed).
    private void assertEqual(Integer[] testSequence, Integer[] sequence) {
        if (testSequence.length != sequence.length) {
            System.out.println("Sequences have different length");
            throw new RuntimeException("Sequences have different length");
        }

        for (int i=0; i < testSequence.length; i++) {
            if (testSequence[i] != sequence[i]) {
                throw new RuntimeException(String.format("Elements do not match: %d, %d", testSequence[i], sequence[i]));
            } else {
                System.out.printf("\n%d == %d", testSequence[i], sequence[i]);
            }
        }
    }

}