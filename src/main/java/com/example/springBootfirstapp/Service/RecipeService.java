package com.example.springBootfirstapp.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.springBootfirstapp.*;
import com.example.springBootfirstapp.DTO.RecipeDto;
import com.example.springBootfirstapp.Entities.AlterNameEntity;
import com.example.springBootfirstapp.Entities.ProductEntity;
import com.example.springBootfirstapp.Entities.RecipeEntity;
import com.example.springBootfirstapp.Entities.TranslateEntity;
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

    public PaginatedRecipeResponse getAllRecipes(Integer page) {
        int limit = 5;
        int currentPage;
        if (page == null || page < 1) {
            currentPage = 1;
        } else {
            currentPage = page;
        }
        PageRequest pageRequest = PageRequest.of(currentPage-1,limit);
        Page<RecipeEntity> recipePage = recipeRepository.findAll(pageRequest);
        ArrayList<RecipeEntity> recipes = new ArrayList<>(recipePage.getContent());
        int totalItems=(int) recipePage.getTotalElements();
        int numberOfPages = recipePage.getTotalPages();
        return new PaginatedRecipeResponse(
                recipes,
                recipes.size(),
                currentPage,
                numberOfPages
        );
    }

//    private ArrayList<RecipeEntity> getRecipesByLimitAndOffset(int limit, int offset) {
//        ArrayList<RecipeEntity> recipes = new ArrayList<>();
//        String sql = "SELECT * FROM recipes ORDER BY id LIMIT ? OFFSET ?";
//        try (Connection conn = DbConnection.getConnection();
//             PreparedStatement pstm = conn.prepareStatement(sql)) {
//            pstm.setInt(1, limit);
//            pstm.setInt(2, offset);
//            ResultSet rs = pstm.executeQuery();
//            while (rs.next()) {
//                RecipeEntity recipe = mapRecipe(rs);
//                recipes.add(recipe);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return recipes;
//    }
//
//    private int countRecipes() {
//        String sql = "SELECT COUNT(*) FROM recipes";
//        try (Connection conn = DbConnection.getConnection();
//             PreparedStatement pstm = conn.prepareStatement(sql)) {
//            ResultSet rs = pstm.executeQuery();
//            if (rs.next()) {
//                return rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    private RecipeEntity mapRecipe(ResultSet rs) throws SQLException {
//        int id = rs.getInt("id");
//        String name = rs.getString("name_of_recipe");
//        String translate = rs.getString("translate_of_recipe");
//        String alterName = rs.getString("alter_name_of_recipe");
//        String recipeText = rs.getString("recipe");
//        List<String> products = new ArrayList<>();
//        return new RecipeEntity(id, name, translate, alterName, recipeText, products);
//    }


    public List<RecipeDto> findRecipeByName(String name, String lang) {
        List<RecipeEntity> recipes = recipeRepository.findAllByNameOrAlterName(name);

        if (recipes.isEmpty()) {
            throw new RuntimeException("Recipe not found");
        }

        return recipes.stream()
                .map(recipeEntity -> returnRecipe(recipeEntity, lang))
                .toList();
    }

    private RecipeDto returnRecipe(RecipeEntity recipeEntity, String lang) {
        String recipeName = getTranslatedProperties(
                recipeEntity.getTranslations(),
                "name",
                lang,
                recipeEntity.getNameRecipe()
        );

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
                recipeText
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
