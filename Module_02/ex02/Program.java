/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/02/17 15:43:29 by meserghi          #+#    #+#             */
/*   Updated: 2025/02/17 15:45:32 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

public class Program {
	public static void main(String []args)
	{
		try
		{
			if (args.length != 1)
				throw new RuntimeException("Usage: java Program --current-folder=<Path>");
			MacroShell pro = new MacroShell(args[0]);
			pro.startReadInput();
		}
		catch(Exception e)
		{
			System.err.println("Error : " + e.getMessage());
		}
	}	
}
