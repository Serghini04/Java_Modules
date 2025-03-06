import java.util.UUID;

public enum trash {
		@Override
	public void removeTrasaction(UUID id) throws TransactionNotFoundException
	{
		Node tmp = header;
		while (tmp != null)
		{
			if (tmp.data.getID().equals(id))
			{
				size--;
				System.out.println("Transaction ID = [" + tmp.data.getID() + "] removed succesfuly.");
				if (tmp.next != null)
					tmp.next = tmp.next.next;
				else
					tmp = null;
				return ;
			}
			tmp = tmp.next;
		}
		throw new TransactionNotFoundException();
		
	}
}
