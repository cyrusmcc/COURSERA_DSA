/* *****************************************************************************
 *  Name:    Cyrus McCormick
 *  NetID:   cmccormick
 *  Precept: P00
 *
 *  Description:  Percolation
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int size;
    private boolean[][] grid;
    private final WeightedQuickUnionUF union;
    private final int vsTop, vsBottom;
    private int openSites;

    // creates n-by-n grid, with all sites initially blocked
    public  Percolation(int n) {

        if (n <= 0) throw new IllegalArgumentException();
        else {
            size = n;
            grid = new boolean[n+1][n+1];
            vsTop = 0;
            vsBottom = (n*n) + 1;
            union = new WeightedQuickUnionUF((n*n)+2);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        if (isOpen(row, col)) return;

        if (row == 1) union.union(xyTo1D(row, col), vsTop);

        if (row == size) union.union(xyTo1D(row, col), vsBottom);

        grid[row][col] = true;
        joinCells(row, col);
        openSites++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!isIndexValid(row, col)) throw new IllegalArgumentException();

        if (grid[row][col]) return true;
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isIndexValid(row, col)) throw new IllegalArgumentException();

        int p = xyTo1D(row, col);

        if (union.find(p) == union.find(0)) return true;
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // check whether index is intialized or not
    private boolean isIndexValid(int row, int col) {
        if ((row <= 0) || (col <= 0))
            return false;
        if ((row > size) || (col > size))
            return false;
        return true;
    }

    // map 2d vector to 1d plane
    private int xyTo1D(int row, int col) {
        int p = ((row-1) * size) + col;
        return p;
    }

    // if two touching cells are opened, join. if neighbor is full, fill connected.
    private void joinCells(int row, int col) {
        int p = xyTo1D(row, col), q;

        if (isIndexValid(row-1, col) && (grid[row-1][col])) {
            q = xyTo1D(row-1, col);
            union.union(p, q);
        }

        if (isIndexValid(row, col-1) && (grid[row][col-1])) {
            q = xyTo1D(row, col-1);
            union.union(p, q);
        }

        if (isIndexValid(row, col+1) && (grid[row][col+1])) {
            q = xyTo1D(row, col+1);
            union.union(p, q);
        }

        if (isIndexValid(row+1, col) && (grid[row+1][col])) {
            q = xyTo1D(row+1, col);
            union.union(p, q);
        }
    }

    /*
      private void print() {
        for(int i = 1; i <= size; i++) {
            for(int j = 1; j <= size; j++) {
                if(isOpen(i, j)) System.out.print("1");
                else System.out.print("0");
            }
            System.out.println("");
        }
    }
    */

    // does the system percolate?
    public boolean percolates() {
        if (union.find(vsTop) == union.find(vsBottom)) {
            return true;
        }
        return false;
    }

    /*
    public static void main(String[] args) {

        int size = 5;
        Percolation test = new Percolation(size);

        while(!test.percolates()) {
            int x = StdRandom.uniform(1, size+1),
                    y = StdRandom.uniform(1, size+1);
            if(!test.isOpen(x, y)) {
                test.open(x, y);
                //test.print();
                System.out.println(x + " " + y);
            }

        }
    }*/

}
