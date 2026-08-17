package com.example.springBootfirstapp.Pagination;

import com.example.springBootfirstapp.DTO.RecipeDto;
import com.example.springBootfirstapp.Entities.RecipeEntity;

import java.util.ArrayList;
import java.util.List;

public class PaginatedRecipeResponse {
    private List<RecipeDto> items;
    private int count;
    private int currentPage;
    private int numberOfPages;

    public PaginatedRecipeResponse(List<RecipeDto> items, int count, int currentPage, int numberOfPages){
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
    public List<RecipeDto> getItems(){
        return items;
    }
}
