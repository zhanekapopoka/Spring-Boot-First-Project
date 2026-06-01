package com.example.springBootfirstapp;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class ProductService {
   public String deleteByProduct(int id){
       String sql= "DELETE FROM products WHERE id =?";
       try(Connection connection = DbConnection.getConnection();
       PreparedStatement preparedStatement = connection.prepareStatement(sql)){
           preparedStatement.setInt(1,id);
           preparedStatement.executeUpdate();
       }catch (SQLException exception){
           exception.printStackTrace();
       }
       return "Product deleted";
   }
   public String updateProduct(int id, Product product){
       String sql = "UPDATE products\n" +
               "SET name = ?, translate_name = ?, slug = ?, alter_name = ?\n" +
               "WHERE id = ?";
       try (Connection conn = DbConnection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
           String name = product.getName();
           String altername = product.getAlterName();
           String slug = Utils.returnSlug(name);
           String translate = product.getTranslate();
           pstm.setString(1, name);
           pstm.setString(2, translate);
           pstm.setString(3, slug);
           pstm.setString(4, altername);
           pstm.setInt(5,id);
           pstm.executeUpdate();
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return "Product updated";
   }

    public String postProduct(Product product) {
        String sql = "INSERT INTO products (name,translate_name,slug,alter_name) VALUES(?,?,?,?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            String name = product.getName();
            String altername = product.getAlterName();
            String slug = Utils.returnSlug(name);
            String translate = product.getTranslate();
            pstm.setString(1, name);
            pstm.setString(2, translate);
            pstm.setString(3, slug);
            pstm.setString(4, altername);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Product created";
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        String sql2 = "SELECT * FROM products";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql2)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String translate = rs.getString("translate_name");
                String alterName = rs.getString("alter_name");
                Product product = new Product(id, name, translate, alterName, new ArrayList<>());
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
