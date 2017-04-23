package com.codetinkerhack;

/**
 * Created by evgeniys on 20/04/2017.
 */
public interface Game {

    /**
     * This method executes simulation for N children with step K (number of children
     * skipped between eliminations)
     *
     * Validates parameters n, k and raises IllegalArgumentException if n or k are negative or zero
     *
     * @param n number of Children for this simulation
     * @param k step - number of children skipped between eliminations
     *
     * @return Result - tuple of Winner and Elimination sequence
     */
    Result runSimulation(int n, int k);
}
