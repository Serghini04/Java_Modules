/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <serghini@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:59:47 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:59:48 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Program {
    private static int threadsCount;
    private static BlockingQueue<String> urlQueue = new LinkedBlockingQueue<>();

    private static void parseInput(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--threadsCount=")) {
            throw new RuntimeException("Usage: java Program --threadsCount={NumberThreads}");
        }

        threadsCount = Integer.parseInt(args[0].split("=")[1]);
        if (threadsCount <= 0)
			throw new RuntimeException("Threads count must be greater than 0");

        try (Scanner scanner = new Scanner(new File("files_urls.txt"))) {
            while (scanner.hasNextLine()) {
                String url = scanner.nextLine().trim();
                if (!url.isEmpty()) urlQueue.add(url);
            }
        } catch (IOException e) {
            System.err.println("Cannot find file: files_urls.txt");
            System.exit(1);
        }
    }

    private static void startDownloadThreads() {
        DownloaderThread[] threads = new DownloaderThread[threadsCount];

        for (int i = 0; i < threadsCount; i++) {
            threads[i] = new DownloaderThread(urlQueue, i + 1);
            threads[i].start();
        }

        for (DownloaderThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
		try {
			parseInput(args);
			startDownloadThreads();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
    }
}
