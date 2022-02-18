import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] queue;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        queue = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (size == 0);
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (size == queue.length) resize(2 * queue.length);
        queue[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        verifySize();
        int i = StdRandom.uniform(size);
        // StdOut.printf("sample: %d:", i);
        Item item = queue[i];
        --size;
        queue[i] = queue[size];
        queue[size] = null;
        if (size > 0 && size == queue.length/4) resize(queue.length/2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        verifySize();
        int i = StdRandom.uniform(size);
        // StdOut.printf("sample: %d", i);
        return queue[i];
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        // TODO: Change to iterator
        for (int i = 0; i < size; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
    }

    private void verifySize() {
        if (isEmpty()) throw new NoSuchElementException();
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private int i = 0;
        private final int[] order = new int[size];
        
        public RandomIterator() {
            for (int idx = 0; idx < size; ++idx) {
                order[idx] = idx;
            }
            StdRandom.shuffle(order);
        }

        public boolean hasNext() {
            return (i < size);
        }

        public Item next() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            } else {
                return queue[order[i++]];
            }
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        StdOut.printf("Size: %d%n", rq.size());

        rq.enqueue("T");
        rq.enqueue("A");
        rq.enqueue("L");
        rq.enqueue("U");
        rq.enqueue("S");
        rq.enqueue("A");
        rq.enqueue("N");

        StdOut.printf("sample: %s%n", rq.sample());
        StdOut.printf("sample: %s%n", rq.sample());

        for (String s : rq)
            StdOut.printf("%s", s);
        StdOut.println();

        StdOut.println(rq.dequeue());
        for (String s : rq)
            StdOut.printf("%s", s);
        StdOut.println();

        StdOut.printf("Size: %d%n", rq.size());
        StdOut.printf("isEmpty: %b%n", rq.isEmpty());
        rq.resize(10);
    }
}