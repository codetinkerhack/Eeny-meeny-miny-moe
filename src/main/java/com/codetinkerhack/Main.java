package com.codetinkerhack;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        int n = 0, k = 0;

        if (args.length == 0) {
            System.out.println("Please specify N and K as arguments");
            System.exit(0);
        }

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
        List<Integer> result = game.getSequence(n, k);

        System.out.printf("Winner is: %d ", result.get(result.size() - 1));

        // Remove winner from elimination sequence.
        result.remove(result.size() - 1);

        System.out.printf("\nElimination sequence is: ");
        for (Integer v : result) {
            System.out.printf("%d ", v);
        }
    }
}
