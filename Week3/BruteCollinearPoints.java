import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private int segmentCount;
    private LineSegment[] ls;
    
    // Slope is vital
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException();
            }
        }

        Arrays.sort(points);
        for (int i = 0; i < points.length - 1; ++i) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        

        segmentCount = 0;
        ls = new LineSegment[points.length];

        for (int j = 0; j < points.length; ++j) {
            for (int k = j + 1; k < points.length; ++k) {
                for (int l = k + 1; l < points.length; ++l) {
                    for (int m = l + 1; m < points.length; ++m) {
                        double s0 = points[j].slopeTo(points[k]);
                        double s1 = points[j].slopeTo(points[l]);
                        double s2 = points[j].slopeTo(points[m]);
                        // StdOut.printf("%f, %f, %f: ", s0, s1, s2);
                        if ((s0 == s1) && (s1 == s2) && (s0 == s2)) {
                            // StdOut.printf("Straight: %s %s %s %s%n", points[j], points[k], points[l], points[m]);
                            // Check if longest
                            ls[segmentCount++] = new LineSegment(points[j], points[m]);
                            // StdOut.printf("%s, %s, %s%n", points[j], points[m], ls[segmentCount]);
                            // break;
                        } else {
                            // StdOut.printf("Tried: %s %s %s %s%n", points[j], points[k], points[l], points[m]);
                        }
                    }
                }
            }
        }
    }

    public int numberofSegments() {
        return segmentCount;
    }

    public LineSegment[] segments() {
        return ls;
    }

    public static void main(String[] args) {
        int length = 10;
        Point[] p = new Point[length];
        p[0] = new Point(10, 15);
        p[1] = new Point(1, 1);
        p[2] = new Point(2, 2);
        p[3] = new Point(3, 3);
        p[4] = new Point(1, 2);
        p[5] = new Point(2, 4);
        p[6] = new Point(4, 8);
        p[7] = new Point(8, 16);
        p[8] = new Point(4, 2);
        p[9] = null;

        for (Point _p : p) {
            if (_p == null) {
                throw new IllegalArgumentException();
            }
        }

        StdOut.println("Before sort:");
        for (int i = 0; i < length; i++) {
            System.out.println(p[i]);
        }
        Arrays.sort(p);
        // Arrays.sort(p, p[8].slopeOrder());
        StdOut.println();
        StdOut.println("After sort:");
        for (int i = 0; i < length; i++) {
            System.out.println(p[i]);
        }

        StdOut.println();
        BruteCollinearPoints bcp = new BruteCollinearPoints(p);
        
        for (int i = 0; i < bcp.numberofSegments(); ++i) {
            StdOut.println(bcp.segments()[i]);
        }

    }
}
