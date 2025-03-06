import java.util.Scanner;

public class Program
{
	static Scanner scanner = new Scanner(System.in);
	private static void putError()
	{
		System.err.println("IllegalArgument");
		scanner.close();
		System.exit(-1);
	}
	private static void progressVisualizationOfWeeks(int []minWeeksGrade, int size)
	{
		for (int i = 0 ; i < size; i++)
		{
			System.out.print("Week " + (i + 1) + " ");
			for (int j = 0; j < minWeeksGrade[i]; j++)
				System.out.print("=");
			System.out.println(">");
		}
	}
	private static int getMinimalGrade()
	{
		int min = 10;
		for (int i = 0; i < 5; i++)
		{
			if (!scanner.hasNextInt())
				putError();
			int nb = scanner.nextInt();
			if (nb < 1 || nb > 9)
				putError();
			if (nb < min)
				min = nb;
		}
		return min;
	}
	public static void main(String []arg)
	{
		int index = 0;
		int[] minWeeksGrade = new int[18];
		String len;
		while (!(len = scanner.nextLine()).equals("42"))
		{
			String week = "Week " + (index + 1);
			if (week.equals(len))
				minWeeksGrade[index] = getMinimalGrade();
			else
				putError();
			index++;
		}
		progressVisualizationOfWeeks(minWeeksGrade, index);
		scanner.close();
		System.exit(0);
	}
}
