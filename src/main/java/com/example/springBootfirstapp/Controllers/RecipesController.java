package com.example.springBootfirstapp.Controllers;

import com.example.springBootfirstapp.DTO.RecipeDto;
import com.example.springBootfirstapp.Pagination.PaginatedRecipeResponse;
import com.example.springBootfirstapp.Entities.RecipeEntity;
import com.example.springBootfirstapp.Service.ProductService;
import com.example.springBootfirstapp.Service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RecipesController {
    private final RecipeService service;
//    private final ProductService service1;


    public RecipesController(RecipeService service, ProductService service1) {
        this.service = service;
//        this.service1 = service1;
    }

//    @PostMapping("/product")
//    public String postProduct(@RequestBody ProductEntity product) {
//        return service1.postProduct(product);
//    }
//
//    @PutMapping("/product/{id}")
//    public String updateProduct(@PathVariable int id, @RequestBody ProductEntity product) {
//        return service1.updateProduct(id, product);
//    }

    @PutMapping("/recipe/{id}")
    public String updateRecipe(@PathVariable int id, @RequestBody RecipeEntity recipe) {
        return service.updateRecipe(id, recipe);
    }

//    @GetMapping("/products")
//    public PaginatedProductResponse getAllProducts(@RequestParam(required = false) Integer page) {
//        return service1.getAllProducts(page);
//    }

//    @DeleteMapping("/product/{id}")
//    public String deleteByProductId(@PathVariable int id) {
//        return service1.deleteByProduct(id);
//    }

    @DeleteMapping("/recipes/{id}")
    public String deleteByRecipeId(@PathVariable int id) {
        return service.deleteByRecipe(id);
    }

    @GetMapping("/recipes")
    public PaginatedRecipeResponse getAllRecipes(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) String lang
    ) {
        return service.getAllRecipes(page, lang);
    }

//    @GetMapping(value = "/search", params = "product")
//    public ProductEntity getProduct(@RequestParam String product) {
//        return service1.findByProduct(product);
//    }

    @GetMapping(value = "/search",params = "recipe")
    public List<RecipeDto> searchRecipe(
            @RequestParam String recipe,
            @RequestParam(required = false) String lang
    ) {
        return service.findRecipeByName(recipe, lang);
    }

    @PostMapping("/recipe")
    public String postRecipe(@RequestBody RecipeEntity recipes) {
        return service.postRecipe(recipes);
    }
}
