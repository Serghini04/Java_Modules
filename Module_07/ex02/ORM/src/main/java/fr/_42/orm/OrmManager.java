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
import java.lang.reflect.Field;
import fr._42.annotations.*;

public class OrmManager {
    private Connection  connection;
    private final String DROP_TABLE = "DROP TABLE IF EXISTS %s";
    private final String CREATE_TABLE = "CREATE TABLE %s (%s)";
    private final String INSERT = "INSERT INTO %s (%s) VALUES (%s)";
    private final String UPDATE = "UPDATE %s SET %s WHERE id = %s";
    private final String SELECT = "SELECT * FROM %s WHERE id = %s";

    private String sqlTypeMapping(Field field) {
        String  sqlType;
        if (field.getType() == String.class)
            sqlType = "VARCHAR(" + field.getAnnotation(OrmColumn.class).length() + ")";
        else if (field.getType() == int.class || field.getType() == Integer.class)
            sqlType = "INTEGER";
        else if (field.getType() == long.class || field.getType() == Long.class)
            sqlType = "BIGINT";
        else if (field.getType() == boolean.class || field.getType() == Boolean.class)
            sqlType = "BOOLEAN";
        else
            sqlType = "TEXT";
        return sqlType;
    }

    private void setupTables() {
        Reflections reflections = new Reflections("fr._42.models");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(OrmEntity.class);
        for (Class<?> c : classes) {
            OrmEntity annotation = c.getAnnotation(OrmEntity.class);
            try (Statement stm = connection.createStatement()) {
                String sql = String.format(DROP_TABLE, annotation.table());
                System.out.println("Executing SQL: " + sql);
                stm.executeUpdate(sql);

                Field[] fields = c.getDeclaredFields();
                StringBuilder columns = new StringBuilder();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    String columnDef = "";
                    if (field.isAnnotationPresent(OrmColumnId.class))
                        columnDef += field.getName() + " SERIAL PRIMARY KEY";
                    else if (field.isAnnotationPresent(OrmColumn.class)) {
                        columnDef += field.getAnnotation(OrmColumn.class).name() + " " + sqlTypeMapping(field);
                    }
                    else
                        continue;
                    columns.append(columnDef);
                    if (i < fields.length - 1)
                        columns.append(", ");
                }
                sql = String.format(CREATE_TABLE, annotation.table(), columns);
                System.out.println("Executing SQL: " + sql);
                stm.executeUpdate(sql);
            } catch (SQLException e) {
                System.err.println("SQL Error: " + e.getMessage());
            }
        }
    }

    public void close() throws SQLException {
        connection.close();
    }

    public OrmManager(String url, String username, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
            setupTables();
            
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