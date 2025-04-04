/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   User.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/19 00:33:54 by meserghi          #+#    #+#             */
/*   Updated: 2025/03/19 00:33:54 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.chat.models;
import java.util.List;
import java.util.Objects;
import fr._42.chat.models.Chatroom; 

public class User {
    private long userId;
    private String login;
    private String password;
    private List<Chatroom> chatrooms;
    private List<Chatroom> socializedRooms;

    public User() {
        
    }
    
    public User(long userId, String login, String password, List<Chatroom> chatrooms, List<Chatroom> socializedRooms) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.chatrooms = chatrooms;
        this.socializedRooms = socializedRooms;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(chatrooms, user.chatrooms) && Objects.equals(socializedRooms, user.socializedRooms);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", chatrooms=" + chatrooms +
                ", socializedRooms=" + socializedRooms +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, password, chatrooms, socializedRooms);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chatroom> getChatrooms() {
        return chatrooms;
    }

    public void setChatrooms(List<Chatroom> chatrooms) {
        this.chatrooms = chatrooms;
    }

    public List<Chatroom> getSocializedRooms() {
        return socializedRooms;
    }

    public void setSocializedRooms(List<Chatroom> socializedRooms) {
        this.socializedRooms = socializedRooms;
    }
}