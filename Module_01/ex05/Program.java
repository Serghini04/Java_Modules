public class Program
{
	public static void main(String []arg)
	{
		try
		{
			if (arg.length != 1 || (!arg[0].equals("--profile=dev") && !arg[0].equals("--profile=prod")))
				throw new IllegalArgumentException("You Should be : java Program (--profile=dev) or (--profile=prod)");
			Menu	menu = new Menu(arg[0]);

			menu.readInput();
		}
		catch (Exception e)
		{
			System.err.println("Error : " + e.getMessage());
			System.exit(1);
		}
	}
}
