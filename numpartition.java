import java.io.*;
import java.util.*;
import java.math.*;
import java.nio.ByteBuffer;

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
				System.out.printf("KK: %d\n",karmarkar_karp(rands,rands.length));
			}
			catch (Exception e){
				System.err.println("Error: "+e.getMessage());
			}
		}
		
		if (args.length == 0)
		{
			System.out.printf("KK\tRR(bin)\tRR(pp)\tHC(bin)\tHC(pp)\tSA(bin)\tSA(pp)\t");
			System.out.printf("KK\tRR(bin)\tRR(pp)\tHC(bin)\tHC(pp)\tSA(bin)\tSA(pp)\n");
			for (int i=0; i<50; i++)
			{
				// Generates 100 random numbers
				rands = rand100();
				localSearch ls = new localSearch(rands,0);
				localSearch ls2 = new localSearch(rands,1);
				
				long start = System.nanoTime();
				long kk = karmarkar_karp(rands,100);
				long kk_t = System.nanoTime()-start;
				
				start = System.nanoTime();
				long rr_b = ls.repeatedRandom(25000);
				long rr_b_t = System.nanoTime()-start;
				
				start = System.nanoTime();
				long rr_p = ls2.repeatedRandom(25000);
				long rr_p_t = System.nanoTime()-start;
				
				start = System.nanoTime();
				long hc_b = ls.hillClimbing(25000);
				long hc_b_t = System.nanoTime()-start;
				
				start = System.nanoTime();
				long hc_p = ls2.hillClimbing(25000);
				long hc_p_t = System.nanoTime()-start;
				
				start = System.nanoTime();
				long sa_b = ls.simulatedAnnealing(25000);
				long sa_b_t = System.nanoTime()-start;
				
				start = System.nanoTime();
				long sa_p = ls2.simulatedAnnealing(25000);
				long sa_p_t = System.nanoTime()-start;
		
				System.out.printf("%d\t",kk);		
				System.out.printf("%d\t",rr_b);
				System.out.printf("%d\t",rr_p);
				System.out.printf("%d\t",hc_b);
				System.out.printf("%d\t",hc_p);
				System.out.printf("%d\t",sa_b);
				System.out.printf("%d\t",sa_p);
				
				System.out.printf("%d\t",kk_t);		
				System.out.printf("%d\t",rr_b_t);
				System.out.printf("%d\t",rr_p_t);
				System.out.printf("%d\t",hc_b_t);
				System.out.printf("%d\t",hc_p_t);
				System.out.printf("%d\t",sa_b_t);
				System.out.printf("%d\n",sa_p_t);
			}
		}
	}
	
	public static void test()
	{
		long[] nums = {10,8,7,6,5};
		//karmaker_karp(nums,5);
		localSearch ls = new localSearch(nums,1);
		//ls.repeatedRandom(10);
		//ls.hillClimbing(10);
		ls.simulatedAnnealing(10);
		
		//solution sol = new PrepartitionSolution(nums);
		//sol.printSolution();
	}
	
	public static long karmarkar_karp(long[] nums, int capacity)
	{
		PriorityQueue<Long> q = new PriorityQueue<Long>(capacity);
		int i;
		
		for (i=0; i<capacity; i++)
			q.add(new Long(-1*nums[i]));
					
		Long first = q.poll();
		Long sec;
		
		while (first != null){
			//System.out.println(-1*first.longValue());
			
			if (q.peek() == null)
				return Math.abs(first.longValue());
			sec = q.poll();
			q.add(new Long(first.longValue() - sec.longValue()));
			
			first = q.poll();
		}
		
		return 0;
	}
	
	public static long[] rand100()
	{
		Random r = new Random(System.nanoTime());
		long l = (long)1e12;
		long[] rands = new long[100];
		
		for (int i=0; i<100; i++)
			rands[i] = nextLong(r,l);
						
		return rands;
	}
	
	public static long nextLong(Random rng, long n) 
	{
   		byte[] bytes = new byte[8];
    	rng.nextBytes(bytes);
    	ByteBuffer b = ByteBuffer.wrap(bytes);
    	// Scale [Long.MIN_VALUE, Long.MAX_VALUE] -> [1,n]
    	return (1 + (long)Math.abs((b.getLong() / (double)Long.MAX_VALUE) * (n-1)));
	}

}