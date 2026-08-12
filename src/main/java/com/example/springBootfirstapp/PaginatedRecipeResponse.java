package com.example.springBootfirstapp;

import com.example.springBootfirstapp.Entities.RecipeEntity;

import java.util.ArrayList;

public class PaginatedRecipeResponse {
    private ArrayList<RecipeEntity> items;
    private int count;
    private int currentPage;
    private int numberOfPages;

    public PaginatedRecipeResponse(ArrayList<RecipeEntity> items, int count, int currentPage, int numberOfPages){
        this.count=count;
        this.currentPage=currentPage;
        this.numberOfPages=numberOfPages;
        this.items=items;
    }
    public int getNumberOfPages() {
        return numberOfPages;
    }

    public int getCount(){
        return count;
    }
    public int getCurrentPage(){
        return currentPage;
    }
    public ArrayList<RecipeEntity> getItems(){
        return items;
    }
}
