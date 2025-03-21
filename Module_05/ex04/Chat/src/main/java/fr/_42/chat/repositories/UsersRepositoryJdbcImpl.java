/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   UsersRepositoryJdbcImpl.java                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/21 14:14:24 by meserghi          #+#    #+#             */
/*   Updated: 2025/03/21 14:14:24 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.chat.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import fr._42.chat.models.Chatroom;
import fr._42.chat.models.User;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private DataSource ds;

    public UsersRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<User> findAll(int page, int size) {
        String query = "WITH UserWithOwnedChatrooms AS (\r\n" + //
                        "    SELECT\r\n" + //
                        "        u.\"userId\",\r\n" + //
                        "        u.\"login\",\r\n" + //
                        "        u.\"password\",\r\n" + //
                        "        COALESCE(json_agg(\r\n" + //
                        "            json_build_object(\r\n" + //
                        "                'chatroomId', c.\"chatroomId\",\r\n" + //
                        "                'name', c.\"name\",\r\n" + //
                        "                'owner', json_build_object(\r\n" + //
                        "                    'userId', u.\"userId\",\r\n" + //
                        "                    'login', u.\"login\"\r\n" + //
                        "                )\r\n" + //
                        "            )\r\n" + //
                        "        ) FILTER (WHERE c.\"chatroomId\" IS NOT NULL), '[]') AS chatrooms\r\n" + //
                        "    FROM \"User\" u\r\n" + //
                        "    LEFT JOIN \"Chatroom\" c ON u.\"userId\" = c.\"ownerId\"\r\n" + //
                        "    GROUP BY u.\"userId\", u.\"login\", u.\"password\"\r\n" + //
                        "),\r\n" + //
                        "UserWithSocializedChatrooms AS (\r\n" + //
                        "    SELECT\r\n" + //
                        "        uc.\"userId\",\r\n" + //
                        "        COALESCE(json_agg(\r\n" + //
                        "            json_build_object(\r\n" + //
                        "                'chatroomId', c.\"chatroomId\",\r\n" + //
                        "                'name', c.\"name\"\r\n" + //
                        "            )\r\n" + //
                        "        ) FILTER (WHERE c.\"chatroomId\" IS NOT NULL), '[]') AS socializedRooms\r\n" + //
                        "    FROM \"UserChatroom\" uc\r\n" + //
                        "    JOIN \"Chatroom\" c ON uc.\"chatroomId\" = c.\"chatroomId\"\r\n" + //
                        "    GROUP BY uc.\"userId\"\r\n" + //
                        ")\r\n" + //
                        "SELECT\r\n" + //
                        "    uo.\"userId\",\r\n" + //
                        "    uo.\"login\",\r\n" + //
                        "    uo.\"password\",\r\n" + //
                        "    uo.chatrooms,\r\n" + //
                        "    COALESCE(us.socializedRooms, '[]') AS socializedRooms\r\n" + //
                        "FROM UserWithOwnedChatrooms uo\r\n" + //
                        "LEFT JOIN UserWithSocializedChatrooms us ON uo.\"userId\" = us.\"userId\"\r\n" + //
                        "ORDER BY uo.\"userId\"\r\n" + //
                        "LIMIT ? OFFSET ?;\r\n" + //
                        "";

        List<User> users = new ArrayList<>();
        try (Connection connection = ds.getConnection(); PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, size);
            stm.setInt(2, page * size);
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                    resultSet.getLong("userId"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    Chatroom.fromJsonArray(resultSet.getString("chatrooms")),
                    Chatroom.fromJsonArray(resultSet.getString("socializedRooms"))
                );
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return users;
    }
}
