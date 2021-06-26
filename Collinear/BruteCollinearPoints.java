import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BruteCollinearPoints {

    private int numSegments = 0;
    private List<LineSegment> lineSegmentList = new ArrayList<>();


    public BruteCollinearPoints(Point[] points) {
    /*
        for(int i = 0; i < points.length-3; i++) {
            double p1SlopeToP2 = points[i].slopeTo(points[i+1]);
            double p1SlopeToP3 = points[i].slopeTo(points[i+2]);
            double p1SlopeToP4 = points[i].slopeTo(points[i+3]);

            if((p1SlopeToP2 == p1SlopeToP3) && (p1SlopeToP2 == p1SlopeToP4)) {
                lineSegmentList.add(new LineSegment(points[i],points[i+3]));
                numSegments++;
            }
        }
    */


        for (int i = 0; i < points.length-3; i++) {
            for(int j = i+1; j < points.length-2; j++) {
                for(int k = j+1; k < points.length-1; k++) {
                    for(int l = k+1; l < points.length; l++) {
                        double s1 = points[i].slopeTo(points[j]);
                        double s2 = points[i].slopeTo(points[k]);
                        double s3 = points[i].slopeTo(points[l]);
                        if((s1 == s2) && (s1 == s3) && (s2 == s3)) {
                            System.out.println(s1 + " " + s2 + " " + s3);
                            lineSegmentList.add(new LineSegment(points[i],points[l]));
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
