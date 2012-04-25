import java.io.*;
import java.util.*;
import java.math.*;

public class numpartition
{
	public static void main(String[] args)
	{
		if (args.length > 1){
			System.out.println("Usage: numpartition (filename)");
			return;
		}
		
		long[] rands;
		
		// Reads 100 random numbers from input file
		if (args.length == 1){
			if (args[0].equals("t")){
				test();
				return;
			}
			try{
				FileInputStream fis = new FileInputStream(args[0]);
				DataInputStream dis = new DataInputStream(fis);
				BufferedReader br = new BufferedReader(new InputStreamReader(dis));
			
				String line;
				rands = new long[100];
				int i=0;
				
				while ((line = br.readLine()) != null){
					rands[i] = Long.parseLong(line);
					i++;
				}
			}
			catch (Exception e){
				System.err.println("Error: "+e.getMessage());
			}
		}
		// Generates 100 random numbers
		else
			rands = rand100();
	}
	
	public static void test()
	{
		long[] nums = {19, 6, 13, 9, 17};
		//karmaker_karp(nums,5);
		localSearch ls = new localSearch(nums,0);
		ls.hillClimbing(10);
	}
	
	public static long karmaker_karp(long[] nums, int capacity)
	{
		PriorityQueue<Long> q = new PriorityQueue<Long>(capacity);
		int i;
		
		for (i=0; i<capacity; i++)
			q.add(new Long(-1*nums[i]));
					
		Long first = q.poll();
		Long sec;
		
		while (first != null){
			System.out.println(-1*first.longValue());
			
			if (q.peek() == null)
				return first.longValue();
			sec = q.poll();
			q.add(new Long(first.longValue() - sec.longValue()));
			
			first = q.poll();
		}
		
		return 0;
	}
	
	public static long[] rand100()
	{
		Random r = new Random(System.nanoTime());
		long l = 1000000000;
		long[] rands = new long[100];
		
		for (int i=0; i<100; i++)
			rands[i] = nextLong(r,l);
						
		return rands;
	}
	
	public static long nextLong(Random rng, long n) 
	{
   		// error checking and 2^x checking removed for simplicity.
   		long bits, val;
   		do {
      		bits = (rng.nextLong() << 1) >>> 1;
     		 val = bits % n;
   		} while (bits-val+(n-1) < 0L);
   		return val;
	}

}