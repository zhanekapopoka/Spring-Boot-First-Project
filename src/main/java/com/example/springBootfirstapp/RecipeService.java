package com.example.springBootfirstapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RecipeService {
    public String postRecipe(Recipes recipe) {
        String sql = "INSERT INTO recipes (name_of_recipe,translate_of_recipe,slug_of_recipe,alter_name_of_recipe,recipe) VALUES(?,?,?,?,?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            String name = recipe.getName();
            String altername = recipe.getAlter_name_of_recipe();
            String recipeText = recipe.getRecipe();
            String translate = recipe.getTranslate_of_recipe();
            String slug = Utils.returnSlug(name);
            pstm.setString(1, name);
            pstm.setString(2, translate);
            pstm.setString(3, slug);
            pstm.setString(4, altername);
            pstm.setString(5, recipeText);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Recipe created";
    }

    public String updateRecipe(int id, Recipes recipe) {
        String sql = "UPDATE recipes\n" +
                "SET name_of_recipe = ?, translate_of_recipe = ?, slug_of_recipe = ?, alter_name_of_recipe = ?,recipe = ?\n" +
                "WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            String name = recipe.getName();
            String altername = recipe.getAlter_name_of_recipe();
            String slug = Utils.returnSlug(name);
            String translate = recipe.getTranslate_of_recipe();
            String recipeText = recipe.getRecipe();
            pstm.setString(1, name);
            pstm.setString(2, translate);
            pstm.setString(3, slug);
            pstm.setString(4, altername);
            pstm.setString(5, recipeText);
            pstm.setInt(6, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Recipe updated";
    }

    public String deleteByRecipe(int id) {
        String sql = "DELETE FROM recipes WHERE id =?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
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
        int offset = (currentPage - 1) * limit;
        ArrayList<Recipes> recipes = getRecipesByLimitAndOffset(limit, offset);
        int totalItems = countRecipes();
        int numberOfPages = (int) Math.ceil(totalItems / (double) limit);
        return new PaginatedRecipeResponse(
                recipes,
                recipes.size(),
                currentPage,
                numberOfPages
        );
    }

    private ArrayList<Recipes> getRecipesByLimitAndOffset(int limit, int offset) {
        ArrayList<Recipes> recipes = new ArrayList<>();
        String sql = "SELECT * FROM recipes ORDER BY id LIMIT ? OFFSET ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, limit);
            pstm.setInt(2, offset);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Recipes recipe = mapRecipe(rs);
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    private int countRecipes() {
        String sql = "SELECT COUNT(*) FROM recipes";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Recipes mapRecipe(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name_of_recipe");
        String translate = rs.getString("translate_of_recipe");
        String alterName = rs.getString("alter_name_of_recipe");
        String recipeText = rs.getString("recipe");
        List<String> products = new ArrayList<>();
        return new Recipes(id, name, translate, alterName, recipeText, products);
    }


    public Recipes findRecipeByName(String input) {
        String sql = "SELECT * FROM recipes WHERE LOWER(name_of_recipe) = LOWER(?)";
        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            pstm.setString(1, input);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int recipeId = rs.getInt("id");
                String name = rs.getString("name_of_recipe");
                String translate = rs.getString("translate_of_recipe");
                String alterName = rs.getString("alter_name_of_recipe");
                String recipeText = rs.getString("recipe");
                String join = "SELECT p.name " +
                        "FROM recipe__products rp " +
                        "JOIN products p ON rp.product_id = p.id " +
                        "WHERE rp.recipe_id = ?";

                PreparedStatement pstm1 = conn.prepareStatement(join);
                pstm1.setInt(1, recipeId);
                List<String> products = new ArrayList<>();
                ResultSet rs2 = pstm1.executeQuery();
                while (rs2.next()) {
                    products.add(rs2.getString("name"));
                }
                Recipes recipe = new Recipes(recipeId, name, translate, alterName, recipeText, products);
                return recipe;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Product findByProduct(String input) {
        String sql2 = "SELECT * FROM products WHERE LOWER(name) = LOWER(?)";
        try (
                Connection conn2 = DbConnection.getConnection();
                PreparedStatement pstm2 = conn2.prepareStatement(sql2)
        ) {
            pstm2.setString(1, input);
            ResultSet rs2 = pstm2.executeQuery();
            if (rs2.next()) {
                int productId = rs2.getInt("id");
                String nameOfProduct = rs2.getString("name");
                String translate = rs2.getString("translate_name");
                String alterName = rs2.getString("alter_name");

                String join2 = "SELECT r.name_of_recipe, r.recipe " +
                        "FROM recipe__products rp " +
                        "JOIN recipes r ON rp.recipe_id = r.id " +
                        "WHERE rp.product_id = ?";

                PreparedStatement pstm3 = conn2.prepareStatement(join2);
                pstm3.setInt(1, productId);

                ResultSet rs3 = pstm3.executeQuery();
                List<String> recipeList = new ArrayList<>();
                while (rs3.next()) {
                    String recipeName = rs3.getString("name_of_recipe");
                    String recipeText = rs3.getString("recipe");
                    recipeList.add(recipeName + " | " + recipeText);
                }
                Product product = new Product(productId, nameOfProduct, translate, alterName, recipeList);
                return product;
            } else {
                return null;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
