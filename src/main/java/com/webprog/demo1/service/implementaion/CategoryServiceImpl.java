package com.webprog.demo1.service.implementaion;

import com.webprog.demo1.model.Category;
import com.webprog.demo1.repository.InMemoryCategoryRepository;
import com.webprog.demo1.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final InMemoryCategoryRepository categoryRepository;

    public CategoryServiceImpl(InMemoryCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> create(String name, String description) {
        if (name == null || name.isEmpty() || description == null || description.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Category category = new Category(name, description);

        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> update(String name, String description) {
        if (name == null || name.isEmpty() || description == null || description.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Category category = new Category(name, description);

        return categoryRepository.save(category);
    }

    @Override
    public void delete(String name) {
        categoryRepository.delete(name);
    }

    @Override
    public List<Category> searchCategories(String text) {
        return categoryRepository.search(text);
    }

}
