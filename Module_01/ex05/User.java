/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   User.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <serghini@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:58:15 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:58:16 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

public class User
{
	private String name;
	private int ID;
	private int balance;
	private TransactionsList transactions;

	public User(String name, int balance)
	{
		this.ID = UserIdsGenerator.getInstance().generatorId();
		this.name = name;
		this.balance = balance;
		this.transactions = new TransactionsLinkedList();
	}
	
	public User()
	{
		this.ID = UserIdsGenerator.getInstance().generatorId();
		this.transactions = new TransactionsLinkedList();
	}

	// Getters
	public String getName()
	{
		return name;
	}

	public int getId()
	{
		return ID;
	}

	public int getBalance()
	{
		return balance;
	}

	public TransactionsList getTransactionsList()
	{
		return transactions;
	}

	// Setters
	public void setTransactionList(TransactionsList transactions)
	{
		this.transactions = transactions;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setBalance(int balance)
	{
		this.balance = balance;
	}

	@Override
	public String toString() {
		return (String.format("User ID = %s, Name = %s, Balance = %s.", ID, name, balance));
	}
}
