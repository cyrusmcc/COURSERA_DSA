import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private int numSegments = 0;
    private List<LineSegment> lineSegmentList = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        Point[] sortedPoints = new Point[points.length];
        Arrays.sort(points);

        /*
        System.out.println("original sorted");
        for(int i = 0; i < points.length; i++) {
            sortedPoints[i] = points[i];
            System.out.println(sortedPoints[i]);
        }
        System.out.println("");
        */

        for (int i = 0; i < points.length; i++) {
            int numCon = 3;
            Arrays.sort(sortedPoints, points[i].slopeOrder());
            Point curr = sortedPoints[0];
            Point max;

            for (int j = 3; j < points.length; j++) {
                double prevSlope = sortedPoints[0].slopeTo(sortedPoints[j-2]);
                double currSlope = sortedPoints[0].slopeTo(sortedPoints[j-1]);
                double nextSlope = sortedPoints[0].slopeTo(sortedPoints[j]);
                if ((prevSlope == currSlope) && (curr.compareTo(sortedPoints[j-2]) == -1)
                                            && (curr.compareTo(sortedPoints[j-1]) == -1)) {
                    if (nextSlope == currSlope) numCon++;
                    if (((nextSlope != currSlope) || (j+1 == points.length)) && (numCon >= 4)) {
                        max = sortedPoints[j-1];
                        lineSegmentList.add(new LineSegment(curr, max));
                        numSegments++;
                        //System.out.println(numCon + " low: " + curr + "max: " + max);
                        break;
                    }
                }
            }
            //System.out.println("");
        }

    } // finds all line segments containing 4 or more points

    public int numberOfSegments() {
        return numSegments;
    } // the number of line segments

    public LineSegment[] segments() {
        LineSegment[] lineSegmentArr = lineSegmentList.toArray(new LineSegment[0]);
        return lineSegmentArr;
    } // the line segments

    public static void main(String[] args) {

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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();


    }


}