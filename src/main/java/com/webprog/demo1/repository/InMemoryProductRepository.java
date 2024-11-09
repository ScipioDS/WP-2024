package com.webprog.demo1.repository;

import com.webprog.demo1.bootstrap.DataHolder;
import com.webprog.demo1.model.Category;
import com.webprog.demo1.model.Manufacturer;
import com.webprog.demo1.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProductRepository {
    public List<Product> findAll() {
        return DataHolder.products;
    }
    public Optional<Product> findById(Long id) {
        return DataHolder.products.stream().filter(i -> i.getId().equals(id)).findFirst();
    }
    public Optional<Product> findByName(String name) {
        return DataHolder.products.stream().filter(i -> i.getName().equals(name)).findFirst();
    }

    public void deleteById(Long id) {
        DataHolder.products.removeIf(i -> i.getId().equals(id));
    }

    public Optional<Product> save(String name, Double price, Integer quantity,
                                  Category category, Manufacturer manufacturer) {
        if (category == null || manufacturer == null) {
            throw new IllegalArgumentException();
        }

        Product product = new Product(name, price, quantity, category, manufacturer);
        DataHolder.products.removeIf(p -> p.getName().equals(product.getName()));
        DataHolder.products.add(product);
        return Optional.of(product);
    }

}
