/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {

        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rQ = new RandomizedQueue<String>();


        while (!StdIn.isEmpty()) {
            String in = StdIn.readString();
            rQ.enqueue(in);
        }

        for (int i = 0; i < k; i++) {
            System.out.println(rQ.dequeue());
        }

    }
}
