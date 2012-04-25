import java.io.*;
import java.util.*;
import java.math.*;

public class binarySolution implements solution
{
	private int[] sol;
	private long[] sequence;
	
	public binarySolution(long[] input)
	{
		sequence = input;
		sol = new int[input.length];
		randomizeSolution();
	}
	
	public binarySolution(binarySolution b)
	{
		sol = new int[b.sol.length];
		sequence = b.sequence;
		for (int i=0; i<sol.length; i++)
			sol[i] = b.sol[i];
	}
	
	public void randomizeSolution()
	{
		Random r = new Random(System.nanoTime());
		int i;
		
		for (i=0; i<sol.length; i++)
		{
			if (r.nextBoolean())
				sol[i] = 1;
			else
				sol[i] = -1;
		}
	}
	
	public solution randomMove()
	{
		Random r = new Random(System.nanoTime());
		int i = r.nextInt(sol.length);
		int j = r.nextInt(sol.length);
		while (j == i)
			j = r.nextInt(sol.length);
		
		binarySolution newSol = new binarySolution(this);
		
		newSol.flip(i);
		if (r.nextBoolean())
			newSol.flip(j);
		
		return newSol;
	}
	
	public long cost()
	{
		int cost = 0;
		for (int i=0; i<sol.length; i++)
			cost += sol[i]*sequence[i];
		return Math.abs(cost);
	}
	
	private void flip(int i)
	{
		sol[i] = -1*sol[i];
	}
	
	public void printSolution()
	{	
		for (int i=0; i<sol.length; i++)
			System.out.printf("%d\t",sol[i]);
		System.out.printf("Cost: %d\n",cost());
	}
}