import java.util.Random;

public class PrepartitionSolution implements solution
{
	private int[] sol;
	private long[] sequence;
	private Random generator;

	public PrepartitionSolution(long[] input)
	{
    generator = new Random(System.nanoTime());
		sequence = input;
		sol = new int[input.length];
		randomizeSolution();
	}

	public PrepartitionSolution(PrepartitionSolution p)
	{
		sequence = p.sequence;
		sol = new int[p.sol.length];
		for (int i = 0; i < sol.length; ++i) {
			sol[i] = p.sol[i];
    }
	}

  /**
   * Generate a sequence of N random values from [1,N] and use that as the
   * initial prepartitioning solution.
   */
	public void randomizeSolution()
	{
		for (int i = 0; i < sol.length; ++i) {
			do { // Make sure it's not zero.
        sol[i] = generator.nextInt(sol.length);
      } while (sol[i] == 0);
		}
	}

  /**
   * Create a random move by choosing two random values i,j from [1,N] and
   * setting the i'th prepartition value to j.
   */
	public solution randomMove()
	{
		int i, j;
    do {
      i = generator.nextInt(sol.length);
		  j = generator.nextInt(sol.length);
    } while (i == 0 || j == 0 || sol[i] == j);

		PrepartitionSolution neighbor = new PrepartitionSolution(this);
    neighbor.sol[i] = j;
		return neighbor;
	}

  /**
   * The cost of this solution is based on how well the KK algorithm would do
   * with this prepartitioning scheme.
   */
	public long cost()
	{
		//return Math.abs(numpartition.karmaker_karp(sol, sol.length));
		return 0;
	}

	public void printSolution()
	{
		for (int i = 0; i < sol.length; ++i)
			System.out.printf("P = %d, ", sol[i]);
		System.out.printf("Cost: %d\n", cost());
	}
}
