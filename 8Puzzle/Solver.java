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

    private static class SearchNode implements Comparable{
        private Board board;
        private int moves;
        private int priority;
        private SearchNode prev;

        // sort hi to lo
        private Comparator<SearchNode> sortByPriority() {
            Comparator<SearchNode> comparator = (o1, o2) -> {
                if (o1 == null || o2 == null) throw new NullPointerException();

                return Integer.compare(o2.priority, o1.priority);
            };
            return comparator;
        }

        public int compareTo(Object o) {
            return 0;
        }
    }

    private int moves;
    private boolean solvable;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();

        MinPQ<SearchNode> minPQ = new MinPQ<SearchNode>();
        SearchNode initialNode = new SearchNode();
        initialNode.board = initial;
        initialNode.moves = 0;
        initialNode.prev = null;
        minPQ.insert(initialNode);

        /*
        MinPQ<SearchNode> twinMinPq = new MinPQ<SearchNode>();
        SearchNode initialTwin = new SearchNode();
        initialTwin.board = initial.twin();
        initialTwin.moves = 0;
        initialTwin.prev = null;
        twinMinPq.insert(initialTwin);
        */

        while(true) {

            List<SearchNode> neighbors = new ArrayList<>();
            SearchNode min = minPQ.delMin();
            moves = min.moves;

           /* List<SearchNode> twinNeighbors = new ArrayList<>();
            SearchNode twinMin = twinMinPq.delMin();*/

            if (min.board.isGoal()) {
                solvable = true;
                for(Board b : solution(min)) System.out.println(b);
                break;
            }

           /* if (twinMin.board.isGoal()) {
                solvable = false;
                for(Board b : solution(twinMin)) System.out.println(b);
                break;
            } */

            for (Board  b: min.board.neighbors()) {
                if (min.prev != null && b.equals(min.prev.board)) continue;
                SearchNode newNode = new SearchNode();
                newNode.board = b;
                newNode.prev = min;
                newNode.moves = newNode.prev.moves + 1;
                newNode.priority = (b.manhattan() + newNode.moves);
                neighbors.add(newNode);
            }

           /* for (Board  b: twinMin.board.neighbors()) {
                if (twinMin.prev != null && b.equals(twinMin.prev.board)) continue;
                SearchNode newNode = new SearchNode();
                newNode.board = b;
                newNode.prev = twinMin;
                newNode.moves = newNode.prev.moves + 1;
                newNode.priority = (b.manhattan() + newNode.moves);
                twinNeighbors.add(newNode);
            } */

            neighbors.sort(neighbors.get(0).sortByPriority());
            for(SearchNode s : neighbors) {
                minPQ.insert(s);
                System.out.println(s.priority + " " + s.board);
            }

          /*  twinNeighbors.sort(twinNeighbors.get(0).sortByPriority());
            for(SearchNode t : twinNeighbors) twinMinPq.insert(t); */
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        if(solvable == true) return true;
        return false;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if(!isSolvable()) return moves;
        else return -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution(SearchNode min) {
        Stack<Board> stack = new Stack<>();

        while(min != null) {
            stack.push(min.board);
            min = min.prev;
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

        System.out.println(solver.isSolvable());
        // print solution to standard output
        //if (!solver.isSolvable())
          //  StdOut.println("No solution possible");
        //else {
            //StdOut.println("Minimum number of moves = " + solver.moves());
            //for (Board board : solver.solution())
              //  StdOut.println(board);
        //}

    }

}
