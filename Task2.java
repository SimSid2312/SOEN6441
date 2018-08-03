import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task2 {
	
	
	
	public static void write1000file(int flag) throws IOException {
		for (int i=1;i<=1000;i++) {
			
			if(flag==1) { //Hello World Bye World
				
				flag=2;
				BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\inputfiles\\file"+i+".txt"));
				writer.write("Hello World Bye World");
				writer.flush();
				
			}
			
			else if (flag==2) { //Hello Hadoop Goodbye Hadoop
				flag=1;
				BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\inputfiles\\file"+i+".txt"));
				writer.write("Hello Hadoop Goodbye Hadoop");
				writer.flush();
			}
				
			
		}
	}
	
	public static HashMap<String,Integer> computeSingleThreaded(File[] fList){
		
		
		HashMap<String,Integer> wrd_counter=new HashMap<String,Integer>();
		BufferedReader reader;
		for (int i=0;i<fList.length;i++) {			
	      try {
			reader =new BufferedReader(new FileReader(fList[i]));
			String wrd[] = reader.readLine().split(" ");
			for (String w:wrd) {
					 
				if (wrd_counter.containsKey(w))
				{
						 wrd_counter.put(w, (wrd_counter.get(w)+1));
				 }
				 else
						 wrd_counter.put(w, 1);
					 
		    }
		}	
			catch (Exception e) {
				e.printStackTrace();
			}
		 }	
		
		
		return wrd_counter;
		
	}
	
		
	public static Map<String, Long> computeParallelVersion(File[] fList) {
		
			return   Arrays.asList(fList).parallelStream()
									.map( i -> {
										String[] out = null;
										BufferedReader reader = null;
										try {
											reader = new BufferedReader(new FileReader(i)); 
											out= reader.readLine().split(" ");
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										  return out;
										})
									.flatMap(Arrays::stream)
									.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));									
	}


	
	public static void main(String [] args) throws IOException
	{
		int flag=1;
		write1000file(flag);
		File folder =new File("D:\\inputfiles");
		String path ="D:\\inputfiles";
	    File[] fList = folder.listFiles();
	    System.out.println("Single Threaded Style done in:"+measurePerf(Task2::computeSingleThreaded,fList)+" msecs");
	    System.out.println("Parallel Style done in:"+measurePerf(Task2::computeParallelVersion,fList)+"msecs");
	    
	    
	}
	
	   

	public static <T, R> long measurePerf(Function<T, R> f, T input) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) 
        {  long start = System.nanoTime();
            R result = f.apply(input);
            long duration = (System.nanoTime() - start) / 1_000_000;
           // System.out.println(result);
           if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

	
		
	

}
