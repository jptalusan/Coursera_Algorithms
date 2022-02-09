import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final int trials;
    private final double[] means;
    private Percolation p;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.trials = trials;
        this.means = new double[trials];
        for (int i = 0; i < trials; ++i) {
            p = new Percolation(n);
            while (!p.percolates()) {
                int randomRow = StdRandom.uniform(1, n + 1);
                int randomCol = StdRandom.uniform(1, n + 1);
                if (!p.isOpen(randomRow, randomCol)) {
                    p.open(randomRow, randomCol);
                }
            }
            // StdOut.println("Number of open sites: " + p.numberOfOpenSites());
            int totalSites = n * n;
            means[i] = (double) p.numberOfOpenSites() / (double) totalSites;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(means);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        for (int i = 0; i < this.trials; ++i) {
        }
        return StdStats.stddev(means);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * stddev() / Math.sqrt(this.trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * stddev() / Math.sqrt(this.trials));
    }

   // test client (see below)
   public static void main(String[] args) {
       if (args.length != 2) {
           throw new IllegalArgumentException();
       }
       int n = Integer.parseInt(args[0]);
       int trials = Integer.parseInt(args[1]);
       
       if (n <= 0 || trials <= 0) {
           throw new IllegalArgumentException();
       }
       PercolationStats ps = new PercolationStats(n, trials);
       double mean = ps.mean();
       double std  = ps.stddev();
       double lo   = ps.confidenceLo();
       double hi   = ps.confidenceHi();
       StdOut.println("mean                     = " + mean);
       StdOut.println("stddev                   = " + std);
       StdOut.println("95% confidence interval  = [" + lo + ", " + hi + "]");
   }

}