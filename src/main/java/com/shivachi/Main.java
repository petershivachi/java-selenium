package com.shivachi;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Process started...");
        Selenium selenium = new Selenium();
        System.out.println("Data Intake...");
        selenium.dataIn();
        System.out.println("Data intake Done...");
        System.out.println("Google Account Creation...");
        selenium.googleAccountCreation();
        System.out.println("Google Account Creation Done...");
        System.out.println("Jumia Account Creation...");
        selenium.jumiaAccountCreation();
        System.out.println("Jumia Account Creation Done...");
        System.out.println("Search Product and Add To Cart...");
        selenium.searchProductAndAddToCart();
        System.out.println("Search Product and Add To Cart Done...");
        System.out.println("Send Report...");
        selenium.sendReport();
        System.out.println("Send Report Done...");
    }
}