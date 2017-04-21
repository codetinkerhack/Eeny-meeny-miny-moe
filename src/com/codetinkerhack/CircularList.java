package com.codetinkerhack;

/**
 * Created by evgeniys on 21/04/2017.
 */
public class CircularList<T> {


    private Node current;
    private Node first;
    private Integer size = 0;

    public Node<T> insert(Node<T> insertedNode) {

        if (current != null) {
            size++;

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

        return current;
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

    public Node<T> getCurrent() {
        return current;
    }

    public Node<T> getNext() {
        Node<T> next = current.getNext();
        current = next;
        return next;

    }

    public Node<T> getPrev() {
        Node<T> next = current.getNext();
        current = next;

        return next;
    }

    public static class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> prev;


        public Node(T value) {
            setValue(value);
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getPrev() {
            return prev;
        }

        public void setPrev(Node<T> prev) {
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
