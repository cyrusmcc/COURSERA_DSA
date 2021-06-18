import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int size, trials;
    private int[] numOpenPerTrial;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if(n <= 0 || trials <= 0) throw new IllegalArgumentException();

        this.size = n;
        this.trials = trials;
        numOpenPerTrial = new int[trials];

        for(int i = 0; i < trials; i++) {
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
        return (StdStats.mean(numOpenPerTrial));
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return (StdStats.stddev(numOpenPerTrial));
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return (mean()-(1.96/Math.sqrt(stddev())));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (mean()+(1.96/Math.sqrt(stddev())));
    }

    // test client (see below)
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats percStats = new PercolationStats(size, trials);

        System.out.printf("mean%22s%f\n", "= ", percStats.mean()/(size*size));
        System.out.printf("stddev%20s%f\n", "= ", percStats.stddev()/(size*size));
        System.out.printf("95%% confidence interval = [%f, %f]",
                          percStats.confidenceLo()/(size*size),
                          percStats.confidenceHi()/(size*size));

    }

}
