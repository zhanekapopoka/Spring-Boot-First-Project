package com.example.springBootfirstapp;

import java.sql.*;

public class DataSource {
    public static void main(String[] args) {
        String sql = """
    INSERT INTO recipe__products (recipe_id, product_id)
    VALUES (?, ?)
    ON CONFLICT (recipe_id, product_id) DO NOTHING
""";;

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

// Курица с рисом
            pstm.setInt(1, 5);
            pstm.setInt(2, 46); // рис
            pstm.executeUpdate();

            pstm.setInt(1, 5);
            pstm.setInt(2, 47); // масло
            pstm.executeUpdate();


// Жареный рис с яйцом
            pstm.setInt(1, 7);
            pstm.setInt(2, 46); // рис
            pstm.executeUpdate();

            pstm.setInt(1, 7);
            pstm.setInt(2, 47); // масло
            pstm.executeUpdate();


// Гренки
            pstm.setInt(1, 8);
            pstm.setInt(2, 47); // масло
            pstm.executeUpdate();


// Каша овсянная
            pstm.setInt(1, 10);
            pstm.setInt(2, 48); // овсянка
            pstm.executeUpdate();


// Бутерброд с колбасой и сыром
            pstm.setInt(1, 13);
            pstm.setInt(2, 49); // колбаса
            pstm.executeUpdate();


// Йогурт с гранолой и фруктами
            pstm.setInt(1, 17);
            pstm.setInt(2, 51); // йогурт
            pstm.executeUpdate();

            pstm.setInt(1, 17);
            pstm.setInt(2, 52); // гранола
            pstm.executeUpdate();

            pstm.setInt(1, 17);
            pstm.setInt(2, 25); // банан
            pstm.executeUpdate();


// Лаваш с тунцом и овощами
            pstm.setInt(1, 16);
            pstm.setInt(2, 53); // лаваш
            pstm.executeUpdate();


// Творожная запеканка
            pstm.setInt(1, 19);
            pstm.setInt(2, 54); // манка
            pstm.executeUpdate();


// Рыбный суп
            pstm.setInt(1, 23);
            pstm.setInt(2, 58); // рыба
            pstm.executeUpdate();


// Макароны с сосисками
            pstm.setInt(1, 24);
            pstm.setInt(2, 50); // сосиски
            pstm.executeUpdate();
//
            System.out.println("Связи добавлены успешно");
//        String sql = "INSERT INTO products (name,translate_name,slug,alter_name) VALUES(?,?,?,?)";
//        try (Connection conn = DbConnection.getConnection();
//             PreparedStatement pstm = conn.prepareStatement(sql);) {
//           pstm.setString(1,"рис");
//           pstm.setString(2,"rice");
//           pstm.setString(3,Utils.returnSlug("рис"));
//           pstm.setString(4,"food");
//
//           pstm.executeUpdate();
//           pstm.setString(1, "молоко");
//           pstm.setString(2, "milk");
//           pstm.setString(3, Utils.returnSlug("молоко"));
//           pstm.setString(4, "dairy");
//
//           pstm.executeUpdate();
//           pstm.setString(1, "хлеб");
//           pstm.setString(2, "bread");
//           pstm.setString(3, Utils.returnSlug("хлеб"));
//           pstm.setString(4, "food");
//
//           pstm.executeUpdate();
//
//           pstm.setString(1, "курица");
//           pstm.setString(2, "chicken");
//           pstm.setString(3, Utils.returnSlug("курица"));
//           pstm.setString(4, "meat");
//
//           pstm.executeUpdate();
//           pstm.setString(1, "сыр");
//           pstm.setString(2, "cheese");
//           pstm.setString(3, Utils.returnSlug("сыр"));
//           pstm.setString(4, "dairy");
//
//           pstm.executeUpdate();
//           pstm.setString(1, "яйца");
//           pstm.setString(2, "eggs");
//           pstm.setString(3, Utils.returnSlug("яйца"));
//           pstm.setString(4, "protein");
//
//           pstm.executeUpdate();
//           pstm.setString(1, "паста");
//           pstm.setString(2, "pasta");
//           pstm.setString(3, Utils.returnSlug("паста"));
//           pstm.setString(4, "food");

//            pstm.setString(1, "соль");
//            pstm.setString(2, "salt");
//            pstm.setString(3, Utils.returnSlug("соль"));
//            pstm.setString(4, "seasoning");

//            pstm.setString(1, "масло");
//            pstm.setString(2, "oil");
//            pstm.setString(3, Utils.returnSlug("масло"));
//            pstm.setString(4, "dairy");

//            pstm.setString(1, "огурцы");
//            pstm.setString(2, "cucumber");
//            pstm.setString(3, Utils.returnSlug("огурец"));
//            pstm.setString(4, "vegetable");

//            pstm.setString(1, "помидоры");
//            pstm.setString(2, "tomatoes");
//            pstm.setString(3, Utils.returnSlug("помидор"));
//            pstm.setString(4, "vegetable");
//
//        String sql1 = "INSERT INTO recipes (name_of_recipe, translate_of_recipe, slug_of_recipe, alter_name_of_recipe, recipe) VALUES (?, ?, ?, ?, ?)";
//
//        try (Connection conn1 = DbConnection.getConnection();
//             PreparedStatement pstm1 = conn1.prepareStatement(sql1)) {
//
//            pstm1.setString(1, "Омлет");
//            pstm1.setString(2, "Omelet");
//            pstm1.setString(3, Utils.returnSlug("Омлет"));
//            pstm1.setString(4, "food");
//            pstm1.setString(5, "Взбей яйца с молоком и солью, жарь 3–5 минут.");
//
//            pstm1.setString(1, "Паста с сыром");
//            pstm1.setString(2, "Pasta with cheese");
//            pstm1.setString(3, Utils.returnSlug("Паста с сыром"));
//            pstm1.setString(4, "food");
//            pstm1.setString(5, "Отвари пасту, добавь сыр, соль и немного масла.");
//            pstm1.executeUpdate();
//            pstm1.setString(1, "Яичница");
//            pstm1.setString(2, "Fried eggs");
//            pstm1.setString(3, Utils.returnSlug("Яичница"));
//            pstm1.setString(4, "food");
//            pstm1.setString(5, "Обжарь яйца на масле и добавь соль.");
//            pstm1.executeUpdate();
//            pstm1.setString(1, "Курица с рисом");
//            pstm1.setString(2, "Chicken with rice");
//            pstm1.setString(3, Utils.returnSlug("Курица с рисом"));
//            pstm1.setString(4, "food");
//            pstm1.setString(5, "Отвари рис. Обжарь курицу с маслом и солью, затем подай с рисом.");
//            pstm1.executeUpdate();
//            pstm1.setString(1, "Салат из огурцов и помидоров");
//            pstm1.setString(2, "Cucumber and tomato salad");
//            pstm1.setString(3, Utils.returnSlug("Салат из огурцов и помидоров"));
//            pstm1.setString(4, "salad");
//            pstm1.setString(5, "Нарежь огурцы и помидоры, добавь соль и масло, перемешай.");
//            pstm1.executeUpdate();
//
//            pstm1.setString(1, "Салат из огурцов и помидоров");
//            pstm1.setString(2, "Cucumber and tomato salad");
//            pstm1.setString(3, Utils.returnSlug("Салат из огурцов и помидоров"));
//            pstm1.setString(4, "salad");
//            pstm1.setString(5, "Нарежь огурцы и помидоры, добавь соль и масло, перемешай.");
//
//            pstm1.executeUpdate();

//            pstm1.setString(1, "Жареный рис с яйцом");
//            pstm1.setString(2, "Fried rice with egg");
//            pstm1.setString(3, Utils.returnSlug("Жареный рис с яйцом"));
//            pstm1.setString(4, "rice with egg");
//            pstm1.setString(5, "Обжарь готовый рис с яйцом на масле, добавь соль.");
//            pstm1.executeUpdate();
//
//            pstm1.setString(1, "Гренки");
//            pstm1.setString(2, "Fried bread in milk and egg");
//            pstm1.setString(3, Utils.returnSlug("Гренки"));
//            pstm1.setString(4, "fried bread");
//            pstm1.setString(5, "Обмакни хлеб в яйце с молоком и обжарь на масле.");
//            pstm1.executeUpdate();



            System.out.println("Добавлено успешно");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}