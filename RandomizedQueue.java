/* *****************************************************************************
 *  Name: Susan Tan
 *  Date: 07/20/2021
 *  Description: A randomized queue is similar to a stack or queue,
 * except that the item removed is chosen uniformly at random among items
 * in the data structure.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private int capacity;
    private Item[] rq;

    // construct an empty randomized queue
    public RandomizedQueue() {
        capacity = 1;
        size = 0;
        rq = (Item[]) new Object[capacity];

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("arg cannot be null");
        rq[size] = item;
        size++;
        if (size == capacity) resize(2 * capacity);
    }

    private void resize(int newCapacity) {
        capacity = newCapacity;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = rq[i];
        }
        rq = copy;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException("empty queue");
        int n = StdRandom.uniform(size);
        Item rdm = rq[n];

        // IMP** remove item from queue
        rq[n] = null;

        // shift the index
        for (int i = n; i < (size - 1); i++) {
            rq[i] = rq[i + 1];
            rq[i + 1] = rq[i];
        }

        size--;
        if (size > 0 && size == capacity / 4) resize(capacity / 2);
        return rdm;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException("empty queue");
        int n = StdRandom.uniform(size);
        Item rdm = rq[n];
        return rdm;
    }


    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        StdRandom.shuffle(rq, 0, size);
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int itr;

        public boolean hasNext() {
            return itr < size;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove is not supported");
        }

        public Item next() {
            if (rq[itr] == null) {
                throw new java.util.NoSuchElementException();
            }
            else {
                return rq[itr++];
            }

        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rqs = new RandomizedQueue<String>();
/*
        // testing Enqueue
        rqs.enqueue("a");
        rqs.enqueue("b");
        rqs.enqueue("c");
        rqs.enqueue("d");

        // testing Sample
        int j = rqs.size();
        while (j > 0) {
            StdOut.println("Sample = " + rqs.sample());
            j--;
        }

        // testing Dequeue
        int i = rqs.size();
        while (i > 0) {
            StdOut.println("Dequeue = " + rqs.dequeue());
            i--;
        } */

        rqs.enqueue("1");
        rqs.enqueue("2");
        rqs.enqueue("3");
        rqs.enqueue("4");
        // testing Iterator
        Iterator<String> itr = rqs.iterator();
        Iterator<String> itr2 = rqs.iterator();
        while (itr.hasNext()) {
            while (itr2.hasNext()) {
                System.out.println("Iterating INNER = " + itr2.next());
            }
            System.out.println("Iterating OUTER = " + itr.next());
        }

    }

}
