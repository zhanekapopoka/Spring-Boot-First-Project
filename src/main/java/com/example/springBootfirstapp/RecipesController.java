package com.example.springBootfirstapp;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RecipesController {
    private final RecipeService service;
    private final ProductService service1;


    public RecipesController(RecipeService service, ProductService service1) {
        this.service = service;
        this.service1 = service1;
    }

    @PostMapping("/product")
    public String postProduct(@RequestBody Product product) {
        return service1.postProduct(product);
    }

    @PutMapping("/product/{id}")
    public String updateProduct(@PathVariable int id, @RequestBody Product product) {
        return service1.updateProduct(id, product);
    }

    @PutMapping("/recipe/{id}")
    public String updateRecipe(@PathVariable int id, @RequestBody Recipes recipe) {
        return service.updateRecipe(id, recipe);
    }

    @GetMapping("/products")
    public PaginatedProductResponse getAllProducts(@RequestParam(required = false) Integer page) {
        return service1.getAllProducts(page);
    }

    @DeleteMapping("/product/{id}")
    public String deleteByProductId(@PathVariable int id) {
        return service1.deleteByProduct(id);
    }

    @DeleteMapping("/recipes/{id}")
    public String deleteByRecipeId(@PathVariable int id) {
        return service.deleteByRecipe(id);
    }

    @GetMapping("/recipes")
    public PaginatedRecipeResponse getAllRecipes(@RequestParam(required = false) Integer page) {
        return service.getAllRecipes(page);
    }

    @GetMapping(params = "product")
    public Product getProduct(@RequestParam String product) {
        return service.findByProduct(product);
    }

    @GetMapping(params = "recipe")
    public Recipes getRecipe(@RequestParam String recipe) {
        return service.findRecipeByName(recipe);
    }

    @PostMapping("/recipe")
    public String postRecipe(@RequestBody Recipes recipes) {
        return service.postRecipe(recipes);
    }
}
