/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   User.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <meserghi@student.1337.ma>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:55:57 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:55:58 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

public class User
{
	String name;
	int ID;
	int balance;

	public User(String name, int balance)
	{
		this.ID = UserIdsGenerator.getInstance().generatorId();
		this.name = name;
		this.balance = balance;
	}

	public User()
	{
		this.ID = UserIdsGenerator.getInstance().generatorId();
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

	// Setters
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
