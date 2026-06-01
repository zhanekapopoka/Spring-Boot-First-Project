package com.example.springBootfirstapp;

import java.util.ArrayList;
import java.util.List;

public class Recipes {
    private Integer id;
    private String name_of_recipe;
    private String translate_of_recipe;
    private String slug_of_recipe;
    private String alter_name_of_recipe;
    private String recipe;
    private List<String> products= new ArrayList<>();

    public Recipes(Integer id,String name, String translate, String alterName, String recipe,List<String> products) {
        this.id =id;
        this.name_of_recipe = name;
        this.translate_of_recipe = translate;
        this.alter_name_of_recipe = alterName;
        this.recipe = recipe;
        this.slug_of_recipe = Utils.returnSlug(name);
        this.products=products;
    }

    public Integer getIdOfRecipe(){
        return id;
    }

    public String getName() {
        return name_of_recipe;
    }

    public String getTranslate_of_recipe() {
        return translate_of_recipe;
    }

    public String getSlug_of_recipe() {
        return slug_of_recipe;
    }

    public String getAlter_name_of_recipe() {
        return alter_name_of_recipe;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public List<String> getProducts() {
        return products;
    }
}

