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

import fr._42.chat.models.Chatroom;
import fr._42.chat.models.Message;
import fr._42.chat.models.User;
import fr._42.chat.repositories.MessagesRepository;
import fr._42.chat.repositories.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    }
    public static void main(String []args) {
        try {
            init();
            User user = new User(5, "Ana1", "*1*", new ArrayList<Chatroom>(), new ArrayList<Chatroom>());
            Chatroom chatroom = new Chatroom(5, "room", user, new ArrayList<Message>());
            Message msg = new Message(6,user, chatroom, "hellllllllllllllllllllo.", Timestamp.valueOf(LocalDateTime.now()));
            msgRepo.update(msg);
            ds.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}