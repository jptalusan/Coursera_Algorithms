import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last  = null;
    private int size   = 0;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        StdOut.printf("addFirst: %s%n", item);
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

        Item p = null;
        Item n = null;
        if (first.prev != null) p = first.prev.item;
        if (first.next != null) n = first.next.item;
        StdOut.printf("addFirst Prev: %s, Next: %s%n", p, n);
        ++size;
    }

    // add the item to the back
    public void addLast(Item item) {
        StdOut.printf("addLast: %s%n", item);
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

        Item p = null;
        Item n = null;
        if (last.prev != null) p = last.prev.item;
        if (last.next != null) n = last.next.item;
        StdOut.printf("addLast Prev: %s, Next: %s%n", p, n);
        ++size;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        StdOut.printf("removeFirst()%n");
        --size;
        Item item = first.item;
        first = first.next;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        StdOut.printf("removeLast()%n");
        --size;
        Item item = last.item;
        last = last.prev;
        last.next = null;
        StdOut.printf("Last.prev: %s%n", last.item);
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
            Item item = current.item;
            current = current.next;
            return item;
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

        String first = deck.removeFirst();
        String last  = deck.removeLast();
        StdOut.printf("first: %s%n", first);
        StdOut.printf("last: %s%n", last);

        StdOut.println("Current deck...");
        StdOut.printf("Size: %d%n", deck.size());
        for (String s : deck)
            StdOut.println(s);

    }
}
