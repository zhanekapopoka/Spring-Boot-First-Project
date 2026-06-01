package com.example.springBootfirstapp;

import java.util.List;

public class Product {
    private Integer id;
    private String name;
    private String translate;
    private String slug;
    private String alterName;
    private List<String> recipeListForProduct;

    public Product(Integer id,String name, String translate, String altername,List<String> recipeListForProduct) {
        this.id=id;
        this.name = name;
        this.alterName = altername;
        this.translate = translate;

        this.slug=Utils.returnSlug(name);
        this.recipeListForProduct=recipeListForProduct;
    }
    public Product() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public void setAlterName(String alterName) {
        this.alterName = alterName;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
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