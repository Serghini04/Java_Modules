/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   User.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/04/06 18:28:54 by meserghi          #+#    #+#             */
/*   Updated: 2025/04/06 19:16:33 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.models;

import java.util.StringJoiner;

public class User {
    private String firstName;
    private String lastName;
    private int height;
    
    public User() {
        this.firstName = "Default first name";
        this.lastName = "Default last name";
        this.height = 0;
    }
    
    public User(String firstName, String lastName, int height) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
    }
    public int grow(int value) {
        this.height += value;
        return height;
    }
    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
        .add("firstName='" + firstName + "'")
        .add("lastName='" + lastName + "'")
        .add("height=" + height)
        .toString();
    }
}
