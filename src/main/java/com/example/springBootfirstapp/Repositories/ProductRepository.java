package com.example.springBootfirstapp.Repositories;

import com.example.springBootfirstapp.Entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository <ProductEntity, Integer>{
Optional<ProductEntity> findByNameIgnoreCase(String name);
Optional<ProductEntity> findBySlug(String slug);
    @Query("""
        SELECT p FROM ProductEntity p
        LEFT JOIN p.alterNames a
        WHERE LOWER(p.name) = LOWER(:name)
           OR LOWER(a.alterName) = LOWER(:name)
        """)
    Optional<ProductEntity> findByNameOrAlterName(@Param("name") String name);
}
