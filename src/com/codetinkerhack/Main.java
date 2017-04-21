package com.codetinkerhack;

public class Main {

    public static void main(String[] args) {
        int n = 0, k = 0;

        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("First argument" + args[0] + " must be an integer.");
            System.exit(1);
        }

        try {
            k = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Second argument" + args[1] + " must be an integer.");
            System.exit(1);
        }

        GameCircularListImpl game = new GameCircularListImpl();
        Integer[] result = game.getSequence(n, k);

        System.out.printf("Winner is %d", result[result.length - 1]);

        System.out.printf("\nElimination sequence is:");
        for (int i = 0; i < result.length - 1; i++) {
            System.out.printf("%d ", result[i]);
        }
    }
}
