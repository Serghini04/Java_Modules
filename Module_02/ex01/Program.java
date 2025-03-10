/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <serghini@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:58:56 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:58:57 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Program
{
	private static Set<String> dictionary = new TreeSet<>();
	private static Vector<String> dataFile1 = new Vector<>();
	private static Vector<String> dataFile2 = new Vector<>();

	private static void	readContentFile(String fileName, Vector<String> dataFile)
	{
		
		try (FileInputStream fd = new FileInputStream(fileName))
		{
			Scanner scanner = new Scanner(fd);

			while (scanner.hasNextLine())
			{
				String []line = scanner.nextLine().trim().split("\\s+");
				for (String word : line) {
					dictionary.add(word);
					dataFile.add(word);
				}
			}
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	private static Integer	countDuplication(String value, Vector<String> data)
	{
		Integer	ans = 0;
		for (String word : data) {
			if (word.equals(value))
				ans++;
		}
		return ans;
	}

	private static Double calculSimilarity()
	{
		Vector<Integer>	a = new Vector<>();
		Vector<Integer>	b = new Vector<>();

		// Mathematical Rules for Similarity Calculation :
		// Similarity = cos(θ) = A * B​ / ||A|| * ||B||

		for (String line : dictionary) {
			a.add(countDuplication(line, dataFile1));
			b.add(countDuplication(line, dataFile2));
		}
	
		// Numerator =  A * B :
		Integer numerator = 0;
		// Euclidean Norm ||A|| 
		Integer euclideanNormForA = 0;
		// Euclidean Norm ||B|| 
		Integer euclideanNormForB = 0;
		for (int i = 0; i < a.size(); i++)
		{
			numerator += (a.get(i) * b.get(i));
			euclideanNormForA += (a.get(i) * a.get(i));
			euclideanNormForB += (b.get(i) * b.get(i));
		}
		// Denomina ||A|| * ||B|| :
		Double denomina;
		denomina = Math.sqrt(euclideanNormForA) * Math.sqrt(euclideanNormForB);
	
		return numerator / denomina;
	}
	public static void main(String []args)
	{
		try
		{
			if (args.length != 2)
				throw new RuntimeException("You should pass two file names as arguments like: java Program file1 file2");
			readContentFile(args[0], dataFile1);
			readContentFile(args[1], dataFile2);
			System.out.printf("%.2f%n", calculSimilarity() );
		}
		catch (Exception e)
		{
			System.out.println("Error : " + e.getMessage());
			System.exit(1);
		}
	}
}