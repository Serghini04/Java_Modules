/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   OrmManager.java                                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/05/31 09:11:16 by meserghi          #+#    #+#             */
/*   Updated: 2025/05/31 13:32:11 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.orm;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OrmManager {
    private Connection  connection;

    public OrmManager(String url, String username, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, username, password);
            
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
        System.out.println("ORM : Everyting is Work.");
    }

    public void save(Object entity) {
        // String  sql = "INSERT INTO "
        try {
            Statement   stm = connection.createStatement();
            
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }
    
}