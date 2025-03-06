
public class Pragram
{
	public static void main(String []arg)
	{
		User user1 = new User("Test 1", 5888);
		User user2 = new User("Test 2", 55);
		
		System.out.println(UserIdsGenerator.getInstance().generatorId());
		System.out.println(user1);
		System.out.println(user2);
	}
}
