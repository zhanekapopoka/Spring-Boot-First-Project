package com.example.springBootfirstapp;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RecipesController {
    private final RecipeService service;

    public RecipesController(RecipeService service) {
        this.service = service;
    }

    @GetMapping(params="recipe")
    public Recipes getRecipe(@RequestParam String recipe) {//замениить на query
        return service.findRecipeByName(recipe);
    }
    @GetMapping(params="product")
    public Product getProduct(@RequestParam String product){
        return service.findByProduct(product);
    }
}
