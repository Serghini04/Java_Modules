/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Product.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/04/04 00:46:37 by meserghi          #+#    #+#             */
/*   Updated: 2025/04/04 21:39:16 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    
    private Long identifier;
    private String name;
    private BigDecimal price;

    public Product (Long id, String name, BigDecimal price) {
        this.identifier = id;
        this.name = name;
        this.price = price;
    }
    public void setIdentifier(Long id) {
        identifier = id;
    }

    public Long getIdentifier() {
        return this.identifier;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
     
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return this.price;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(identifier, product.identifier) &&
            Objects.equals(name, product.name) &&
            price.compareTo(product.price) == 0; // Use compareTo for BigDecimal
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, name, price);
    }
    
    @Override
    public String toString() {
        return "Product{" +
                "identifier=" + identifier +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}