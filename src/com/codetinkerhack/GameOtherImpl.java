package com.codetinkerhack;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by evgeniys on 21/04/2017.
 */
public class GameOtherImpl implements Game {

    @Override
    public Integer[] getSequence(int n, int k) {

        if ( n <= 0  || k <= 0) {
            throw new IllegalArgumentException("Values must be non negative, non zero");
        }

        int currentIndex = 0;

        LinkedList<Integer> children = new LinkedList<>();
        ArrayList<Integer> eliminationSequence = new ArrayList<>(n);

        for(int i = 0;i < n;i++){
            children.add(i);
        }

        while(children.size() > 0){
            currentIndex = (currentIndex + k - 1) % children.size();
            eliminationSequence.add(children.get(currentIndex) + 1);
            children.remove(currentIndex);
        }

        return eliminationSequence.toArray(new Integer[0]);
    }
}
