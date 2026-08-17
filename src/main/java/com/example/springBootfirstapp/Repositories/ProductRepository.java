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
        SELECT DISTINCT p FROM ProductEntity p
        LEFT JOIN p.alterNames a
        LEFT JOIN p.translations t
        WHERE LOWER(p.name) = LOWER(:name)
           OR LOWER(a.alterName) = LOWER(:name)
           OR LOWER(t.translation) = LOWER(:name)
        """)
    Optional<ProductEntity> findByNameOrAlterNameOrTranslation(@Param("name") String name);
}
