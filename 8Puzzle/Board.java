/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Queue;

public class Board {

    private int[] tiles;
    private int[][] twoDTiles;
    private int dim;
    private int n;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {

        this.dim = tiles.length;
        this.twoDTiles = tiles;
        this.n = dim*dim;
        this.tiles = new int[n];

        int count = 0;
        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < tiles.length; j++) {
                this.tiles[count++] = tiles[i][j];
            }
        }
    }

    private Board(){}

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(dim);
        for(int i = 0; i < n; i++) {
            if(i % (dim) == 0) sb.append("\n");
            sb.append(" " + tiles[i]);
        }
        return sb.toString();
    }


    // board dimension n
    public int dimension() {
        return dim;
    }

    // number of tiles out of place
    public int hamming() {
        int outOfPlace = 0;

        for(int i = 0; i < n; i++) {
            if(tiles[i] != 0 && tiles[i] != (i + 1)) outOfPlace++;
        }

        return outOfPlace;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int sum = 0;

        // loop 0-n, if val at i is equal to i+1, value in correct location
        // else, second loop, find position with val equal to i+1 and
        // calculate manhattan distance.
        for(int i = 0; i < n; i++) {
            if(tiles[i] != 0 && tiles[i] == i+1) continue;

            for(int j = 0; j < n; j++) {

                if(tiles[j] != 0 && tiles[j] == i+1) {
                    sum += Math.abs((i % dim) - (j % dim))
                            + Math.abs((i / dim) - (j / dim));
                }
            }
        }
        return sum;
    }

    // is this board the goal board?
    public boolean isGoal() {

        for(int i = 0; i < n; i++) {
            if(tiles[i] != 0 && !(tiles[i] == i+1)) return false;
        }

        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {

        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;

        if(that.dim != this.dim) return false;
        for(int i = 0; i < n; i++) {
            if(that.tiles[i] != this.tiles[i]) return false;
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Queue<Board> neighbors = new Queue<>();
        int emptyPos = -1;

        for (int i = 0; i < n; i ++) {
            if (tiles[i] == 0) {
                emptyPos = i;
                break;
            }
        }

        if (emptyPos-dim >= 0 && emptyPos-dim <= n) {
            Board temp = new Board(twoDTiles);
            //swapTiles(temp, emptyPos, (emptyPos - dim));
            int t  = temp.tiles[emptyPos];
            temp.tiles[emptyPos] = temp.tiles[emptyPos-dim];
            temp.tiles[emptyPos-dim] = t;
            neighbors.enqueue(temp);
        }
        if (emptyPos+dim >= 0 && emptyPos+dim <= n) {
            Board temp = new Board(twoDTiles);
            swapTiles(temp, emptyPos, (emptyPos + dim));
            neighbors.enqueue(temp);
        }
        if (emptyPos-1 >= 0 && emptyPos-1 <= n) {
            Board temp = new Board(twoDTiles);
            swapTiles(temp, emptyPos, (emptyPos - 1));
            neighbors.enqueue(temp);
        }
        if (emptyPos+1 >= 0 && emptyPos+1 <= n) {
            Board temp = new Board(twoDTiles);
            swapTiles(temp, emptyPos, (emptyPos + 1));
            neighbors.enqueue(temp);
        }

        return neighbors;
    }

    private void swapTiles(Board temp, int t1, int t2) {

        int t  = temp.tiles[t1];
        temp.tiles[t1] = temp.tiles[t2];
        temp.tiles[t2] = t;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {

        Board twin = new Board(twoDTiles);

        int p1, p2;
        if(twin.tiles[n-1] != 0) p1 = n-1;
        else p1 = n-2;

        if(twin.tiles[0] != 0) p2 = 0;
        else p2 = 1;

        int t = twin.tiles[p1];
        twin.tiles[p1] = twin.tiles[p2];
        twin.tiles[p2] = t;

        return twin;
    }

    // unit testing (not graded)
    public static void main(String[] args) {

        int[][] tiles = new int[3][3];
        //int[][] tiles2 = new int[3][3];
        int c = 1;

        /*
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                tiles[i][j] = c;
                tiles2[i][j] = c;
                c++;
            }
        }
        */

        tiles[0][0] = 1;
        tiles[0][1] = 0;
        tiles[0][2] = 3;
        tiles[1][0] = 4;
        tiles[1][1] = 2;
        tiles[1][2] = 5;
        tiles[2][0] = 7;
        tiles[2][1] = 8;
        tiles[2][2] = 6;

        Board board = new Board(tiles);
        //Board board2 = new Board(tiles2);
        System.out.println(board);
        System.out.println("Hamming: " + board.hamming());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is Goal: " + board.isGoal());
        //System.out.println("Are equal: " + board.equals(board2));
        for(Board b : board.neighbors()) System.out.println("neighbor: " + b);
        System.out.println("\n twin: " + board.twin());
    }
}
