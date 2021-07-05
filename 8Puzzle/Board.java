/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Board {

    private int[] board;
    private int dim;
    private int n;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {

        this.dim = tiles.length;
        this.n = dim*dim;
        this.board = new int[n];

        int count = 0;
        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < tiles.length; j++) {
                board[count++] = tiles[i][j];
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(dim);
        for(int i = 0; i < n; i++) {
            if(i % (dim) == 0) sb.append("\n");
            sb.append(" " + board[i]);
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
            if(board[i] != 0 && board[i] != (i + 1)) outOfPlace++;
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
            if(board[i] != 0 && board[i] == i+1) continue;

            for(int j = 0; j < n; j++) {

                if(board[j] != 0 && board[j] == i+1) {
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
            if(board[i] != 0 && !(board[i] == i+1)) return false;
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
            if(that.board[i] != this.board[i]) return false;
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return null;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        return null;
    }

    // unit testing (not graded)
    public static void main(String[] args) {

        int[][] tiles = new int[3][3];
        int[][] tiles2 = new int[3][3];
        int c = 1;

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                tiles[i][j] = c;
                tiles2[i][j] = c;
                c++;
            }
        }

        /*
        tiles[0][0] = 8;
        tiles[0][1] = 1;
        tiles[0][2] = 3;
        tiles[1][0] = 4;
        tiles[1][1] = 0;
        tiles[1][2] = 2;
        tiles[2][0] = 7;
        tiles[2][1] = 6;
        tiles[2][2] = 5;
         */

        Board board = new Board(tiles);
        Board board2 = new Board(tiles2);
        System.out.println(board.toString());
        System.out.println("Hamming: " + board.hamming());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is Goal: " + board.isGoal());
        System.out.println("Are equal: " + board.equals(board2));
    }
}
