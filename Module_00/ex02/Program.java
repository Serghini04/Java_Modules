/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <meserghi@student.1337.ma>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:54:41 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:54:43 by serghini         ###   ########.fr       */
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
	private static int sumOfDigits(int nb)
	{
		int sum = 0;
		while (nb != 0)
		{
			int res = nb % 10;
			sum += res;
			nb /= 10;
		}
		return sum;
	}

	private static boolean isPrime(int nb)
	{
		if (nb <= 1)
			return false;
		int i = 2;
		while (i * i <= nb)
		{
			if (nb % i == 0)
				return false;
			i++;
		}
		return true;
	}

	public static void	main(char[] arg[])
	{
		int count = 0;
		int nb;
		while (true)
		{
			if (!scanner.hasNextInt())
				putError();
			nb = scanner.nextInt();
			if (nb == 42)
				break ;
			if (isPrime(sumOfDigits(nb)))
				count++;
		}
		System.out.println(count);
		System.exit(0);
	}
};