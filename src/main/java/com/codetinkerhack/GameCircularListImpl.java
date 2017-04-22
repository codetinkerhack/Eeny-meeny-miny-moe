package com.codetinkerhack;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by evgeniys on 21/04/2017.
 */
public class GameCircularListImpl implements Game {

    @Override
    public List<Integer> getSequence(int n, int k) {

        if ( n <= 0  || k <= 0) {
            throw new IllegalArgumentException("Values must not be negative or zero");
        }

        CircularList<Integer> children = new CircularList<>();
        LinkedList<Integer> eliminationSequence = new LinkedList<>();

        // Initialize sequence
        for(int i = 1;i <= n;i++){
            children.add(i);
        }

        // Reset sequence to first element
        children.reset();

        // run main elimination loop
        while(children.hasNext()){
            eliminationSequence.add(children.skipK(k));
            children.remove();
        }

        return eliminationSequence;
    }
}
