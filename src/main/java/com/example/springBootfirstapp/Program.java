package com.example.springBootfirstapp;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        RecipeService recepiservice=new RecipeService();
        Scanner sc = new Scanner(System.in);
        System.out.println("Что вы хотите найти?");
        System.out.println("1 - рецепт");
        System.out.println("2 - продукт");
        if (sc.hasNextInt()) {
            int number = sc.nextInt();
            sc.nextLine();
            if (number == 1) {
                System.out.println("Введите рецепт:");
                String input = sc.nextLine();
                recepiservice.findRecipeByName(input);
            } else if (number == 2) {
                System.out.println("Введите продукт:");
                String input = sc.nextLine();
                recepiservice.findByProduct(input);
            } else {
                System.out.println("Такого варианта нет.");
            }
            sc.close();
        }
    }
}
