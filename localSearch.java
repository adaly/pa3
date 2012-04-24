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
			if (sol2.cost() < sol1.cost())
				minCost = sol2.cost();
		}
		return minCost;
	}
	
	
}