/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <meserghi@student.1337.ma>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:55:51 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:55:53 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

public class Program
{
	public static void main(String []arg)
	{
		UsersArrayList	users =  new UsersArrayList(6);
		try
		{
			// Create Users :
			for (int i = 0; i < 10; i++)
				users.addUser(new User("User " + (i + 1), i * 10));
			
			// Size of Users :
			System.out.println("Size Of Users : " + users.usersNumbers());

			// Find Using Index :
			System.out.println(users.findUserByIndex(0));
			System.out.println(users.findUserByIndex(8));

			// Find Using ID :
			System.out.println(users.findUserById(0));
			System.out.println(users.findUserById(8));
		}
		catch (Exception e)
		{
			System.err.println(e);
			System.exit(-1);
		}
		System.exit(0);
	}
}
