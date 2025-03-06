public class SynchronizationThread extends Thread {
	
	private final String threadName;
	private Integer time;

	public SynchronizationThread(String name, Integer time)
	{
		this.time = time;
		this.threadName = name;
	}

	public void	run()
	{
		try
		{
			while (time > 0)
			{
				System.out.println(threadName);
				Thread.sleep(50);
				time--;
			}
		}
		catch (InterruptedException e)
		{
			System.err.println("Error: " + e.getMessage());
		}
	}
}
