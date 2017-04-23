package com.codetinkerhack;

import java.util.List;

/**
 * Created by evgeniys on 23/04/2017.
 */
public class Result {
    private final Integer winner;
    private final List<Integer> eliminationSequence;

    public Result(Integer winner, List<Integer> eliminationSequence) {
        this.winner = winner;
        this.eliminationSequence = eliminationSequence;
    }

    public Integer getWinner() {
        return winner;
    }

    public List<Integer> getEliminationSequence() {
        return eliminationSequence;
    }
}
