package com.example.helloServer.Service;

import com.example.helloServer.Model.Category;
import java.util.List;

import org.springframework.http.ResponseEntity;

public interface CategoryService {
    List<Category> findAll();

    Category save(Category category);

    Category findById(Long id);

    ResponseEntity<String> deleteById(Long id);
}
