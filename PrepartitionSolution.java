import java.io.*;
import java.util.*;
import java.math.*;
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
		//sol[0] = 0; sol[1] = 1; sol[2] = 1; sol[3] = 3; sol[4] = 4;
	}

	public PrepartitionSolution(PrepartitionSolution p)
	{
		sequence = p.sequence;
		generator = new Random(System.nanoTime());
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
        	sol[i] = generator.nextInt(sol.length);
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
    	} while (sol[i] == j);

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
		int i;
		long[] ppseq = new long[sol.length];
		for (i=0; i<sol.length; i++)
			ppseq[i] = 0;
		for (i=0; i<sol.length; i++)
			ppseq[sol[i]] += sequence[i];
			
		/*System.out.printf("\nProcessed sequence: ");
		for (i=0; i<sol.length; i++)
			System.out.printf("%d\t",ppseq[i]);
		System.out.println("");*/
			
		return Math.abs(numpartition.karmarkar_karp(ppseq, sol.length));
	}

	public void printSolution()
	{
		for (int i = 0; i < sol.length; ++i)
			System.out.printf("P = %d, ", sol[i]);
		System.out.printf("Cost: %d\n", cost());
	}
}
