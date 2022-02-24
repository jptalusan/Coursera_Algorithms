import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

public class FastCollinearPoints {
    private int segmentCount;
    private LineSegment[] ls;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        // Catching Exceptions
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException();
            }
        }
        // How to detect duplicates?
        Arrays.sort(points);
        for (Point _cp : points) System.out.println(_cp);
        
        for (int i = 0; i < points.length - 1; ++i) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        System.out.println();
        // Algo Proper
        Point[] cloneP = new Point[points.length - 1];

        for (int i = 0; i < points.length; ++i) {
            StdOut.printf("I: \t %s%n", points[i]);
            
            // Create array without the current point
            for (int j = 0, k = 0; j < points.length; ++j) {
                if (points[j].compareTo(points[i]) != 0) {
                    cloneP[k++] = points[j];
                }
            }

            Arrays.sort(cloneP, points[i].slopeOrder());
            double currSlope = points[i].slopeTo(cloneP[0]);
            int count = 1;
            Point minPt = points[0];
            Point maxPt = points[points.length - 1];

            for (int x = 1; x <= cloneP.length - 1; ++x) {
                StdOut.printf("%s currSlope: %f%n", cloneP[x], currSlope);
                if (currSlope == points[i].slopeTo(cloneP[x])) {
                    count = count + 1;
                    if (cloneP[x].compareTo(maxPt) > 0) {
                        maxPt = cloneP[x];
                    }
                    if (cloneP[x].compareTo(minPt) < 0) {
                        minPt = cloneP[x];
                    }
                } else {
                    if (count >= 3) {
                        StdOut.printf("MinPt: %s MaxPt: %s%n", minPt, maxPt);
                    }
                    currSlope = points[i].slopeTo(cloneP[x]);
                    count = 1;
                }
            }
            
            // for (Point _cp : cloneP) System.out.println(_cp);
            // break;
        }


    }

    // the number of line segments
    public int numberOfSegments() {
        return segmentCount;
    }

    // the line segments
    public LineSegment[] segments() {
        return ls;
    }
    
    public static void main(String[] args) {
        // Sample client
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        FastCollinearPoints collinear = new FastCollinearPoints(points);

        // // draw the points
        // StdDraw.enableDoubleBuffering();
        // StdDraw.setXscale(0, 32768);
        // StdDraw.setYscale(0, 32768);
        // for (Point p : points) {
        //     p.draw();
        // }
        // StdDraw.show();

        // // print and draw the line segments
        // FastCollinearPoints collinear = new FastCollinearPoints(points);
        // for (LineSegment segment : collinear.segments()) {
        //     StdOut.println(segment);
        //     segment.draw();
        // }
        // StdDraw.show();
    }
}

/*
for (Point _cp : points) System.out.println(_cp);

        
segmentCount = 0;
ls = new LineSegment[points.length / 3];

Point[] cloneP = new Point[points.length - 1];
// StdOut.println(cloneP.length);

// Do i need to loop through each point? Results in duplicates
// Maybe keep all the remaining point that were not part of any segments?
for (int i = 0; i < points.length; ++i) {
    StdOut.printf("I: \t %s%n", points[i]);
    
    // Create array without the current point
    for (int j = 0, k = 0; j < points.length; ++j) {
        if (points[j].compareTo(points[i]) != 0) {
            cloneP[k] = points[j];
            k = k + 1;
        }
    }
    Arrays.sort(cloneP, points[i].slopeOrder());
    for (Point _cp : cloneP) {
        // System.out.println(_cp);
        StdOut.printf("slope: %f %s%n", points[i].slopeTo(_cp), _cp);
    }

    int count = 1;
    int start = -1;
    Point minPt = points[0];
    Point maxPt = points[points.length - 1];
    double currSlope = points[i].slopeTo(cloneP[0]);
    for (int x = 1; x <= cloneP.length; ++x) {
        if (x == cloneP.length) {
            if (count >= 3) {
                // ls[segmentCount++] = new LineSegment(points[i], cloneP[start + count - 1]);
                StdOut.printf("A SEGMENT: %s, ", points[i]);
                for (int y = start; y < start + count; ++y) {
                    StdOut.printf("%s, ", cloneP[y]);
                }
                StdOut.printf("%n");
            }
            break;
        }
        if (currSlope == points[i].slopeTo(cloneP[x])) {
            // StdOut.println("same slope");
            count = count + 1;
            if (start < 0) {
                start = x - 1;
            }
        } else {
            if (count >= 3) {
                StdOut.printf("A SEGMENT: %s, ", points[i]);
                for (int y = start; y < start + count; ++y) {
                    StdOut.printf("%s, ", cloneP[y]);
                }
                StdOut.printf("%n");
                // ls[segmentCount++] = new LineSegment(points[i], cloneP[start + count - 1]);
            }
            currSlope = points[i].slopeTo(cloneP[x]);
            count = 1;
            start = -1;
        }
        // StdOut.printf("%s currSlope: %f%n", cloneP[x], currSlope);
    }
}
*/