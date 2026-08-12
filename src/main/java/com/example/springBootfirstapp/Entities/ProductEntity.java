package com.example.springBootfirstapp.Entities;

import com.example.springBootfirstapp.Utils;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany
    @JoinTable(
            name = "product_translations",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "translation_id")
    )
    private List<TranslateEntity> translations;
    @ManyToMany
    @JoinTable(
            name = "product_alter_names",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "alter_name_id")
    )
    List<AlterNameEntity> alterNames;
    private String slug;
    @Column(name = "alter_name")
    private String alterName;
    @ManyToMany(mappedBy = "products")
    private List<RecipeEntity> recipeListForProduct;

    public ProductEntity(Integer id, String name,String altername, List<RecipeEntity> recipeListForProduct, List<TranslateEntity> translations, List<AlterNameEntity> alterNames) {
        this.id=id;
        this.name = name;
        this.alterName = altername;
        this.translations = translations;
        this.slug= Utils.returnSlug(name);
        this.recipeListForProduct=recipeListForProduct;
        this.alterNames = alterNames;
    }
    public ProductEntity() {
    }

    public void setName(String name) {
        this.name = name;
        this.slug= Utils.returnSlug(name);
    }

    public void setAlterName(String alterName) {
        this.alterName = alterName;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public String getAlterName() {
        return alterName;
    }

    public void setRecipeListForProduct(List<RecipeEntity> recipeListForProduct){
        this.recipeListForProduct=recipeListForProduct;
    }
    public List<TranslateEntity> getTranslations(){
        return translations;
    }
    public void setTranslations(List<TranslateEntity> translations){
        this.translations=translations;
    }
    public List<RecipeEntity> getRecipeListForProduct(){
        return recipeListForProduct;
    }

    public void setAlterNames(List<AlterNameEntity> alterNames) {
        this.alterNames = alterNames;
    }

    public List<AlterNameEntity> getAlterNames() {
        return alterNames;
    }
}