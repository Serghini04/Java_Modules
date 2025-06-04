/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   OrmManager.java                                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/05/31 09:11:16 by meserghi          #+#    #+#             */
/*   Updated: 2025/06/04 18:31:37 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.orm;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import fr._42.annotations.OrmColumn;
import fr._42.annotations.OrmColumnId;
import fr._42.annotations.OrmEntity;

public class OrmManager {
    private Connection connection;
    private final String DROP_TABLE = "DROP TABLE IF EXISTS %s";
    private final String CREATE_TABLE = "CREATE TABLE %s (%s)";
    private final String INSERT = "INSERT INTO %s (%s) VALUES (%s)";
    private final String UPDATE = "UPDATE %s SET %s WHERE %s = ?";
    private final String SELECT = "SELECT * FROM %s WHERE %s = ?";

    private String sqlTypeMapping(Field field) {
        if (field.getType() == String.class)
            return "VARCHAR(" + field.getAnnotation(OrmColumn.class).length() + ")";
        else if (field.getType() == int.class || field.getType() == Integer.class)
            return "INTEGER";
        else if (field.getType() == long.class || field.getType() == Long.class)
            return "BIGINT";
        else if (field.getType() == boolean.class || field.getType() == Boolean.class)
            return "BOOLEAN";
        else
            return "TEXT";
    }

    private String buildDebugSql(String sql, List<Object> values) {
        StringBuilder result = new StringBuilder();
        int valueIndex = 0;
        for (int i = 0; i < sql.length(); i++) {
            char c = sql.charAt(i);
            if (c == '?' && valueIndex < values.size()) {
                Object val = values.get(valueIndex++);
                if (val instanceof String || val instanceof java.sql.Date)
                    result.append("'").append(val).append("'");
                else
                    result.append(val);
            } else {
                result.append(c);
            }
        }
        return result.toString();
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
                    } else
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
        System.out.println("ORM : Everyting is Work perfictly.");
    }

    public void save(Object entity) {
        if (!entity.getClass().isAnnotationPresent(OrmEntity.class)) {
            System.err.println("Error: The class " + entity.getClass().getName() + " is not annotated with @OrmEntity.");
            return;
        }

        try {
            StringBuilder columns = new StringBuilder();
            StringBuilder placeholders = new StringBuilder();
            Field[] fields = entity.getClass().getDeclaredFields();
            List<Object> values = new ArrayList<>();

            for (Field field : fields) {
                if (field.isAnnotationPresent(OrmColumn.class)) {
                    if (field.isAnnotationPresent(OrmColumnId.class)) continue; // skip auto-increment ID
                    columns.append(field.getAnnotation(OrmColumn.class).name()).append(", ");
                    placeholders.append("?, ");
                    field.setAccessible(true);
                    values.add(field.get(entity));
                }
            }

            if (values.isEmpty()) return;

            columns.setLength(columns.length() - 2);       // remove last comma
            placeholders.setLength(placeholders.length() - 2);

            String sql = String.format(INSERT,
                entity.getClass().getAnnotation(OrmEntity.class).table(),
                columns, placeholders);

            System.out.println("Executing SQL: " + buildDebugSql(sql, values));
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                for (int i = 0; i < values.size(); i++) {
                    ps.setObject(i + 1, values.get(i));
                }
                ps.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }

    public void update(Object entity) {
        if (!entity.getClass().isAnnotationPresent(OrmEntity.class))
            throw new RuntimeException("Error: The class " + entity.getClass().getName() + " is not annotated with @OrmEntity.");

        try {
            StringBuilder updateValues = new StringBuilder();
            List<Object> values = new ArrayList<>();
            Field idField = null;
            Object idValue = null;

            for (Field field : entity.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(OrmColumnId.class)) {
                    idField = field;
                    idValue = field.get(entity);
                } else if (field.isAnnotationPresent(OrmColumn.class)) {
                    updateValues.append(field.getAnnotation(OrmColumn.class).name()).append(" = ?, ");
                    values.add(field.get(entity));
                }
            }

            if (idField == null || idValue == null)
                throw new RuntimeException("No primary key (@OrmColumnId) found. Cannot update.");

            updateValues.setLength(updateValues.length() - 2); // remove trailing comma

            String sql = String.format(UPDATE,
                entity.getClass().getAnnotation(OrmEntity.class).table(),
                updateValues,
                idField.getName());

            List<Object> allValues = new ArrayList<>(values);
            allValues.add(idValue);
            System.out.println("Executing SQL: " + buildDebugSql(sql, allValues));
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                int i = 1;
                for (Object val : values)
                    ps.setObject(i++, val);
                ps.setObject(i, idValue);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println("Update Error: " + e.getMessage());
        }
    }

    public <T> T findById(Long id, Class<T> aClass) {
        if (!aClass.isAnnotationPresent(OrmEntity.class))
            throw new RuntimeException("The class " + aClass.getName() + " is not annotated with @OrmEntity.");

        try {
            Field idField = null;
            for (Field field : aClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(OrmColumnId.class)) {
                    idField = field;
                    break;
                }
            }

            if (idField == null)
                throw new RuntimeException("No field annotated with @OrmColumnId found.");

            String idColumn = idField.getName();
            String sql = String.format(SELECT,
            aClass.getAnnotation(OrmEntity.class).table(), idColumn);
            System.out.println("Executing SQL: " + buildDebugSql(sql, List.of(id)));
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    T instance = aClass.getDeclaredConstructor().newInstance();
                    for (Field field : aClass.getDeclaredFields()) {
                        field.setAccessible(true);
                        String columnName = field.isAnnotationPresent(OrmColumn.class)
                            ? field.getAnnotation(OrmColumn.class).name()
                            : field.getName();
                        Object value = rs.getObject(columnName);
                        if (value != null) {
                            if (field.getType() == int.class) field.setInt(instance, ((Number) value).intValue());
                            else if (field.getType() == long.class) field.setLong(instance, ((Number) value).longValue());
                            else if (field.getType() == boolean.class) field.setBoolean(instance, (Boolean) value);
                            else field.set(instance, value);
                        }
                    }
                    return instance;
                }
            }
        } catch (Exception e) {
            System.err.println("FindById Error: " + e.getMessage());
        }
        return null;
    }
}