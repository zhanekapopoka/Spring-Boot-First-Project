package com.example.springBootfirstapp;

public class Recipes {
    private String name_of_recipe;
    private String translate_of_recipe;
    private String slug_of_recipe;
    private String alter_name_of_recipe;
    private String recipe;

    public Recipes(String name, String translate, String alterName, String recipe) {
        this.name_of_recipe = name;
        this.translate_of_recipe = translate;
        this.alter_name_of_recipe = alterName;
        this.recipe = recipe;
        this.slug_of_recipe = Utils.returnSlug(name);
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
}
