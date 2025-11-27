package com.example.helloServer.Service.impl;

import com.example.helloServer.Model.Category;
import com.example.helloServer.Repository.CategoryRepository;
import com.example.helloServer.Service.CategoryService;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isPresent()) {
            categoryRepository.deleteById(id);
            return ResponseEntity.ok("Category with ID " + id + " deleted successfully.");
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Category with ID " + id + " not found or already deleted.");
    }

}
