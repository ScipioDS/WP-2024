package com.webprog.demo1.service.implementaion;

import com.webprog.demo1.model.Category;
import com.webprog.demo1.model.Manufacturer;
import com.webprog.demo1.model.Product;
import com.webprog.demo1.model.exceptions.CategoryNotFoundException;
import com.webprog.demo1.model.exceptions.ManufacturerNotFoundException;
import com.webprog.demo1.repository.InMemoryCategoryRepository;
import com.webprog.demo1.repository.InMemoryManufacturerRepository;
import com.webprog.demo1.repository.InMemoryProductRepository;
import com.webprog.demo1.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final InMemoryProductRepository productRepository;
    private final InMemoryManufacturerRepository manufacturerRepository;
    private final InMemoryCategoryRepository categoryRepository;

    public ProductServiceImpl(InMemoryProductRepository inMemoryProductRepository,
                              InMemoryManufacturerRepository manufacturerRepository,
                              InMemoryCategoryRepository categoryRepository) {
        this.productRepository = inMemoryProductRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        return this.productRepository.save(name, price, quantity, category, manufacturer);
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }

}
