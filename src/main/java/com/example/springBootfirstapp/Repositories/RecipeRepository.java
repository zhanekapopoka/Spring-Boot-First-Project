package com.example.springBootfirstapp.Repositories;

import com.example.springBootfirstapp.Entities.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer> {

    Optional<RecipeEntity> findByNameRecipe(String nameRecipe);

    Optional<RecipeEntity> findBySlugRecipe(String slugRecipe);
    @Query("""
        SELECT DISTINCT r FROM RecipeEntity r
        LEFT JOIN r.alterNames a
        WHERE LOWER(r.nameRecipe) = LOWER(:name)
           OR LOWER(a.alterName) = LOWER(:name)
        """)
    List<RecipeEntity> findAllByNameOrAlterName(@Param("name") String name);
}