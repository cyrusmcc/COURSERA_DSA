import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    int size;
    int[][] grid;
    WeightedQuickUnionUF union;

    // creates n-by-n grid, with all sites initially blocked
    public  Percolation(int n) {
        size = n;
        grid = new int[n+1][n+1];
        union = new WeightedQuickUnionUF((n*n)+1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if(!isIndexValid(row, col)) throw new IllegalArgumentException();

        if(!(grid[row][col] == 0)) return;
        else if(row == 1) {
            grid[row][col] = 2;
        }
        else {
            grid[row][col] = 1;
        }
        joinCells(row, col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if(!isIndexValid(row, col)) throw new IllegalArgumentException();

        if(grid[row][col] == 1) return true;
        else return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if(!isIndexValid(row, col)) throw new IllegalArgumentException();

        if(grid[row][col] == 2) return true;
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int openSites = 0;

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid.length; j++) {
                if(grid[i][j] == 1) openSites++;
            }
        }
        return openSites;
    }

    private boolean isIndexValid(int row, int col) {
        if((row <= 0) || (col <= 0))
            return false;
        if((row > (grid.length - 1)) || (col > (grid.length - 1)))
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

        if(isIndexValid(row-1, col) && !(grid[row-1][col] == 0)) {
            q = xyTo1D(row-1, col);
            union.union(p, q);
            if(isFull(row-1, col) && !isFull(row, col)) grid[row][col] = 2;
        }

        if(isIndexValid(row, col-1) && !(grid[row][col-1] == 0)) {
            q = xyTo1D(row, col-1);
            union.union(p, q);
            if(isFull(row, col-1) && !isFull(row, col)) grid[row][col] = 2;
        }

        if(isIndexValid(row, col+1) && !(grid[row][col+1] == 0)) {
            q = xyTo1D(row, col+1);
            union.union(p, q);
            if(isFull(row, col+1) && !isFull(row, col)) grid[row][col] = 2;
        }

        if(isIndexValid(row+1, col) && !(grid[row+1][col] == 0)) {
            q= xyTo1D(row+1, col);
            union.union(p, q);
            if(isFull(row+1, col) && !isFull(row, col)) grid[row][col] = 2;
        }
        fillConnected(row, col);
    }

    private void fillConnected(int row, int col) {
        int p = xyTo1D(row, col);

        for(int i = 1; i <= size; i++) {
            for(int j = 1; j <= size; j++) {
                int q = xyTo1D(i, j);
                if(union.connected(p, q) && grid[row][col] == 2) {
                    grid[i][j] = 2;
                }
            }
        }
    }

    public void print() {
        for(int i = 1; i <= size; i++) {
            for(int j = 1; j <= size; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println("");
        }
    }

    // does the system percolate?
    public boolean percolates() {
        for(int i = 0; i <= size; i++) {
            if(grid[size][i] == 2) {
                return true;
            }
        }
        return false;
    }

   /*public static void main(String[] args) {

        int size = 5;
        Percolation test = new Percolation(size);

        while(!test.percolates()) {
            int x = StdRandom.uniform(1, 4),
                    y = StdRandom.uniform(1, 4);
            if(!test.isOpen(x, y)) {
                test.open(x, y);
                test.print();
            }
            System.out.println(x + " " + y);
        }

        test.print();
        System.out.println(test.union.count());
        test.percolates();

    }
    */
}
