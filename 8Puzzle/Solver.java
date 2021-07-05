/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;

public class Solver {

    private static class SearchNode {
        Board board;
        int moves;
        SearchNode prev;
    }

    private int moves;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();

        MinPQ minPQ = new MinPQ();
        SearchNode initialNode = new SearchNode();
        initialNode.board = initial;
        initialNode.moves = 0;
        initialNode.prev = null;
        minPQ.insert(initialNode);

        while(true) {

            SearchNode min = (SearchNode) minPQ.delMin();

            if (min.board.isGoal()) {
                moves = min.moves;
                break;
            }

            for (Board  b: min.board.neighbors()) {
                SearchNode newNode = new SearchNode();
                newNode.board = b;
                newNode.prev = min;
                newNode.moves = newNode.prev.moves + 1;
            }

        }

    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {

    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if(isSolvable()) return moves;
        else return -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return null;
    }

    // test client (see below)
    public static void main(String[] args) {

    }

}
