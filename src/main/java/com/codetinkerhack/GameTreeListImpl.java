package com.codetinkerhack;

import org.apache.commons.collections4.list.TreeList;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by evgeniys on 22/04/2017.
 */
public class GameTreeListImpl implements Game {

    @Override
    public Result runSimulation(int n, int k) {

        if ( n <= 0  || k <= 0) {
            throw new IllegalArgumentException("Values must not be negative or zero");
        }

        int currentIndex = 0;

        TreeList<Integer> children = new TreeList<>();
        LinkedList<Integer> eliminationSequence = new LinkedList<>();

        // Initialize sequence
        for(int i = 1;i <= n;i++){
            children.add(i);
        }

        while(children.size() > 1){
            currentIndex = (currentIndex + k - 1) % children.size();
            eliminationSequence.add(children.get(currentIndex));
            children.remove(currentIndex);
        }

        return new Result(children.get(0), eliminationSequence);
    }
}
