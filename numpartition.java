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
	
	public static int karmaker_mark(int[] nums)
	{
		return 0;
	}
	
	public static long[] rand100()
	{
		Random r = new Random(System.currentTimeMillis());
		long[] rands = new long[100];
		
		for (int i=0; i<100; i++){
			rands[i] = nextLong(r,1000000000000);
						
		return rands;
	}
	
	public static long nextLong(Random rng, long n) {
   		// error checking and 2^x checking removed for simplicity.
   		long bits, val;
   		do {
      		bits = (rng.nextLong() << 1) >>> 1;
     		 val = bits % n;
   		} while (bits-val+(n-1) < 0L);
   		return val;
	}

}