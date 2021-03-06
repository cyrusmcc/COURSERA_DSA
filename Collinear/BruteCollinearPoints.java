import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class BruteCollinearPoints {

    private int numSegments = 0;
    private List<LineSegment> lineSegmentList = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {

        if(findDuplicatePoints(points) == true) throw new IllegalArgumentException();

        for (int i = 0; i < points.length; i++) {
            if(points[i] == null) throw new IllegalArgumentException();
            for (int j = i+1; j < points.length; j++) {

                double s1 = (points[i].slopeTo(points[j]));

                for (int k = j+1; k < points.length; k++) {

                    double s2 = (points[i].slopeTo(points[k]));

                    for (int t = k+1; t < points.length; t++) {

                        double s3 = (points[i].slopeTo(points[t]));

                        if ((s1 == s2) && (s1 == s3)) {

                            Point[] temp = new Point[4];
                            temp[0] = points[i];
                            temp[1] = points[j];
                            temp[2] = points[k];
                            temp[3] = points[t];

                            Point lowest = temp[0];
                            Point highest = temp[0];

                            for (int f = 0; f < 4; f++) {
                                if (lowest.compareTo(temp[f]) > 0) lowest = temp[f];
                                if (highest.compareTo(temp[f]) < 0) highest = temp[f];
                            }

                            lineSegmentList.add(new LineSegment(lowest, highest));
                            numSegments++;
                        }
                    }
                }
            }
        }
    } // finds all line segments containing 4 points

    public int numberOfSegments() {
        return numSegments;
    } // the number of line segments

    private boolean findDuplicatePoints(Point[] points) {

        if (points == null) return true;

        for (int i = 0; i < points.length; i++) {
            Point p1 = points[i];
            for (int j = i+1; j < points.length; j++) {
                Point p2 = points[j];
                if ((p1 == null) || (p2 == null) || (p1.compareTo(p2) == 0)) return true;
            }
        }
        return false;
    }

    public LineSegment[] segments() {
        LineSegment[] lineSegmentArr = lineSegmentList.toArray(new LineSegment[0]);
        return lineSegmentArr;
    } // the line segments

    public static void main(String[] args) {
        /*
        Point[] arr = new Point[8];
        arr[0] = new Point(1, 2);
        arr[1] = new Point(2, 4);
        arr[2] = new Point(4, 8);
        arr[3] = new Point(8, 16);
        arr[4] = new Point(3, 1);
        arr[5] = new Point(6, 2);
        arr[6] = new Point(1, 8);
        arr[7] = new Point(8, 15);

        BruteCollinearPoints bCP = new BruteCollinearPoints(arr);
        System.out.println(bCP.numSegments);

        for(LineSegment segment : bCP.segments()) {
            System.out.println(segment);
        }
        */
        // read the n points from a file

        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

    }
}
