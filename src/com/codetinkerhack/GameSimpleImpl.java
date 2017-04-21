package com.codetinkerhack;

import java.util.ArrayList;

/**
 * Created by evgeniys on 19/04/2017.
 */
public class GameSimpleImpl implements Game {

    @Override
    public Integer[] getSequence(int n, int k) {

        if ( n <= 0  || k <= 0) {
            throw new IllegalArgumentException("Values must be non negative, non zero");
        }

        int currentIndex = 0;

        ArrayList<Integer> children = new ArrayList<>(n);
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
