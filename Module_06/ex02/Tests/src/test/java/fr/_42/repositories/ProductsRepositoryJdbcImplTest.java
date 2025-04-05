/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ProductsRepositoryJdbcImplTest.java                :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/04/04 11:02:08 by meserghi          #+#    #+#             */
/*   Updated: 2025/04/05 00:26:12 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.repositories;

import java.math.BigDecimal;
import java.util.*;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import fr._42.models.Product;
import fr._42.repositories.ProductsRepositoryJdbcImpl;

public class ProductsRepositoryJdbcImplTest {

    private ProductsRepositoryJdbcImpl repo;
    private DataSource ds;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
        new Product(1L, "Laptop", new BigDecimal("1200.50")),
        new Product(2L, "Smartphone", new BigDecimal("899.99")),
        new Product(3L, "Tablet", new BigDecimal("499.49")),
        new Product(4L, "Monitor", new BigDecimal("299.99")),
        new Product(5L, "Keyboard", new BigDecimal("99.99"))
    );
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(1L, "Laptop", new BigDecimal("1200.50"));
    final Product EXPECTED_UPDATED_PRODUCT = new Product(1L, "Laptop Pro", new BigDecimal("1400.0"));

    @BeforeEach
    void init() {
        ds = new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .addScript("schema.sql")
            .addScript("data.sql")
            .build();
        repo = new ProductsRepositoryJdbcImpl(ds);
    }

    @Test
    void    testFindAll() {
        List<Product> products = repo.findAll();
        assertEquals(EXPECTED_FIND_ALL_PRODUCTS, products);
    }

    @Test
    void    testFindById() {
        repo = new ProductsRepositoryJdbcImpl(ds);
        Optional<Product> product = repo.findById(EXPECTED_FIND_BY_ID_PRODUCT.getIdentifier());
        assertTrue(product.isPresent());
        assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, product.get());
        repo = new ProductsRepositoryJdbcImpl(ds);
}

    @Test
    void testUpdate() {
        repo.update(EXPECTED_UPDATED_PRODUCT);
        Optional<Product> updated = repo.findById(1L);
        assertTrue(updated.isPresent());
        assertEquals(EXPECTED_UPDATED_PRODUCT, updated.get());
    }

    @Test
    void testSave() {
        Product newProduct = new Product(9L, "Keyboard", BigDecimal.valueOf(50.0));
        repo.save(newProduct);
        assertNotNull(newProduct.getIdentifier());
    }

    @Test
    void testDelete() {
        repo.delete(1L);
        assertFalse(repo.findById(1L).isPresent());
    }
}