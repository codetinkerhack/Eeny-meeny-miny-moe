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

        list.add(0);

        assertTrue(list.hasNext());

        list.remove();

        assertFalse(list.hasNext());
    }

    @Test
    public void testInsertSingle() throws Exception {
        CircularList<Integer> list = new CircularList<>();
        assertNull(list.getNext());
        assertNull(list.getPrev());

        list.add(0);
        assertNotNull(list.getNext());
        assertNotNull(list.getPrev());
        assertTrue(list.getNext() == 0);
    }

    @Test
    public void testInsertMany() throws Exception {
        CircularList<Integer> list = new CircularList<>();

        for (int i = 0; i<10; i++) {
            list.add(i);
        }
        for (int i = 0; i<20; i++) {
            assertTrue(list.getNext() == i % 10);
        }

    }

    @Test
    public void testRemove() throws Exception {
        CircularList<Integer> list = new CircularList<>();
        assertNull(list.getNext());
        assertNull(list.getPrev());

        list.add(0);
        list.add(1);
        list.add(2);

        assertTrue(list.getCurrent() == 2);
        assertTrue(list.getNext() == 0);
        assertTrue(list.getNext() == 1);

        list.remove();

        assertTrue(list.getCurrent() == 2);
        assertTrue(list.getNext() == 0);
        assertTrue(list.getNext() == 2);

    }

    @Test
    public void testRemoveMany() throws Exception {
        CircularList<Integer> list = new CircularList<>();

        for (int i = 0; i<10; i++) {
            list.add(i);
        }
        for (int i = 0; i<10; i++) {
            list.remove();
        }

        assertFalse(list.hasNext());
    }

    @Test
    public void testGetNext() throws Exception {
        CircularList<Integer> list = new CircularList<>();
        list.add(0);
        list.add(1);
        list.add(2);

        assertTrue(list.getCurrent() == 2);
        assertTrue(list.getNext() == 0);
        assertTrue(list.getNext() == 1);
    }

    @Test
    public void testGetPrev() throws Exception {
        CircularList<Integer> list = new CircularList<>();
        list.add(0);
        list.add(1);
        list.add(2);

        assertTrue(list.getCurrent() == 2);
        assertTrue(list.getPrev() == 1);
        assertTrue(list.getPrev() == 0);
    }

}