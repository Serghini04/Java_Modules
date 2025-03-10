/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <serghini@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:54:17 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:54:19 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

public class Program
{
	public static void	main(String arg[])
	{
		int	nb = 479598;
		int sum = 0;
		while (nb != 0)
		{
			int res = nb % 10; 
			sum += res;
			nb /= 10;
		}
		System.out.println(sum);
		System.exit(0);
	}	
}
