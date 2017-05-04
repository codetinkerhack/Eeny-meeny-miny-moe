package com.codetinkerhack;

import java.util.List;

/**
 * Created by evgeniys on 23/04/2017.
 */
public class Result {

    private Integer winner;
    private List<Integer> eliminationSequence;

    // Default constructor needed for restTemplate tests to construct ResponseEntity<Result>
    public Result() {
    }

    public Result(Integer winner, List<Integer> eliminationSequence) {
        this.winner = winner;
        this.eliminationSequence = eliminationSequence;
    }

    public Integer getWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }

    public List<Integer> getEliminationSequence() {
        return eliminationSequence;
    }

    public void setEliminationSequence(List<Integer> eliminationSequence) {
        this.eliminationSequence = eliminationSequence;
    }

}
