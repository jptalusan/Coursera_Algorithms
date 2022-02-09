import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {
    private boolean[][] grid;
    private final WeightedQuickUnionUF uf;
    private static final int srcNode = 0;
    private final int dstNode;
    private final int n;
    private int openSites = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Argument should be > 0");
        }
        this.n = n;
        int gridSquares = n * n;

        grid = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }
        int allSites = gridSquares + 2;
        uf = new WeightedQuickUnionUF(allSites);

        dstNode = allSites - 1;

        for (int i = 1; i <= n; ++i) {
            uf.union(i, srcNode);
            uf.union(dstNode, gridSquares + 1 - i);
        }
    }

    private void isValid(int row, int col) {
        // StdOut.printf("%d,%d%n", row, col);
        if (row < 1 || col < 1 || col > n || row > n) {
            throw new IllegalArgumentException();
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        isValid(row, col);

        if (isOpen(row, col)) {
            return;
        }

        if (!grid[row - 1][col - 1]) {
            openSites++;
            grid[row - 1][col - 1] = true;
        }

        int p = convert2Dto1D(row, col);
        // StdOut.printf("Open: %d - %d,%d - %d%n", n, row, col, p);

        // up
        if (row > 1 && isOpen(row - 1, col)) {
            // StdOut.printf("Connect to up.%n");
            int q = convert2Dto1D(row - 1, col);
            uf.union(p, q);
        }
        // down
        if (row < n && isOpen(row + 1, col)) {
            // StdOut.printf("Connect to down.%n");
            int q = convert2Dto1D(row + 1, col);
            uf.union(p, q);
        }
        // left
        if (col > 1 && isOpen(row, col - 1)) {
            // StdOut.printf("Connect to left.%n");
            int q = convert2Dto1D(row, col - 1);
            uf.union(p, q);
        }
        // right
        if (col < n && isOpen(row, col + 1)) {
            // StdOut.printf("Connect to right.%n");
            int q = convert2Dto1D(row, col + 1);
            uf.union(p, q);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        isValid(row, col);
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        isValid(row, col);
        if (isOpen(row, col)) {
            int srcSet = uf.find(srcNode);
            int q = convert2Dto1D(row, col);
            int qSet = uf.find(q);
            return srcSet == qSet;
        } else {
            return false;
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        if (this.n == 1) {
            return isOpen(0, 0);
        }
        int srcSet = uf.find(srcNode);
        int dstSet = uf.find(dstNode);
        return srcSet == dstSet;
    }

    private int convert2Dto1D(int row, int col) {
        return (row - 1) * this.n + col;
    }

    // test client (optional) use with n = 4
    public static void main(String[] args) {
        int number = Integer.parseInt(args[0]);
        Percolation p = new Percolation(number);
        StdOut.println("Connected components: " + p.uf.count());

        StdOut.println("Number of open sites: " + p.numberOfOpenSites());
        StdOut.println(p.isFull(1, 1));
        StdOut.println(p.isFull(1, 2));
        p.open(1, 4);
        p.open(2, 3);
        p.open(1, 4);
        p.open(2, 4);
        p.open(3, 3);
        p.open(4, 3);
        StdOut.println("Is 3,3 full: " + p.isFull(3, 3));
        StdOut.println("Connected components: " + p.uf.count());
        StdOut.println("Number of open sites: " + p.numberOfOpenSites());
        StdOut.println("Percolates: " + p.percolates());
    }
}