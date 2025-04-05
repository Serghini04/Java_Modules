/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   MessagesRepositoryJdbcImpl.java                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/19 03:06:13 by meserghi          #+#    #+#             */
/*   Updated: 2025/03/19 03:06:13 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.chat.repositories;

import fr._42.chat.models.Chatroom;
import fr._42.chat.models.User;
import fr._42.chat.repositories.MessagesRepository;
import javax.sql.DataSource;
import fr._42.chat.models.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final DataSource jdbc;

    public MessagesRepositoryJdbcImpl(DataSource jdbc) {
        this.jdbc = jdbc;
    }

    public User setUserObj(ResultSet rs) throws SQLException {
        User user = new User();

        user.setUserId(rs.getInt("userId"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        return user;
    }
    public Chatroom setChatroomObj(ResultSet rs) throws SQLException {
        Chatroom chatroom = new Chatroom();
        chatroom.setId(rs.getInt("chatroomId"));
        chatroom.setName(rs.getString("name"));
        return chatroom;
    }
    public Message setMessageObj(ResultSet rs) throws SQLException {
        Message message = new Message();
        message.setId(rs.getInt("messageId"));
        message.setText(rs.getString("text"));
        message.setDateTime(rs.getTimestamp("dateTime"));
        message.setAuthor(setUserObj(rs));
        message.setChatroom(setChatroomObj(rs));
        return message;
    }
    @Override
    public Optional<Message> findById(long id) {
        String query = "SELECT * FROM \"Message\" " +
                "JOIN \"User\" u ON \"Message\".\"authorId\" = u.\"userId\" " +
                "JOIN \"Chatroom\" r ON \"Message\".\"chatroomId\" = r.\"chatroomId\" " +
                "WHERE \"Message\".\"messageId\" = ?";
        try {
            Connection connection = jdbc.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                return Optional.of(setMessageObj(rs));
        }catch (SQLException e) {
            // Log the exception with details
            throw new RuntimeException("JDBC failed! " + e.getMessage(), e);
        }
        return Optional.empty();
    }
}