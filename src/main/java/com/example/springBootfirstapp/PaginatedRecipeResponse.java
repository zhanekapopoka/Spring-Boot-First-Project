package com.example.springBootfirstapp;

import java.util.ArrayList;

public class PaginatedRecipeResponse {
    private ArrayList<Recipes> items;
    private int count;
    private int currentPage;
    private int numberOfPages;

    public PaginatedRecipeResponse(ArrayList<Recipes> items, int count,int currentPage, int numberOfPages){
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
    public ArrayList<Recipes> getItems(){
        return items;
    }
}
