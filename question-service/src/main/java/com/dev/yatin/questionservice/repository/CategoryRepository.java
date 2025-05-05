package com.dev.yatin.questionservice.repository;

import com.dev.yatin.questionservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    /****
 * Retrieves a category by its name.
 *
 * @param name the name of the category to search for
 * @return an Optional containing the matching Category if found, or empty if not found
 */
Optional<Category> findByName(String name);
    /**
 * Checks if a category with the specified name exists.
 *
 * @param name the name of the category to check
 * @return true if a category with the given name exists, false otherwise
 */
boolean existsByName(String name);
} 