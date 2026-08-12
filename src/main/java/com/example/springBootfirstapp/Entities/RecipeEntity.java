package com.example.springBootfirstapp.Entities;

import com.example.springBootfirstapp.Utils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "recipes")
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name_of_recipe")
    private String nameRecipe;
    @Column(name = "slug_of_recipe")
    private String slugRecipe;
    @Column(name = "alter_name_of_recipe")
    private String alterNameRecipe;
    private String recipe;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "recipe__products",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductEntity> products;

    @OneToMany
    @JoinTable(name = "recipe_translations",
    joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "translation_id")
    )
    private List<TranslateEntity> translations;
    @ManyToMany
    @JoinTable(name = "recipe_alter_names",
    joinColumns = @JoinColumn(name = "recipe_id"),
    inverseJoinColumns = @JoinColumn(name = "alter_name_id")
    )
    private List<AlterNameEntity>alterNames;

    public RecipeEntity() {
    }

    public RecipeEntity(Integer id, String nameRecipe,String alterNameRecipe, String recipe, List<ProductEntity> products, List<TranslateEntity> translations,List<AlterNameEntity> alterNames) {
        this.id = id;
        this.translations=translations;
        this.nameRecipe = nameRecipe;
        this.alterNameRecipe = alterNameRecipe;
        this.recipe = recipe;
        this.slugRecipe = Utils.returnSlug(nameRecipe);
        this.products = products;
        this.alterNames = alterNames;
    }

    public List<AlterNameEntity> getAlterNameEntityList() {
        return alterNames;
    }

    public void setAlterNameEntityList(List<AlterNameEntity> alterNames) {
        this.alterNames = alterNames;
    }

    public Integer getIdOfRecipe() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameRecipe() {
        return nameRecipe;
    }

    public void setNameRecipe(String nameRecipe) {
        this.nameRecipe = nameRecipe;
        this.slugRecipe = Utils.returnSlug(nameRecipe);
    }

    public String getSlugRecipe() {
        return slugRecipe;
    }

    public void setSlugRecipe(String slugRecipe) {
        this.slugRecipe = slugRecipe;
    }

    public String getAlterNameRecipe() {
        return alterNameRecipe;
    }

    public void setAlterNameRecipe(String alterNameRecipe) {
        this.alterNameRecipe = alterNameRecipe;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public List<TranslateEntity> getTranslations() {
        return translations;
    }

    public void setTranslations(List<TranslateEntity> translations) {
        this.translations = translations;
    }
}