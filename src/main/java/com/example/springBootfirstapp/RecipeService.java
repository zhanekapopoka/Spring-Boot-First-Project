package com.example.springBootfirstapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeService {
    public void findRecipeByName(String input) {
        String sql = "SELECT * FROM recipes WHERE LOWER(name_of_recipe) = LOWER(?)";

        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            pstm.setString(1, input);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                System.out.println("Рецепт найден");

                String name = rs.getString("name_of_recipe");
                String recipeText = rs.getString("recipe");
                int recipeId = rs.getInt("id");

                System.out.println("Название: " + name);
                System.out.println("Рецепт: " + recipeText);
                System.out.println("Продукты:");

                String join = "SELECT p.name " +
                        "FROM recipe__products rp " +
                        "JOIN products p ON rp.product_id = p.id " +
                        "WHERE rp.recipe_id = ?";

                PreparedStatement pstm1 = conn.prepareStatement(join);
                pstm1.setInt(1, recipeId);

                ResultSet rs2 = pstm1.executeQuery();
                while (rs2.next()) {
                    System.out.println(rs2.getString("name"));
                }

            } else {
                System.out.println("Рецепт не найден");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void findByProduct(String input) {
        String sql2="SELECT * FROM products WHERE LOWER(name) = LOWER(?)";
        try  (
                Connection conn2 = DbConnection.getConnection();
                PreparedStatement pstm2 = conn2.prepareStatement(sql2)
        ){
            pstm2.setString(1, input);
            ResultSet rs2 = pstm2.executeQuery();

            if (rs2.next()) {
                System.out.println("Продукт найден");

                String nameOfProduct = rs2.getString("name");
                int productId = rs2.getInt("id");

                System.out.println("Название: " + nameOfProduct);

                String join2 = "SELECT r.name_of_recipe, r.recipe " +
                        "FROM recipe__products rp " +
                        "JOIN recipes r ON rp.recipe_id = r.id " +
                        "WHERE rp.product_id = ?";

                PreparedStatement pstm3 = conn2.prepareStatement(join2);
                pstm3.setInt(1, productId);

                ResultSet rs3 = pstm3.executeQuery();
                while (rs3.next()) {
                    System.out.println(rs3.getString("name_of_recipe"));
                    System.out.println(rs3.getString("recipe"));
                }

            } else {
                System.out.println("Продукт не найден");
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }
}
