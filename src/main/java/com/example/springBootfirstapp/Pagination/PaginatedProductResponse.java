package com.example.springBootfirstapp.Pagination;

import com.example.springBootfirstapp.DTO.ProductDto;
import com.example.springBootfirstapp.Entities.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class PaginatedProductResponse {
    private List<ProductDto> items;
    private int count;
    private int currentPage;
    private int numberOfPages;

    public PaginatedProductResponse(List<ProductDto> items, int count, int currentPage, int numberOfPages) {
        this.items = items;
        this.count = count;
        this.currentPage = currentPage;
        this.numberOfPages = numberOfPages;
    }

    public List<ProductDto> getItems() {
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