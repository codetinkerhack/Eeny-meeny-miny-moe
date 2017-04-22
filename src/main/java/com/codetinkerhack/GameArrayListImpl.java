package com.codetinkerhack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by evgeniys on 19/04/2017.
 */
public class GameArrayListImpl implements Game {

    @Override
    public List<Integer> getSequence(int n, int k) {

        if ( n <= 0  || k <= 0) {
            throw new IllegalArgumentException("Values must not be negative or zero");
        }

        int currentIndex = 0;

        ArrayList<Integer> children = new ArrayList<>(n);
        LinkedList<Integer> eliminationSequence = new LinkedList<>();

        for(int i = 1;i <= n;i++){
            children.add(i);
        }

        while(children.size() > 0){
            currentIndex = (currentIndex + k - 1) % children.size();
            eliminationSequence.add(children.get(currentIndex));
            children.remove(currentIndex);
        }

        return eliminationSequence;
    }

}
