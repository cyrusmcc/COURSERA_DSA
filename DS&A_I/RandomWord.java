import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {

        double count = 0;
        String random = "";

        while (!StdIn.isEmpty()) {
            String line = StdIn.readString();
            count++;

            if (StdRandom.bernoulli(1/ count))
                random = line;

        }

        StdOut.println(random);

    }
}
