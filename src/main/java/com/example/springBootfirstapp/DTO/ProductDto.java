package com.example.springBootfirstapp.DTO;

import java.util.List;

public class ProductDto {
    private Integer id;
    private String name;
    private List<String> alterNames;
    private String slug;
    private List<RecipeDto> recipes;

    public ProductDto(String name, List<String> alterName, List<RecipeDto> recipesForProduct, String slug, Integer id){
        this.alterNames =alterName;
        this.name=name;
        this.recipes = recipesForProduct;
        this.slug=slug;
        this.id=id;
    }

    public String getName(){
        return name;
    }
    public List<String> getAlterNames(){
        return alterNames;
    }
    public String getSlug(){
        return slug;
    }
    public Integer getId(){
        return id;
    }
    public void setName(String name){
        this.name=name;
    }

    public void setAlterNames(List<String> alterName) {
        this.alterNames = alterNames;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<RecipeDto> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeDto> recipes) {
        this.recipes = recipes;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
