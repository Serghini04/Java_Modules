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
import fr._42.chat.models.User;
import fr._42.chat.repositories.UsersRepository;
import fr._42.chat.repositories.UsersRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.util.List;

public class Program {
    private static HikariDataSource ds;
    private static UsersRepository usersRepo;

    private static void init() {
        ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://192.168.1.104:5432/postgres");
        ds.setUsername("postgres");
        ds.setPassword("****");

        usersRepo = new UsersRepositoryJdbcImpl(ds);
    }

    public static void main(String[] args) {
        try {
            init();

            int page = 1;
            int size = 1;

            List<User> users = usersRepo.findAll(page, size);

            for (User user : users) {
                System.out.println("User: " + user.getUserId() + " | " + user.getLogin());

                System.out.println("  Owned Chatrooms:");
                for (Chatroom chatroom : user.getChatrooms()) {
                    System.out.println("    - " + chatroom.getId() + " | " + chatroom.getName());
                }

                System.out.println("  Participated Chatrooms:");
                for (Chatroom chatroom : user.getSocializedRooms()) {
                    System.out.println("    - " + chatroom.getId() + " | " + chatroom.getName());
                }

                System.out.println();
            }

            ds.close();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}