/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ProductsRepositoryJdbcImpl.java                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/04/04 01:29:48 by meserghi          #+#    #+#             */
/*   Updated: 2025/04/04 21:26:05 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.repositories;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

import fr._42.models.Product;
import fr._42.repositories.ProductsRepository;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private final DataSource ds;
    
    public ProductsRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }
    
    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product";
        try (Connection conn = ds.getConnection();
             PreparedStatement stm = conn.prepareStatement(query);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                products.add(new Product(
                    rs.getLong("identifier"),
                    rs.getString("name"),
                    rs.getBigDecimal("price")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) {
        String query = "SELECT * FROM product WHERE identifier = ?";
        try (Connection conn = ds.getConnection();
             PreparedStatement stm = conn.prepareStatement(query)) {
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return Optional.of(new Product(
                    rs.getLong("identifier"),
                    rs.getString("name"),
                    rs.getBigDecimal("price")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void update(Product product) {
        String query = "UPDATE product SET name = ?, price = ? WHERE identifier = ?";
        try (Connection conn = ds.getConnection();
             PreparedStatement stm = conn.prepareStatement(query)) {
            stm.setString(1, product.getName());
            stm.setBigDecimal(2, product.getPrice());
            stm.setLong(3, product.getIdentifier());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void save(Product product) {
        String query = "INSERT INTO product (identifier, name, price) VALUES (?, ?, ?)";
        try (Connection conn = ds.getConnection();
             PreparedStatement stm = conn.prepareStatement(query)) {
            stm.setLong(1, product.getIdentifier());
            stm.setString(2, product.getName());
            stm.setBigDecimal(3, product.getPrice());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode());
        }
    }
    
    @Override
    public void delete(Long id) {
        String query = "DELETE FROM product WHERE identifier = ?";
        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}