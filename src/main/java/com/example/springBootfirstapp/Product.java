package com.example.springBootfirstapp;

import java.util.List;

public class Product {
    private String name;
    private String translate;
    private String slug;
    private String alterName;
    private List<String> recipeListForProduct;

    public Product(String name, String translate, String altername,List<String> recipeListForProduct) {
        this.name = name;
        this.alterName = altername;
        this.translate = translate;

        this.slug=Utils.returnSlug(name);
        this.recipeListForProduct=recipeListForProduct;
    }

    public String getName() {
        return name;
    }

    public String getTranslate() {
        return translate;
    }

    public String getSlug() {
        return slug;
    }

    public String getAlterName() {
        return alterName;
    }

    public List<String> getRecipeListForProduct() {
        return recipeListForProduct;
    }
    //    public static void main(String[] args) {
//        Product product1 = new Product("зеленое яблоко", "Green apple", "Fruit",);
//        System.out.println(product1.getName()+"->"+product1.getSlug());
//    }
}