package com.example.springBootfirstapp;

import java.util.ArrayList;

public class PaginatedProductResponse {
    private ArrayList<Product> items;
    private int count;
    private int currentPage;
    private int numberOfPages;

    public PaginatedProductResponse(ArrayList<Product> items, int count, int currentPage, int numberOfPages) {
        this.items = items;
        this.count = count;
        this.currentPage = currentPage;
        this.numberOfPages = numberOfPages;
    }

    public ArrayList<Product> getItems() {
        return items;
    }

    public int getCount() {
        return count;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }
}