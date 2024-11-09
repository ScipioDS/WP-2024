package com.webprog.demo1.repository;

import com.webprog.demo1.bootstrap.DataHolder;
import com.webprog.demo1.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class InMemoryCategoryRepository {
    public Optional<Category> save(Category category) {
        // If the category already exists, remove it and add the new one
        DataHolder.categories.removeIf(c -> c.getName().equals(category.getName()));
        DataHolder.categories.add(category);

        return Optional.of(category);
    }

    public List<Category> findAll() {
        return DataHolder.categories;
    }

    public Optional<Category> findByName(String name) {
        return DataHolder.categories.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst();
    }

    public Optional<Category> findById(Long id){
        return DataHolder.categories.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public List<Category> search(String text) {
        return DataHolder.categories.stream()
                .filter(c -> c.getName().contains(text) ||
                        c.getDescription().contains(text))
                .toList();
    }

    public void delete(String name) {
        DataHolder.categories.removeIf(c -> c.getName().equals(name));
    }

    public void deleteById(Long id){
        DataHolder.categories.removeIf(c -> c.getId().equals(id));
    }
}
