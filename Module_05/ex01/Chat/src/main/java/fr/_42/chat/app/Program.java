/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/19 03:00:38 by meserghi          #+#    #+#             */
/*   Updated: 2025/03/19 03:00:38 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import fr._42.chat.repositories.MessagesRepository;
import fr._42.chat.repositories.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.util.Scanner;

public class Program {
    private static long id;
    private static MessagesRepository msgRepo;
    private static HikariDataSource ds;

    private static void init() {
        ds = new HikariDataSource();

        ds.setJdbcUrl("jdbc:postgresql://192.168.1.104:5432/postgres");
        ds.setUsername("postgres");
        ds.setPassword("****");

        msgRepo = new MessagesRepositoryJdbcImpl(ds);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a message ID :");
        if (!scanner.hasNext() && !scanner.hasNextInt())
            throw new RuntimeException("Invalid Input.");
        id = scanner.nextInt();
        scanner.close();
    }
    public static void main(String []args) {
        try {
            init();
            msgRepo.findById(id).ifPresent(System.out::println);
            ds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}