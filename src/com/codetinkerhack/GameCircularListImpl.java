package com.codetinkerhack;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by evgeniys on 21/04/2017.
 */
public class GameCircularListImpl implements Game {

    @Override
    public Integer[] getSequence(int n, int k) {

        if ( n <= 0  || k <= 0) {
            throw new IllegalArgumentException("Values must be non negative, non zero");
        }

        CircularList<Integer> children = new CircularList<>();
        ArrayList<Integer> eliminationSequence = new ArrayList<>(n);

        for(int i = 1;i <= n;i++){
            children.insert(new CircularList.Node<>(i));
        }
        children.reset();

        while(children.hasNext()){
            skipK(children, k);
            eliminationSequence.add(children.getCurrent().getValue());
            children.remove();
        }

        return eliminationSequence.toArray(new Integer[0]);
    }

    private void skipK(CircularList children, int k) {
        for(int i = 1; i < k; i++) {
            children.getNext();
        }
    }
}
