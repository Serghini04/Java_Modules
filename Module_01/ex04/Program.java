/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <serghini@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:56:49 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:56:50 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.util.UUID;

public class Program
{
	public static void main(String []arg)
	{
		TransactionsService	transactionsService = new TransactionsService();

		User	user1 = new User("Mehdi King", 5000);
		User	user2 = new User("Poor Man", 0);

		System.out.println(user1);
		System.out.println(user2);
		
		transactionsService.getUsers().addUser(user1);
		transactionsService.getUsers().addUser(user2);

		for (int i = 10; i < 100; i += 10)
			transactionsService.transferAmount(user1.getId(), user2.getId(), i);
	
		Transaction[] transactions = transactionsService.findUserTransactions(user1.getId());
		for (int i = 0; i < transactions.length; i++)
			System.out.println("Tansaction : " + transactions[i]);
	}
}
