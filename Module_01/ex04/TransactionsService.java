/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   TransactionsService.java                           :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <meserghi@student.1337.ma>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:57:16 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:57:16 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.util.UUID;

public class TransactionsService
{
	private	UsersList users;
	private TransactionsList unpairedTransactions;
	
	public TransactionsService()
	{
		users = new UsersArrayList();
		unpairedTransactions = new TransactionsLinkedList();
	}
	
	public static class IllegalTransactionException extends RuntimeException
	{
		public IllegalTransactionException()
		{
			super("Illegal Transaction");
		}
	}

	public UsersList getUsers()
	{
		return users;
	}

	public void addUser(String name, int balance)
	{
		users.addUser(new User(name, balance));
	}

	public int getUserBalance(int id)
	{
		return users.findUserById(id).balance;
	}

	public	void transferAmount(int senderId, int recipienterId, int amount)
	{
		User sender = users.findUserById(senderId);
		User recipienter = users.findUserById(recipienterId);
	
		if (amount < 0 || sender.getBalance() - amount < 0)
		{
			System.err.println(amount + " | " + sender.getBalance());
			throw new IllegalTransactionException();
		}
		UUID transactionId = UUID.randomUUID();
		sender.getTransactionsList().addTransaction(new Transaction(sender, recipienter, -amount, transactionId));
		sender.setBalance(sender.getBalance()  - amount);
		recipienter.getTransactionsList().addTransaction(new Transaction(recipienter, sender, amount, transactionId));
		recipienter.setBalance(recipienter.getBalance()  + amount);
	}

	public Transaction	[]findUserTransactions(int userID)
	{
		User	user = users.findUserById(userID);
 		return user.getTransactionsList().toArray();
	}

	public void	removedUserTransaction(int userId, UUID transactionId)
	{
		User user = users.findUserById(userId);
		Transaction toDelete = user.getTransactionsList().getTransactionById(transactionId);
		
		try
		{
			this.unpairedTransactions.removeTransaction(transactionId);
		}
		catch (Exception e)
		{
			User secondUser = (userId == toDelete.getRecipient().getId())
					? users.findUserById(toDelete.getSender().getId())
					: users.findUserById(toDelete.getRecipient().getId());
			Transaction toSave = ((TransactionsLinkedList) secondUser.getTransactionsList())
					.getTransactionById(transactionId);
			this.unpairedTransactions.addTransaction(toSave);
		}
		user.getTransactionsList().removeTransaction(transactionId);
	
	}

	public Transaction[] getUnpairedTransactions() {
		return unpairedTransactions.toArray();
	}

}
