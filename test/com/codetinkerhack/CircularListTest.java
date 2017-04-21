package com.codetinkerhack;

import com.sun.tools.javac.util.GraphUtils;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by evgeniys on 21/04/2017.
 */
public class CircularListTest {
    @Test
    public void insert() throws Exception {
        CircularList<Integer> list = new CircularList<>();

        for (int i = 0; i<10; i++) {
            list.insert(new CircularList.Node<>(i));
        }
        for (int i = 0; i<20; i++) {
            if (list.getNext().getValue() != i % 10) throw new RuntimeException("Element not in order");
        }

    }

    @Test
    public void remove() throws Exception {
        CircularList<Integer> list = new CircularList<>();

        for (int i = 0; i<10; i++) {
            list.insert(new CircularList.Node<>(i));
        }
        for (int i = 0; i<10; i++) {
            list.remove();
        }

        if (!list.hasNext()) throw new RuntimeException("Should be empty");
    }

    @Test
    public void hasNext() throws Exception {
        CircularList<Integer> list = new CircularList<>();
        list.insert(new CircularList.Node<>(0));

        if (list.hasNext()) throw new RuntimeException("Should not be empty");
    }

    @Test
    public void getNext() throws Exception {

    }

    @Test
    public void getPrev() throws Exception {

    }

}