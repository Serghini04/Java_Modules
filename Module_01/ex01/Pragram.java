/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Pragram.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <serghini@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:55:22 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:55:25 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

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
