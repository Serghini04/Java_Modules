/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   NumberWorkerTest.java                              :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/04/04 00:45:36 by meserghi          #+#    #+#             */
/*   Updated: 2025/04/04 00:45:36 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.numbers;

import fr._42.numbers.NumberWorker.IllegalNumberException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest {
	// isPrimeForPrimes method to check isPrime using prime numbers:
	@ParameterizedTest
	@ValueSource(ints = {2, 3, 5, 7, 11})
	public void isPrimeForPrime(int n) {
		NumberWorker nw = new NumberWorker();
		boolean result = nw.isPrime(n);
		assertTrue(result);
	}

	// isPrimeForNotPrimes method to check isPrime using composite numbers:
	@ParameterizedTest
	@ValueSource(ints = {6, 8, 9, 10, 12})
	public void isPrimeForNotPrime(int n) {
		NumberWorker nw = new NumberWorker();
		boolean result = nw.isPrime(n);
		assertFalse(result);
	}

	// isPrimeForIncorrectNumbers method to check isPrime using incorrect numbers:
	@ParameterizedTest
	@ValueSource(ints = {-2, -55555, 0, 1, -9})
	public void isPrimeForIncorrectNumbers(int n) {
		NumberWorker nw = new NumberWorker();
		assertThrows(IllegalNumberException.class, () -> nw.isPrime(n));
	}

	// method to check digitsSum :
	@ParameterizedTest
	@CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
	public void digitsSumForCsv(int n, int expectedSum) {
		NumberWorker nw = new NumberWorker();
		assertEquals(nw.digitsSum(n), expectedSum);
	}
}
