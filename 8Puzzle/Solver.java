/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solver {

    private static class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private int moves;
        private int priority;
        private SearchNode prev;

        public SearchNode(Board board, int moves, SearchNode prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            priority = board.manhattan() + moves;
        }

        // sort hi to lo
        private Comparator<SearchNode> sortByPriority() {
            Comparator<SearchNode> comparator = (o1, o2) -> {
                if (o1 == null || o2 == null) throw new NullPointerException();

                if (o2.priority > o1.priority) return  1;
                if (o2.priority < o1.priority) return -1;
                if(o2.priority == o1.priority) {
                    if (o2.board.hamming() > o1.board.hamming()) return 1;
                    if(o2.board.hamming() < o1.board.hamming()) return -1;
                }
                return 0;
            };
            return comparator;
        }

        public int compareTo(SearchNode o) {
            return 0;
        }
    }

    private int leastMoves;
    private boolean solvable;
    private SearchNode sol;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();

        MinPQ<SearchNode> minPQ = new MinPQ<SearchNode>();
        SearchNode initialNode = new SearchNode(initial, 0, null);
        minPQ.insert(initialNode);

        MinPQ<SearchNode> twinMinPq = new MinPQ<SearchNode>();
        SearchNode initialTwin = new SearchNode(initial.twin(), 0, null);
        twinMinPq.insert(initialTwin);

        while (true) {

            List<SearchNode> neighbors = new ArrayList<>();
            SearchNode min = minPQ.delMin();

            List<SearchNode> twinNeighbors = new ArrayList<>();
            SearchNode twinMin = twinMinPq.delMin();

            if (min.board.isGoal()) {
                solvable = true;
                sol = min;
                leastMoves = initial.manhattan();
                for (Board b : solution()) System.out.println(b);
                break;
            }

            if (twinMin.board.isGoal()) {
                solvable = false;
                sol = twinMin;
                for (Board b : solution()) System.out.println(b);
                break;
            }

            for (Board  b: min.board.neighbors()) {
                if (min.prev != null && b.equals(min.prev.board)) continue;
                SearchNode newNode = new SearchNode(b, min.moves + 1, min);
                newNode.priority = (b.manhattan() + newNode.moves);
                neighbors.add(newNode);
            }

            for (Board  b: twinMin.board.neighbors()) {
                if (twinMin.prev != null && b.equals(twinMin.prev.board)) continue;
                SearchNode newNode = new SearchNode(b, twinMin.moves + 1, twinMin);
                newNode.priority = (b.manhattan() + newNode.moves);
                twinNeighbors.add(newNode);
            }

            neighbors.sort(neighbors.get(0).sortByPriority());
            for (SearchNode s : neighbors) {
                // System.out.println(s.priority + " " + s.board);
                minPQ.insert(s);
            }

            twinNeighbors.sort(twinNeighbors.get(0).sortByPriority());
            for (SearchNode t : twinNeighbors) {
                // System.out.println(t.priority + " " + t.board);
                twinMinPq.insert(t);
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        if (solvable == true) return true;
        return false;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (isSolvable()) return leastMoves;
        else return -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        Stack<Board> stack = new Stack<>();
        SearchNode temp = sol;

        while (temp != null) {
            stack.push(temp.board);
            temp = temp.prev;
        }

        return stack;
    }

    // test client (see below)
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);
        // System.out.println("t:" + initial.twin());
        System.out.println(solver.isSolvable());
        System.out.println(solver.moves());
        // print solution to standard output
        // if (!solver.isSolvable())
          //  StdOut.println("No solution possible");
        // else {
            // StdOut.println("Minimum number of moves = " + solver.moves());
            // 8for (Board board : solver.solution())
              //  StdOut.println(board);
        //}

    }

}
