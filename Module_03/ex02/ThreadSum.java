/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ThreadSum.java                                     :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <meserghi@student.1337.ma>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:59:39 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:59:40 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

public class ThreadSum extends Thread {

	private String threadName;
	private int []arr;
	private int start;
	private int end;
	private int sum = 0;

	public ThreadSum (String name, int []arr, int start, int end)
	{
		System.err.println("Name : " + name + ", Start : " + start+ ", end : " + end);
		this.threadName = name;
		this.arr = arr;
		this.start = start;
		this.end = end;
	}

	@Override
	public void run() {
		for (int i = start; i <= end; i++)
			sum += arr[i];
		System.out.println(threadName + ": from " + start + " to " + end + " is " + sum);
	}

	public int getSum()
	{
		return sum;
	} 
}