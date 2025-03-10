/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   SynchronizationThread.java                         :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <serghini@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:59:24 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:59:25 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.util.concurrent.atomic.AtomicBoolean;

public class SynchronizationThread extends Thread {
	
	private final String threadName;
	private Integer time;
	private AtomicBoolean isEggTurn;

	public SynchronizationThread(String name, Integer time, AtomicBoolean isEggTurn)
	{
		this.time = time;
		this.threadName = name;
		this.isEggTurn = isEggTurn;
	}

	public void	run()
	{
		try
		{
			while (time > 0)
			{
				synchronized (isEggTurn)
				{
					if ((threadName.equals("Hen") && isEggTurn.get()) || (threadName.equals("Egg") && !isEggTurn.get()))
						isEggTurn.wait();
					System.out.println(threadName);
					isEggTurn.set(!isEggTurn.get()); // Toggle the turn
					isEggTurn.notify(); // Notify the waiting thread
					time--;
				}
			}
		}
		catch (InterruptedException e)
		{
			System.err.println("Error: " + e.getMessage());
		}
	}
}
