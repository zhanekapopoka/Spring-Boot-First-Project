package com.example.springBootfirstapp.DTO;

import org.springframework.context.annotation.Primary;

import java.util.List;

public class ProductsForRecipeDto {
    private Integer id;
    private String name;
    private String slug;
    private List<String> alterNames;

    public ProductsForRecipeDto(Integer id, String name, String slug, List<String> alterNames) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.alterNames = alterNames;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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
}

