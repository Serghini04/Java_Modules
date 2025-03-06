public class ThreadSum extends Thread {

    private String threadName;
    private int []arr;
	private int start;
	private int end;

    public ThreadSum (String name, int []arr, int start, int end)
	{
		this.threadName = name;
		this.arr = arr;
		this.start = start;
		this.end = end;
	}

	@Override
	public void run() {
		int sum = 0;
		for (int i = start; i <= end; i++)
			sum += arr[start];
		System.out.println(threadName + ": from " + start + "to " + end + " is " + sum);
	}
}