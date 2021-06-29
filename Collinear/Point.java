/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *  
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.awt.*;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.setPenColor(Color.red);
        StdDraw.setPenRadius(0.02);
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        if (compareTo(that) == 0) return Double.NEGATIVE_INFINITY;

        if (that.y == y) return Double.POSITIVE_INFINITY;

        if (that.x == x) return +0.0;

        return ((double ) (that.y - y) / (that.x - x));
    }


    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if ((that.y == y) && (that.x == x)) return 0;

        if (y < that.y) return -1;

        if(((y == that.y) && (x < that.x))) return -1;

        else return 1;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        /* YOUR CODE HERE */
        Comparator<Point> comparator = (o1, o2) -> {
            double slopeO1 = slopeTo(o1);
            double slopeO2 = slopeTo(o2);

            if(slopeO1 > slopeO2) return 1;
            if(slopeO1 < slopeO2) return -1;
            else if(slopeO1 == slopeO2) return 0;

            return 10;
        };

        return comparator;
    }

    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {

        Point[] arr = new Point[4];

        arr[0] = new Point(1, 2);
        arr[1] = new Point(2, 4);
        arr[2] = new Point (4, 8);
        arr[3] = new Point (8, 12);


        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 20);
        StdDraw.setYscale(0, 20);
        for (Point p : arr) {
            p.draw();
        }
        StdDraw.show();

        double p1 = arr[0].slopeTo(arr[1]);
        double p2 = arr[0].slopeTo(arr[2]);
        double p3 = arr[0].slopeTo(arr[3]);

        System.out.println(arr[0].slopeTo(arr[1]));
        System.out.println(arr[0].slopeTo(arr[2]));
        System.out.println(arr[0].slopeTo(arr[3]));

        if ((p1 == p2) && (p1 == p3) && (p2 == p3)) System.out.println("connect");

        Comparator<Point> comp = arr[0].slopeOrder();
        System.out.println(comp.compare(arr[1], arr[2]));

    /*
        System.out.println("slope == " + p1.slopeTo(p2));
        System.out.println("is p1 bigger " + p1.compareTo(p2));

        Comparator<Point> comp = p1.slopeOrder();

        System.out.println(comp.compare(p2, p3));

        System.out.println(p1.toString());
     */
    }
}
