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
    public String updateProduct(@RequestBody int id, Product product ){
        return service1.updateProduct(id,product);
    }

    @GetMapping("/products")
    public ArrayList<Product> getAllProducts() {
        return service1.getAllProducts();
    }
    @DeleteMapping("/product/{id}")
    public String deleteByProductId(@PathVariable int id){
        return service1.deleteByProduct(id);
    }
    @DeleteMapping("/recipes/{id}")
    public String deleteByRecipeId(@PathVariable int id){
        return service.deleteByRecipe(id);
    }

    @GetMapping("/recipes")
    public ArrayList<Recipes> getAllRecipes() {
        return service.getAllRecipes();
    }

    @GetMapping(params = "recipes")
    public Recipes getRecipe(@RequestParam String recipe) {//замениить на query
        return service.findRecipeByName(recipe);
    }

    @PostMapping("/recipe")
    public String postRecipe(@RequestBody Recipes recipes) {
        return service.postRecipe(recipes);
    }
}
