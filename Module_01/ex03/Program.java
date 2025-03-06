public class Program
{
	public static void main(String []arg)
	{
		User	user1 = new User("Mehdi King", 100);
		User	user2 = new User("Poor Man", 0);

		for (int i = 10; i < 100; i += 30)
			user1.getTransactionsList().addTransaction(new Transaction(user2, user1, i));	
		
		Transaction[] transactions = user1.getTransactionsList().toArray();

		System.out.println("	>>=======Transactions=========<<");
		for (int i = 0; i < transactions.length; i++)
			System.out.println(transactions[i].toString());
		System.out.println("	>>============================<<");
		user1.getTransactionsList().removeTransaction(transactions[0].getId());
		user1.getTransactionsList().removeTransaction(transactions[transactions.length - 1].getId());
	}
}
