/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   OrmManager.java                                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/05/31 09:11:16 by meserghi          #+#    #+#             */
/*   Updated: 2025/05/31 17:25:41 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.sql.PreparedStatement;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import fr._42.annotations.OrmEntity;

public class OrmManager {
    private Connection  connection;
    private final String DROP_TABLE = "DROP TABLE IF EXISTS %s";
    private final String CREATE_TABLE = "CREATE TABLE %s (%s)";
    private final String INSERT = "INSERT INTO %s (%s) VALUES (%s)";
    private final String UPDATE = "UPDATE %s SET %s WHERE id = %s";
    private final String SELECT = "SELECT * FROM %s WHERE id = %s";

    private void    setupTables() {
        Reflections reflections = new Reflections("fr._42.models");

        Set<Class<?>>   classes = reflections.getTypesAnnotatedWith(OrmEntity.class);
        for (Class<?> c : classes) {
            OrmEntity annotation = c.getTypesAnnotatedWith(OrmEntity.class);
            Statement   stm = connection.createStatement();
            
            String sql = String.format(DROP_TABLE, annotation.table());
            
            System.out.println("Executing SQL: " + sql);
            stm.executeUpdate(sql);

            // ....
            
        }
    }

    public void close() throws SQLException {
        connection.close();
    }

    public OrmManager(String url, String username, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            setupTables();
            connection = DriverManager.getConnection(url, username, password);
            
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
        System.out.println("ORM : Everyting is Work.");
    }
    

    public void save(Object entity) {
        try {
            Statement   stm = connection.prepareStatement(CREATE_TABLE);
            
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }
}