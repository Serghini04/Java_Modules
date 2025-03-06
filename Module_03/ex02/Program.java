import java.util.Random;
import java.util.concurrent.atomic.AtomicInt;

public class Program
{
	public static AtomicBoolean	isEggTurn = new AtomicBoolean(true);
	private static int arraySize;
	private static int threadsCount;
	private static int []arr;
	private static AtomicInt = 0;

	private static void generateRandomArray(int arraySize)
	{
		arr = new int[arraySize];
		Random random = new Random();	

		for (int i = 0; i < arraySize; i++)
			arr[i] = random.nextInt(1000);	
	}

	private static void createThreads()
	{
		int size = arraySize / threadsCount;
		int start = 0;
	
		for (int i = 1; i <= threadsCount; i++) {
			arraySize -= size;
			ThreadSum thread = new ThreadSum("Thread " + i, arr, start, size);
			start += size;
		}
	}

	private static void	parseInput(String []args)
	{
		if (args.length != 2)
			throw new RuntimeException("Usage: java Program --count={times} --threadsCount={NumberThreads}");
		if (!args[0].startsWith("--count=") || !args[1].startsWith("--threadsCount="))
			throw new RuntimeException("Usage: java Program --count={times} --threadsCount={NumberThreads}");

		arraySize = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
		if (arraySize <= 0)
			throw new RuntimeException("Usage: arraySize must be greate than 0");
		threadsCount = Integer.parseInt(args[1].substring(args[1].indexOf('=') + 1));
		if (threadsCount <= 0)
			throw new RuntimeException("Usage: threadsCount must be greate than 0");
		generateRandomArray(arraySize);
		// for (int i = 0; i < arraySize; i++)
		// 	System.out.println(">>" + arr[i] + "<<");
		
		createThreads();
	}

	public static void main(String []args)
	{
		try {
			parseInput(args);
		}
		catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}