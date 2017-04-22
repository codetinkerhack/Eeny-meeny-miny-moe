package com.codetinkerhack;

import java.util.Iterator;

/**
 * Created by evgeniys on 21/04/2017.
 *
 * Simple implementation of doubly linked circular list
 *
 */
public class CircularList<T> {


    private Node<T> current;
    private Node<T> first;
    private Integer size = 0;

    public void add(T value) {
        size++;

        Node<T> insertedNode = new Node<>(value);

        if (current != null) {
            Node<T> next = current.getNext();

            current.setNext(insertedNode);
            insertedNode.setPrev(current);

            insertedNode.setNext(next);
            next.setPrev(insertedNode);

            current = insertedNode;
        } else {
            current = insertedNode;
            first = insertedNode;

            current.setPrev(insertedNode);
            current.setNext(insertedNode);
        }
    }

    public void remove() {
        if (hasNext()) {
            size--;

            if (size == 0) {
                current = null;
                first = null;
            } else {
                Node<T> next = current.getNext();
                Node<T> prev = current.getPrev();

                current.setNext(null);
                current.setPrev(null);

                next.setPrev(prev);
                prev.setNext(next);

                if (current == first) {
                    first = next;
                }

                current = next;
            }
        }
    }

    public boolean hasNext() {
        if (size == 0)
            return false;
        else
            return true;
    }


    public T getCurrent() {
        return current.getValue();
    }

    public T skipK(int k) {

        for(int i = 1; i < k; i++) {
            getNext();
        }
        return getCurrent();
    }


    public T getNext() {

        if (current == null) return  null;

        Node<T> next = current.getNext();
        current = next;
        return next.getValue();

    }

    public T getPrev() {

        if (current == null) return  null;

        Node<T> next = current.getPrev();
        current = next;

        return next.getValue();
    }

    public void reset() {
        current = first;
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> prev;


        public Node(T value) {
            setValue(value);
        }

        private T getValue() {
            return value;
        }

        private void setValue(T value) {
            this.value = value;
        }

        private Node<T> getNext() {
            return next;
        }

        private void setNext(Node<T> next) {
            this.next = next;
        }

        private Node<T> getPrev() {
            return prev;
        }

        private void setPrev(Node<T> prev) {
            this.prev = prev;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node<?> node = (Node<?>) o;

            return value.equals(node.value);
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }

    }
}
