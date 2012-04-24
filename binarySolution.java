import java.io.*;
import java.util.*;
import java.math.*;

public class binarySolution implements solution
{
	private int[] sol;
	private long[] sequence;
	
	public binarySolution(long[] input, int n)
	{
		sequence = input;
		sol = new int[n];
		randomSolution();
	}
	
	public void randomSolution()
	{
		Random r = new Random(System.currentTimeMillis());
		int i;
		
		for (i=0; i<sol.length; i++)
		{
			if (r.nextBoolean())
				sol[i] = 1;
			else
				sol[i] = -1;
		}
	}
	
	public void randomMove()
	{
		Random r = new Random(System.currentTimeMillis());
		int i = r.nextInt(sol.length);
		int j = r.nextInt(sol.length);
		
		sol[i] = -1*sol[i];
		if (r.nextBoolean())
			sol[j] = -1*sol[j];
	}
	
	public int cost()
	{
		return 0;
	}
	
	public void printSolution()
	{	
		for (int i=0; i<sol.length; i++)
			System.out.println(sol[i]);
	}
}