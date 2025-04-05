/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   NumberWorker.java                                  :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/04/04 00:46:23 by meserghi          #+#    #+#             */
/*   Updated: 2025/04/04 00:46:24 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.numbers;

public class NumberWorker {

    public class IllegalNumberException extends RuntimeException {
        public IllegalNumberException() {
            super("Illegal number");
        }
    }

    public int digitsSum(int number) {
        number = Math.abs(number);
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    public boolean isPrime(int numbers) {
        if (numbers < 2)
            throw new IllegalNumberException();
        for (int i = 2; i * i <= numbers; i++) {
            if (numbers % i == 0) {
                return false;
            }
        }
        return true;
    }
}