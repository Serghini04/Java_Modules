public class Program
{
	public static void main(String []args)
	{
		FileSignatures apply = new FileSignatures();
		try
		{
			apply.readSignaturesFile();
			apply.readUserInput();
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
}