package com.example.helloServer.Repository;

import com.example.helloServer.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Extra custom queries can be added here if needed
}
