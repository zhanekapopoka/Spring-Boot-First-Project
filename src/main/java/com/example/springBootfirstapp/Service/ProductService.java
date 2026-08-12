package com.example.springBootfirstapp.Service;

import com.example.springBootfirstapp.DTO.ProductDto;
import com.example.springBootfirstapp.DTO.RecipeDto;
import com.example.springBootfirstapp.Entities.AlterNameEntity;
import com.example.springBootfirstapp.Entities.RecipeEntity;
import com.example.springBootfirstapp.Entities.TranslateEntity;
import com.example.springBootfirstapp.PaginatedProductResponse;
import com.example.springBootfirstapp.Entities.ProductEntity;
import com.example.springBootfirstapp.Repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String deleteByProduct(int id) {
        ProductEntity productEntity=productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product not found"));

        productRepository.delete(productEntity);
        return "Product deleted";
    }

    public String updateProduct(int id, ProductEntity product) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productEntity.setName(product.getName());
        productEntity.setTranslations(product.getTranslations());
        productEntity.setAlterName(product.getAlterName());

        productRepository.save(productEntity);

        return "Product updated";
    }

    public String postProduct(ProductEntity product) {
        productRepository.save(product);
        return "Product created";
    }

    public PaginatedProductResponse getAllProducts(Integer page) {
        int limit = 5;
        int currentPage;
        if (page == null || page < 1) {
            currentPage = 1;
        } else {
            currentPage = page;
        }

        PageRequest pageRequest = PageRequest.of(currentPage - 1, limit);
        Page<ProductEntity> productPage = productRepository.findAll(pageRequest);
        ArrayList<ProductEntity> products = new ArrayList<>(productPage.getContent());
        int totalItems = (int) productPage.getTotalElements();
        int numberOfPages = productPage.getTotalPages();
        return new PaginatedProductResponse(products, products.size(), currentPage, numberOfPages);
    }

    public ProductDto findByProduct(String name, String lang) {
        ProductEntity productEntity = productRepository.findByNameOrAlterName(name)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return returnProduct(productEntity, lang);
    }

    private ProductDto returnProduct(ProductEntity productEntity, String lang) {
        String productName = getTranslationForProperties(
                productEntity.getTranslations(),
                "name",
                lang,
                productEntity.getName()
        );

        List<String> alterNames = productEntity.getAlterNames()
                .stream()
                .map(AlterNameEntity::getAlterName)
                .toList();

        List<RecipeDto> recipes = productEntity.getRecipeListForProduct()
                .stream()
                .map(recipeEntity -> returnRecipe(recipeEntity, lang))
                .toList();

        return new ProductDto(
                productName,
                alterNames,
                recipes,
                productEntity.getSlug(),
                productEntity.getId()
        );
    }

    private RecipeDto returnRecipe(RecipeEntity recipeEntity, String lang) {
        String recipeName = getTranslationForProperties(
                recipeEntity.getTranslations(),
                "name",
                lang,
                recipeEntity.getNameRecipe()
        );

        String recipeText = getTranslationForProperties(
                recipeEntity.getTranslations(),
                "recipe",
                lang,
                recipeEntity.getRecipe()
        );

        List<String> alterNames = recipeEntity.getAlterNameEntityList()
                .stream()
                .map(AlterNameEntity::getAlterName)
                .toList();

        return new RecipeDto(
                recipeEntity.getIdOfRecipe(),
                recipeName,
                alterNames,
                recipeEntity.getSlugRecipe(),
                recipeText
        );
    }


    private String getTranslationForProperties(List<TranslateEntity> translations, String fieldName, String lang, String defaultValue){
        if(lang == null || lang.isBlank()){
            return defaultValue;
        }
        return translations.stream()
                .filter(translateEntity -> translateEntity.getLangCode().equalsIgnoreCase(lang))
                .filter(translateEntity -> translateEntity.getFieldName().equalsIgnoreCase(fieldName))
                .map(TranslateEntity::getTranslation)
                .findFirst()
                .orElse(defaultValue);
    }
    }