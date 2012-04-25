import java.io.*;
import java.util.*;
import java.math.*;


public class localSearch
{
	static final int BINARY = 0;
	static final int PREPARTITION = 1;

	private long[] sequence;
	private int mode;
	private solution sol1, sol2, sol3;
		
	public localSearch(long[] sequence, int mode)
	{
		this.mode = mode;
		this.sequence = sequence;
		
		sol1 = new binarySolution(sequence);
		sol2 = new binarySolution(sequence);
		sol3 = new binarySolution(sequence);
		
		if (mode == PREPARTITION)
		{
			sol1 = new PrepartitionSolution(sequence);
			sol2 = new PrepartitionSolution(sequence);
			sol3 = new PrepartitionSolution(sequence);
		}
	}
	
	public long repeatedRandom(int maxIter)
	{
		int i;
		long minCost;
		
		sol1.randomizeSolution();
		minCost = sol1.cost();
				
		for (i=0; i<maxIter; i++)
		{
			sol2.randomizeSolution();
			sol2.printSolution();
			System.out.printf("\tminCost: %d\n",minCost);
			
			if (sol2.cost() < minCost){
				minCost = sol2.cost();
			}
		}
		System.out.printf("Cost: %d\n",minCost);
		return minCost;
	}
	
	public long hillClimbing(int maxIter)
	{
		int i;
		sol1.randomizeSolution();
		sol1.printSolution();
		
		for (i=0; i<maxIter; i++)
		{
			sol2 = sol1.randomMove();
			sol2.printSolution();
			
			if (sol2.cost() < sol1.cost()){
				sol1 = sol2;
				System.out.printf("\tminCost: %d\n",sol1.cost());
			}
		}
		return sol1.cost();
	}
	
	public long simulatedAnnealing(int maxIter)
	{
		Random r = new Random(System.nanoTime());
		int i;
		sol1.randomizeSolution();
		sol3 = sol1;
		
		for (i=0; i<maxIter; i++)
		{
			sol2 = sol1.randomMove();
			
			sol1.printSolution();
			sol2.printSolution();
			sol3.printSolution();
			
			if (sol2.cost() < sol1.cost())
				sol1 = sol2;
			else
			{
				double p = coolingSchedule(sol1.cost(),sol2.cost(),i);
				System.out.println(p);
				
				if (r.nextDouble() < p)
					sol1 = sol2;
			}
			if (sol1.cost() < sol3.cost())
				sol3 = sol1;
		}
		return sol3.cost();
	}
	
	private double coolingSchedule(long r1, long r2, int iter)
	{
		double t_iter = Math.pow(10,10)*Math.pow(0.8,Math.floor((double)iter/300));
		double exp = -1*(r2-r1)/t_iter;
		return Math.exp(exp);
	}
	
	
}