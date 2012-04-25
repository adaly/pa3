import java.io.*;
import java.util.*;
import java.math.*;


public class localSearch
{
	static final int BINARY = 0;
	static final int PREPARTITION = 1;

	private long[] sequence;
	private int mode;
	private solution sol1, sol2;
		
	public localSearch(long[] sequence, int mode)
	{
		this.mode = mode;
		this.sequence = sequence;
		
		sol1 = new binarySolution(sequence);
		sol2 = new binarySolution(sequence);
		
		if (mode == PREPARTITION)
		{
			
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
	
	
}