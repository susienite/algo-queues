/* *****************************************************************************
 *  Name: Susan Tan
 *  Date: 07/08/2020
 *  Description: Stack/Queue via LinkedList (double)
 **************************************************************************** */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node rear;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        rear = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("item cannot be null");
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        if (isEmpty()) {
            rear = first;
        }
        else {
            first.next = oldfirst;
            oldfirst.prev = first;
        }
        size++;
    }

    // add the item to the back
    // identical to Queue: linked-list implementation
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("item cannot be null");
        Node oldlast = rear;
        rear = new Node();
        rear.item = item;
        if (isEmpty()) {
            first = rear;
        }
        else {
            oldlast.next = rear;
            rear.prev = oldlast;
        }
        size++;
    }

    // remove and return the item from the front
    // identical to Queue: linked-list implementation
    public Item removeFirst() {
        if (isEmpty()) throw new java.util.NoSuchElementException("Empty Deque");
        Item fitem = first.item;
        first = first.next;
        size--;
        return fitem;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new java.util.NoSuchElementException("Empty Deque");
        Item litem = rear.item;

        // get rid of last element, have the rear point to the element before rear ??
        rear = rear.prev;  // IMP** only shifted the pointers)
        if (rear != null) rear.next = null;  // need to get rid of element

        size--;
        return litem;
    }


    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove is not supported");
        }

        public Item next() {
            if (current == null) { // current.item means null.null is nullpointer e
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
 
    }
}
