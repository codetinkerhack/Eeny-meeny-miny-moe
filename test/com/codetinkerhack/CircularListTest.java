package com.codetinkerhack;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by evgeniys on 21/04/2017.
 */
public class CircularListTest {

    @Test
    public void testHasNext() throws Exception {
        CircularList<Integer> list = new CircularList<>();

        assertFalse(list.hasNext());

        list.insert(new CircularList.Node<>(0));

        assertTrue(list.hasNext());

        list.remove();

        assertFalse(list.hasNext());
    }

    @Test
    public void testInsertSingle() throws Exception {
        CircularList<Integer> list = new CircularList<>();
        assertNull(list.getNext());
        assertNull(list.getPrev());

        list.insert(new CircularList.Node<>(0));
        assertNotNull(list.getNext());
        assertNotNull(list.getPrev());
        assertTrue(list.getNext().getValue() == 0);
    }

    @Test
    public void testInsertMany() throws Exception {
        CircularList<Integer> list = new CircularList<>();

        for (int i = 0; i<10; i++) {
            list.insert(new CircularList.Node<>(i));
        }
        for (int i = 0; i<20; i++) {
            assertTrue(list.getNext().getValue() == i % 10);
        }

    }

    @Test
    public void testRemove() throws Exception {
        CircularList<Integer> list = new CircularList<>();
        assertNull(list.getNext());
        assertNull(list.getPrev());

        list.insert(new CircularList.Node<>(0));
        list.insert(new CircularList.Node<>(1));
        list.insert(new CircularList.Node<>(2));

        assertTrue(list.getCurrent().getValue() == 2);
        assertTrue(list.getNext().getValue() == 0);
        assertTrue(list.getNext().getValue() == 1);

        list.remove();

        assertTrue(list.getCurrent().getValue() == 2);
        assertTrue(list.getNext().getValue() == 0);
        assertTrue(list.getNext().getValue() == 2);

    }

    @Test
    public void testRemoveMany() throws Exception {
        CircularList<Integer> list = new CircularList<>();

        for (int i = 0; i<10; i++) {
            list.insert(new CircularList.Node<>(i));
        }
        for (int i = 0; i<10; i++) {
            list.remove();
        }

        assertFalse(list.hasNext());
    }

    @Test
    public void testGetNext() throws Exception {
        CircularList<Integer> list = new CircularList<>();
        list.insert(new CircularList.Node<>(0));

        assertNotNull(list.getNext());
        assertNotNull(list.getPrev());
        assertTrue(list.getNext().getValue() == 0);
    }

    @Test
    public void testGetPrev() throws Exception {
        CircularList<Integer> list = new CircularList<>();
        list.insert(new CircularList.Node<>(0));

        assertNotNull(list.getNext());
        assertNotNull(list.getPrev());
        assertTrue(list.getPrev().getValue() == 0);
    }

}