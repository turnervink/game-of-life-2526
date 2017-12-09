package ca.bcit.comp2526.a2c;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A doubly linked list with head and tail.
 *
 * @author Turner Vink - A01012232
 * @version 1.0
 * @param <E> generic the type of the DoubleLinkedList
 */
public class DoubleLinkedList<E> implements Iterable<E>, Serializable {

    /**
     * A Node in a DoubleLinkedList, containing data.
     *
     * @param <E> generic the type the Node holds
     */
    public static class Node<E> implements Serializable {
        private E data;
        private Node<E> prev;
        private Node<E> next;

        /**
         * Creates a Node.
         *
         * @param data E data to put in the Node
         */
        Node(final E data) {
            this.data = data;
        }

        /**
         * Gets the data stored in a Node.
         *
         * @return E Node data
         */
        public E getData() {
            return data;
        }
    }

    /* The front of the DoubleLinkedList. */
    private Node<E> head;
    /* The end of the DoubleLinkedList. */
    private Node<E> tail;
    /* The number of elements in the list. */
    private int size;

    /**
     * Creates an empty DoubleLinkedList.
     */
    public DoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Adds a new Node to the front of the DoubleLinkedList.
     *
     * @param e the data to put in the new Node
     * @throws CouldNotAddException if the Node could not be added
     */
    public void addToFront(final E e) throws CouldNotAddException {
        Node<E> temp = new Node<>(e);

        if (temp.data == null) {
            throw new CouldNotAddException();
        } else if (isEmpty()) {
            head = temp;
            tail = head;
            size++;
        } else {
            temp.next = head;
            head.prev = temp;
            head = temp;
            size++;
        }
    }

    /**
     * Adds a new Node to the end of the DoubleLinkedList.
     *
     * @param e the data to put in the new Node
     * @throws CouldNotAddException if the Node could not be added
     */
    public void addToEnd(final E e) throws CouldNotAddException {
        Node<E> temp = new Node<>(e);

        if (temp.data == null) {
            throw new CouldNotAddException();
        } else if (isEmpty()) {
            head = temp;
            tail = head;
            size++;
        } else {
            temp.prev = tail;
            tail.next = temp;
            tail = temp;
            size++;
        }
    }

    /**
     * Removes a Node from the front of the DoubleLinkedList and returns
     * its data.
     *
     * @return E the data from the Node
     * @throws CouldNotRemoveException if the Node could not be removed
     */
    public E removeFromFront() throws CouldNotRemoveException {
        if (isEmpty()) {
            throw new CouldNotRemoveException();
        } else {
            Node<E> removed = head;

            if (head != tail) {
                head = head.next;
                head.prev = null;
            } else {
                head = null;
                tail = null;
            }

            size--;
            return removed.data;
        }
    }

    /**
     * Removes a Node from the end of the DoubleLinkedList and returns
     * its data.
     *
     * @return E the data from the Node
     * @throws CouldNotRemoveException if the Node could not be removed
     */
    public E removeFromEnd() throws CouldNotRemoveException {
        if (isEmpty()) {
            throw new CouldNotRemoveException();
        } else {
            Node<E> removed = tail;

            if (tail.prev != null) {
                tail = tail.prev;
                tail.next = null;
            } else {
                head = null;
                tail = null;
            }

            size--;
            return removed.data;
        }
    }

    /**
     * Gets the data of the first element in the list.
     *
     * @return E first element's data
     */
    public E getFirst() {
        if (!isEmpty()) {
            return head.data;
        } else {
            return null;
        }
    }

    /**
     * Gets the data of the last element in the list.
     *
     * @return E last element's data
     */
    public E getLast() {
        if (!isEmpty()) {
            return tail.data;
        } else {
            return null;
        }
    }

    /**
     * Gets the number of elements in the list.
     *
     * @return int
     */
    public int size() {
        return size;
    }

    /**
     * Creates and iterator for the DoubleLinkedList.
     *
     * @return Iterator<E>
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> cur = head;

            @Override
            public boolean hasNext() {
                return cur != null;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E elem = cur.data;
                    cur = cur.next;
                    return elem;
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * Checks if the DoubleLinkedList is empty.
     *
     * @return boolean true if the list is empty
     */
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    /**
     * Prints the DoubleLinkedList from head to tail.
     */
    public void print() {
        if (isEmpty()) {
            System.out.println("empty list");
        } else {
            Node cur = head;
            while (cur != null) {
                System.out.print(cur.data + " ");
                cur = cur.next;
            }
            System.out.println();
        }
    }

}
