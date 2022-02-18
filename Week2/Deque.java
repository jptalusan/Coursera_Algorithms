import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    private void validateArgument(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }

    // add the item to the front
    public void addFirst(Item item) {
        validateArgument(item);
        if (size == 0) {
            Node oldfirst = first;
            Node oldLast = last;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            first.prev = oldLast;
            last = first;
        } else {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            oldfirst.prev = first;
        }
        ++size;
    }

    // add the item to the back
    public void addLast(Item item) {
        validateArgument(item);
        if (size == 0) {
            Node oldfirst = first;
            Node oldLast = last;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            first.prev = oldLast;
            last = first;
        } else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if (oldLast != null) {
                oldLast.next = last;
                last.prev = oldLast;
            }
        }
        ++size;
    }

    private void verifySize() {
        if (isEmpty()) throw new NoSuchElementException();
    }

    // remove and return the item from the front
    public Item removeFirst() {
        verifySize();
        --size;
        Item item = first.item;
        first = first.next;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        verifySize();
        --size;
        Item item = last.item;
        last = last.prev;
        last.next = null;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (size <= 0) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        StdOut.println("Hello world!");
        Deque<String> deck = new Deque<>();
        deck.addFirst("A");
        deck.addLast("L");
        deck.addFirst("T");
        deck.addLast("U");
        deck.addLast("S");
        deck.addLast("A");
        deck.addLast("N");

        StdOut.println("Current deck...");
        StdOut.printf("Size: %d%n", deck.size());
        for (String s : deck)
            StdOut.println(s);

        StdOut.printf("Is Empty?: %b%n", deck.isEmpty());

        String first = deck.removeFirst();
        String last  = deck.removeLast();
        StdOut.printf("first: %s%n", first);
        StdOut.printf("last: %s%n", last);

        StdOut.println("Current deck...");
        StdOut.printf("Size: %d%n", deck.size());
        for (String s : deck)
            StdOut.println(s);

        // Verify null inserts
        try {
            StdOut.print("Try addFirst(null):");
            deck.addFirst(null);
        } catch (IllegalArgumentException e) {
            StdOut.println(e);
        }
        try {
            StdOut.print("Try addLast(null):");
            deck.addLast(null);
        } catch (IllegalArgumentException e) {
            StdOut.println(e);
        }

        // Verify empty removes
        deck = new Deque<>();
        StdOut.printf("Is Empty?: %b%n", deck.isEmpty());

        try {
            StdOut.print("Try removeFirst() an empty deck:");
            deck.removeFirst();
        } catch (NoSuchElementException e) {
            StdOut.println(e);
        }
        try {
            StdOut.print("Try removeLast() an empty deck:");
            deck.removeLast();
        } catch (NoSuchElementException e) {
            StdOut.println(e);
        }

        // test next when no items to return
        Iterator<String> i = deck.iterator();
        try {
            StdOut.print("Try next() an empty deck:");
            i.next();
        } catch (NoSuchElementException e) {
            StdOut.println(e);
        }

        // try iterator.remove()
        try {
            StdOut.print("Try iterator.remove():");
            i.remove();
        } catch (UnsupportedOperationException e) {
            StdOut.println(e);
        }
    }
}
