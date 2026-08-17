package com.example.springBootfirstapp.Service;
import java.util.ArrayList;
import java.util.List;

import com.example.springBootfirstapp.DTO.ProductsForRecipeDto;
import com.example.springBootfirstapp.DTO.RecipeDto;
import com.example.springBootfirstapp.Entities.AlterNameEntity;
import com.example.springBootfirstapp.Entities.ProductEntity;
import com.example.springBootfirstapp.Entities.RecipeEntity;
import com.example.springBootfirstapp.Entities.TranslateEntity;
import com.example.springBootfirstapp.Pagination.PaginatedRecipeResponse;
import com.example.springBootfirstapp.Repositories.RecipeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService (RecipeRepository recipeRepository){
        this.recipeRepository=recipeRepository;
    }
    public String postRecipe(RecipeEntity recipe) {
        recipeRepository.save(recipe);
        return "Recipe created";
//        String sql = "INSERT INTO recipes (name_of_recipe,translate_of_recipe,slug_of_recipe,alter_name_of_recipe,recipe) VALUES(?,?,?,?,?)";
//        try (Connection conn = DbConnection.getConnection();
//             PreparedStatement pstm = conn.prepareStatement(sql)) {
//            String name = recipe.getName();
//            String altername = recipe.getAlter_name_of_recipe();
//            String recipeText = recipe.getRecipe();
//            String translate = recipe.getTranslate_of_recipe();
//            String slug = Utils.returnSlug(name);
//            pstm.setString(1, name);
//            pstm.setString(2, translate);
//            pstm.setString(3, slug);
//            pstm.setString(4, altername);
//            pstm.setString(5, recipeText);
//            pstm.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return "Recipe created";
    }

    public String updateRecipe(int id, RecipeEntity recipe) {
        RecipeEntity recipeEntity=recipeRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Recipe not found"));

        recipeEntity.setNameRecipe(recipe.getNameRecipe());
        recipeEntity.setTranslations(recipe.getTranslations());
        recipeEntity.setAlterNameRecipe(recipe.getAlterNameRecipe());
        recipeEntity.setRecipe(recipe.getRecipe());

        recipeRepository.save(recipeEntity);
        return "Recipe updated";
//        String sql = "UPDATE recipes\n" +
//                "SET name_of_recipe = ?, translate_of_recipe = ?, slug_of_recipe = ?, alter_name_of_recipe = ?,recipe = ?\n" +
//                "WHERE id = ?";
//        try (Connection conn = DbConnection.getConnection();
//             PreparedStatement pstm = conn.prepareStatement(sql)) {
//            String name = recipe.getName();
//            String altername = recipe.getAlter_name_of_recipe();
//            String slug = Utils.returnSlug(name);
//            String translate = recipe.getTranslate_of_recipe();
//            String recipeText = recipe.getRecipe();
//            pstm.setString(1, name);
//            pstm.setString(2, translate);
//            pstm.setString(3, slug);
//            pstm.setString(4, altername);
//            pstm.setString(5, recipeText);
//            pstm.setInt(6, id);
//            pstm.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return "Recipe updated";
    }

    public String deleteByRecipe(int id) {
        RecipeEntity recipeEntity=recipeRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Recipe not found"));
        recipeRepository.delete(recipeEntity);
        return "Recipe deleted";
    }

    public PaginatedRecipeResponse getAllRecipes(Integer page, String lang) {
        int limit = 5;
        int currentPage;

        if (page == null || page < 1) {
            currentPage = 1;
        } else {
            currentPage = page;
        }

        PageRequest pageRequest = PageRequest.of(currentPage - 1, limit);
        Page<RecipeEntity> recipePage = recipeRepository.findAll(pageRequest);

        List<RecipeDto> recipes = recipePage.getContent()
                .stream()
                .map(recipeEntity -> returnRecipe(recipeEntity, lang))
                .toList();

        int numberOfPages = recipePage.getTotalPages();

        return new PaginatedRecipeResponse(
                recipes,
                recipes.size(),
                currentPage,
                numberOfPages
        );
    }


    public List<RecipeDto> findRecipeByName(String name, String lang) {
        List<RecipeEntity> recipes = recipeRepository.findAllByNameOrAlterNameOrTranslation(name);

        if (recipes.isEmpty()) {
            throw new RuntimeException("Recipe not found");
        }

        return recipes.stream()
                .map(recipeEntity -> returnRecipe(recipeEntity, lang))
                .toList();
    }

    private ProductsForRecipeDto returnProductShort(ProductEntity productEntity, String lang) {
        String productName = getTranslatedProperties(
                productEntity.getTranslations(),
                "name",
                lang,
                productEntity.getName()
        );

        List<String> alterNames = productEntity.getAlterNames()
                .stream()
                .map(AlterNameEntity::getAlterName)
                .toList();

        return new ProductsForRecipeDto(
                productEntity.getId(),
                productName,
                productEntity.getSlug(),
                alterNames
        );
    }

    private RecipeDto returnRecipe(RecipeEntity recipeEntity, String lang) {
        String recipeName = getTranslatedProperties(
                recipeEntity.getTranslations(),
                "name",
                lang,
                recipeEntity.getNameRecipe()
        );

        List<ProductsForRecipeDto> products = recipeEntity.getProducts()
                .stream()
                .map(productEntity -> returnProductShort(productEntity, lang))
                .toList();

        String recipeText = getTranslatedProperties(
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
                recipeText,
                products
        );
    }

    private String getTranslatedProperties(List<TranslateEntity> translations, String fieldName, String lang, String defaultValue){
        if(lang == null || lang.isBlank()){
            return defaultValue;
        } if(translations == null || translations.isEmpty()){
            return defaultValue;
        }
        return translations.stream()
                .filter(translateEntity -> translateEntity.getLangCode().equalsIgnoreCase(lang))
                .filter(translateEntity -> translateEntity.getFieldName().equalsIgnoreCase(fieldName))
                .map(TranslateEntity :: getTranslation)
                .findFirst()
                .orElse(defaultValue);
    }
}

//    public ProductEntity findByProduct(String input) {
//        String sql2 = "SELECT * FROM products WHERE LOWER(name) = LOWER(?)";
//        try (
//                Connection conn2 = DbConnection.getConnection();
//                PreparedStatement pstm2 = conn2.prepareStatement(sql2)
//        ) {
//            pstm2.setString(1, input);
//            ResultSet rs2 = pstm2.executeQuery();
//            if (rs2.next()) {
//                int productId = rs2.getInt("id");
//                String nameOfProduct = rs2.getString("name");
//                String translate = rs2.getString("translate_name");
//                String alterName = rs2.getString("alter_name");
//
//                String join2 = "SELECT r.name_of_recipe, r.recipe " +
//                        "FROM recipe__products rp " +
//                        "JOIN recipes r ON rp.recipe_id = r.id " +
//                        "WHERE rp.product_id = ?";
//
//                PreparedStatement pstm3 = conn2.prepareStatement(join2);
//                pstm3.setInt(1, productId);
//
//                ResultSet rs3 = pstm3.executeQuery();
//                List<String> recipeList = new ArrayList<>();
//                while (rs3.next()) {
//                    String recipeName = rs3.getString("name_of_recipe");
//                    String recipeText = rs3.getString("recipe");
//                    recipeList.add(recipeName + " | " + recipeText);
//                }
//                ProductEntity product = new ProductEntity(productId, nameOfProduct, translate, alterName, recipeList);
//                return product;
//            } else {
//                return null;
//            }
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//        }
//        return null;
//    }
//}
