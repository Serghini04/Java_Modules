/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   SynchronizationThread.java                         :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <meserghi@student.1337.ma>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:59:19 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:59:20 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

public class SynchronizationThread implements Runnable {
	
	private final String threadName;
	private Integer time;

	public SynchronizationThread(String name, Integer time)
	{
		this.time = time;
		this.threadName = name;
	}

	public void	run()
	{
		try
		{
			while (time > 0)
			{
				System.out.println(threadName);
				Thread.sleep(50);
				time--;
			}
		}
		catch (InterruptedException e)
		{
			System.err.println("Error: " + e.getMessage());
		}
	}
}
