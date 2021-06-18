/* *****************************************************************************
 *  Name:    Cyrus McCormick
 *  NetID:   cmccormick
 *  Precept: P00
 *
 *  Description:  Percolation
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double S = 1.96;
    private final int[] numOpenPerTrial;
    private final int size, trials;
    private double mean;
    private double stddev;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();

        size = n;
        this.trials = trials;
        numOpenPerTrial = new int[trials];

        for (int i = 0; i < trials; i++) {
            int numOpen = 0;
            Percolation perc = new Percolation(size);

            while (!perc.percolates()) {
                int x = StdRandom.uniform(1, (size+1)),
                        y = StdRandom.uniform(1, (size+1));
                if (!perc.isOpen(x, y)) {
                    numOpen++;
                    perc.open(x, y);
                }
            }
            numOpenPerTrial[i] = numOpen;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        mean = (StdStats.mean(numOpenPerTrial))/(size*size);
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        stddev = (StdStats.stddev(numOpenPerTrial))/(size*size);
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean - ((S * stddev)/(Math.sqrt(trials)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + ((S * stddev)/(Math.sqrt(trials)));
    }

    // test client (see below)
    public static void main(String[] args) {
        final int size = Integer.parseInt(args[0]);
        final int trials = Integer.parseInt(args[1]);

        PercolationStats percStats = new PercolationStats(size, trials);

        System.out.printf("mean%22s%f\n", "= ", percStats.mean());
        System.out.printf("stddev%20s%f\n", "= ", percStats.stddev());
        System.out.printf("95%% confidence interval = [%f, %f]",
                          percStats.confidenceLo(),
                          percStats.confidenceHi());

    }

}
