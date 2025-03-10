/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <serghini@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:58:48 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:58:49 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

public class Program
{
	public static void main(String []args)
	{
		FileSignatures apply = new FileSignatures();
		try
		{
			apply.readSignaturesFile();
			apply.readUserInput();
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
}