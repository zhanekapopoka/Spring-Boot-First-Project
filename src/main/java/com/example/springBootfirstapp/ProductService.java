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

    public PaginatedProductResponse getAllProducts(Integer page) {
        int limit = 5;
        int currentPage;
        if (page == null || page < 1) {
            currentPage = 1;
        } else {
            currentPage = page;
        }
        int offset = (currentPage - 1) * limit;
        ArrayList<Product> products = getProductsByLimitAndOffset(limit, offset);
        int totalItems = countProducts();
        int numberOfPages = (int) Math.ceil(totalItems / (double) limit);
        return new PaginatedProductResponse(products,products.size(), currentPage,numberOfPages);
    }

    private ArrayList<Product> getProductsByLimitAndOffset(int limit, int offset) {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY id LIMIT ? OFFSET ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, limit);
            pstm.setInt(2, offset);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Product product = mapProduct(rs);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    private int countProducts() {
        String sql = "SELECT COUNT(*) FROM products";
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
    private Product mapProduct(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String translate = rs.getString("translate_name");
        String alterName = rs.getString("alter_name");
        return new Product(id, name, translate, alterName, new ArrayList<>());
    }
}
