package com.webprog.demo1.service;

import com.webprog.demo1.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> listCategories();

    Optional<Category> create(String name, String description);

    Optional<Category> update(String name, String description);

    void delete(String name);

    List<Category> searchCategories(String text);

}
