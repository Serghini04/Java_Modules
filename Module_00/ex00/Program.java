public class Program
{
	public static void	main(String arg[])
	{
		int	nb = 479598;
		int sum = 0;
		while (nb != 0)
		{
			int res = nb % 10; 
			sum += res;
			nb /= 10;
		}
		System.out.println(sum);
		System.exit(0);
	}	
}
