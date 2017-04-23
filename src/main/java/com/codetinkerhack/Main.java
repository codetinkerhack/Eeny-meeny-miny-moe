package com.codetinkerhack;

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

        GameTreeListImpl game = new GameTreeListImpl();
        Result result = game.runSimulation(n, k);

        System.out.printf("Winner is: %d ", result.getWinner());

        System.out.printf("\nElimination sequence is: ");
        for (Integer v : result.getEliminationSequence()) {
            System.out.printf("%d ", v);
        }
    }
}
