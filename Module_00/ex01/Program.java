/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <serghini@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:54:30 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:54:32 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.util.Scanner;

public class Program
{
	static Scanner scanner = new Scanner(System.in);
	private static void putError()
	{
		System.err.println("IllegalArgument");
		scanner.close();
		System.exit(-1);
	}
	public static void main(String args[])	
	{
		int nb = scanner.nextInt();
		if (!scanner.hasNextInt())
			putError();
		nb = scanner.nextInt();
		if (nb <= 1)
			putError();
		int i = 2;
		while (i * i <= nb)
		{
			if (nb % i == 0)
			{
				System.out.println("false " + (i - 1));
				System.exit(-1);
			}
			i++;
		}
		System.out.println("true " + (i - 1));
		scanner.close();
		System.exit(0);
	}
}
