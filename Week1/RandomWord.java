import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int i = 1;
        String champion = "";
        String contender = "";
        champion = StdIn.readString();
        while (!StdIn.isEmpty()) {
            contender = StdIn.readString();
            i++;
            boolean prob = StdRandom.bernoulli(1.0 / (double) i);
            if (prob) {
                champion = contender;
            }
        }
        StdOut.println(champion);
    }
}
// Use javac RandomWord then enter the space delimited list and then hit ctrl+z or ctrl+d