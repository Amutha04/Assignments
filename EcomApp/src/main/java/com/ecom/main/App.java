package com.ecom.main;

import com.ecom.config.AppConfig;
import com.ecom.dto.ProductDto;
import com.ecom.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductService productService = context.getBean(ProductService.class);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("---PRODUCT INVENTORY APP---");
            System.out.println("1. Add Product");
            System.out.println("2. Find Product by ID");
            System.out.println("3. Update Stock Quantity");
            System.out.println("4. Count Products by Vendor");
            System.out.println("0. to Exit");

            int input = sc.nextInt();
            if (input == 0) {
                System.out.println("Exiting...");
                break;
            }
            switch (input) {
                case 1 -> {
                    System.out.println("Add Product");
                    sc.nextLine();

                    System.out.println("Enter product name");
                    String name = sc.nextLine();

                    System.out.println("Enter price");
                    double price = sc.nextDouble();

                    System.out.println("Enter stock quantity");
                    int stockQuantity = sc.nextInt();

                    System.out.println("Enter category id");
                    int categoryId = sc.nextInt();

                    System.out.println("Enter vendor id");
                    int vendorId = sc.nextInt();

                    try {
                        productService.insertProduct(name, price, stockQuantity, categoryId, vendorId);
                        System.out.println("Product Added Successfully");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                }

                case 2 -> {
                    System.out.println("Find Product by ID");
                    System.out.println("Enter product id");
                    int id = sc.nextInt();
                    try {
                        ProductDto productDto = productService.findById(id);
                        System.out.println(productDto);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 3 -> {
                    System.out.println("Update Stock Quantity");
                    System.out.println("Enter product id");
                    int productId = sc.nextInt();
                    System.out.println("Enter new quantity");
                    int newQuantity = sc.nextInt();
                    try {
                        productService.updateStock(productId, newQuantity);
                        System.out.println("Stock Updated Successfully");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 4 -> {
                    try {
                        Map<String, Integer> result = productService.countProductsByVendor();
                        System.out.println("Products Count by Vendor");
                        result.forEach((v, c) ->
                                System.out.println(v + " - " + c));
                    } catch (Exception e) {
                        System.out.println("No Products available");
                    }
                }

                default -> System.out.println("Invalid option, try again");
            }


        }
    }
}
