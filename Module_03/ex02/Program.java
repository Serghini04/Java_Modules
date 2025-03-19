/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <meserghi@student.1337.ma>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:59:34 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:59:35 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Program
{
	public static AtomicBoolean	isEggTurn = new AtomicBoolean(true);
	private static int arraySize;
	private static int threadsCount;
	private static int []arr;
	
	private static void generateRandomArray(int arraySize)
	{
		arr = new int[arraySize];
		Random random = new Random();	
		
		for (int i = 0; i < arraySize; i++)
			arr[i] = random.nextInt(1000);
	}

	private static void sumUsingMultipleThreads()
	{
		ThreadSum[] threadsArr = new ThreadSum[threadsCount];
		int range = arraySize / threadsCount;
		int start = 0;
		int end = 0;
		int ans = 0;

		for (int i = 0; i < threadsCount; i++) {
			end = start + range;
			if (i == threadsCount - 1)
				end = arraySize - 1;
			threadsArr[i] = new ThreadSum("Thread " + (i + 1), arr, start, end);
			threadsArr[i].start();
			start = end + 1;
		}
		
		for (int i = 0; i < threadsCount; i++) {
			try {
				threadsArr[i].join();
				ans += threadsArr[i].getSum();
			} catch (InterruptedException e) {
				System.err.println("Thread was interrupted: " + e.getMessage());
			}
		}

		System.out.println("Sum by threads: " + ans);
	}

	public static void sumWithoutThreads()
	{
		int sum = 0;
		for (int i = 0; i < arraySize; i++)
			sum += arr[i];
		System.err.println("Sum: " + sum);
	}
	
	private static void	parseInput(String []args)
	{
		if (args.length != 2)
			throw new RuntimeException("Usage: java Program --arraySize={times} --threadsCount={NumberThreads}");
		if (!args[0].startsWith("--arraySize=") || !args[1].startsWith("--threadsCount="))
			throw new RuntimeException("Usage: java Program --arraySize={times} --threadsCount={NumberThreads}");
		
		arraySize = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
		if (arraySize <= 0)
			throw new RuntimeException("Usage: arraySize must be greate than 0");
		threadsCount = Integer.parseInt(args[1].substring(args[1].indexOf('=') + 1));
		if (threadsCount <= 0)
			throw new RuntimeException("Usage: threadsCount must be greate than 0");
		generateRandomArray(arraySize);

		sumWithoutThreads();
		sumUsingMultipleThreads();
	}

	public static void main(String []args)
	{
		try {
			parseInput(args);
		}
		catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}