import edu.princeton.cs.algs4.StdOut;

public class ModifiedThreeSum {
    public static void main(String[] args) {
        int[] arr = {-25, -20, -10, 0, 0, 0, 1, 5, 10, 15, 20, 25, 30, 30};
        StdOut.println("Hello");
        

        for (int i = 0; i < arr.length - 2; ++i) {
            int lo = i + 1;
            int hi = arr.length - 1;
            while (lo != hi) {
                if (arr[lo] + arr[hi] == -arr[i]) {
                    StdOut.printf("%d, %d, %d%n", arr[i], arr[lo], arr[hi]);
                    lo = lo + 1;
                    hi = hi + 1;
                    if (hi > arr.length - 1) {
                        break;
                    }
                    // StdOut.printf("more %d, %d, %d%n", i, lo, hi);
                } else if (arr[lo] + arr[hi] < -arr[i]) {
                    // StdOut.printf("less %d, %d, %d%n", arr[i], arr[lo], arr[hi]);
                    lo = lo + 1;
                    // StdOut.printf("idx %d, %d, %d%n", i, lo, hi);
                } else if (arr[lo] + arr[hi] > -arr[i]) {
                    // StdOut.printf("more %d, %d, %d%n", arr[i], arr[lo], arr[hi]);
                    hi = hi - 1;
                    // StdOut.printf("idx %d, %d, %d%n", i, lo, hi);
                }
            }
        }
    }
}
