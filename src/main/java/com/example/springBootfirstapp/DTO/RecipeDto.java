package com.example.springBootfirstapp.DTO;

import java.util.List;

public class RecipeDto {
    private Integer id;
    private String name;
    private List<String> alterNames;
    private String slug;
    private String recipe;
    private List<ProductsForRecipeDto> products;

    public RecipeDto(Integer id,String name, List<String> alterName, String slug, String recipe,List<ProductsForRecipeDto> products){
        this.alterNames =alterName;
        this.name=name;
        this.id=id;
        this.slug=slug;
        this.recipe=recipe;
        this.products=products;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAlterNames() {
        return alterNames;
    }

    public void setAlterNames(List<String> alterNames) {
        this.alterNames = alterNames;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getRecipe(){
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public List<ProductsForRecipeDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsForRecipeDto> products) {
        this.products = products;
    }
}
