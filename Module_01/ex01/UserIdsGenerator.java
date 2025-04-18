/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   UserIdsGenerator.java                              :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <meserghi@student.1337.ma>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:55:37 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:55:39 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

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
