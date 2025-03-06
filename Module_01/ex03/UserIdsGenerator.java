public class UserIdsGenerator
{
	static UserIdsGenerator generator;
	static int	id = -1;

	public static UserIdsGenerator getInstance()
	{
		if (generator == null)
			generator = new UserIdsGenerator();
		return generator;
	}
	public static int  generatorId()
	{
		id++;
		return id;
	}
	@Override
	public String toString()
	{
		return (String.format("ID : %d", id));
	} 
}
