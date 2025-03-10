/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <serghini@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:59:28 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:59:29 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.util.concurrent.atomic.AtomicBoolean;

public class Program
{
	public static AtomicBoolean	isEggTurn = new AtomicBoolean(true);

	private static Integer parseInput(String []args)
	{
		Integer	time;

		if (args.length != 1)
			throw new RuntimeException("Usage: java Program --count={times}");
		if (!args[0].startsWith("--count="))
			throw new RuntimeException("Usage: java Program --count={times}");

		time = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
		if (time <= 0)
			throw new RuntimeException("Usage: time must be greate than 0");
		return time;
	}

	public static void main(String []args)
	{
		try
		{
			Integer time = parseInput(args);
			SynchronizationThread egg = new SynchronizationThread("Egg", time, isEggTurn);
			SynchronizationThread hen = new SynchronizationThread("Hen", time, isEggTurn);
			egg.start();
			hen.start();

			egg.join();  // Wait for egg to finish
            hen.join();  // Wait for hen to finish
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
		}
	}
}