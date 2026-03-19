package com.esempio.streams;

import com.esempio.streams.model.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;

public class StreamExercises2 {

    public static void main(String[] args) {


        Product javaBook     = new Product(1L, "Java Book",     "Books", 150.0);
        Product cheapBook    = new Product(2L, "Cheap Book",    "Books",  50.0);
        Product babyToy      = new Product(3L, "Baby Toy",      "Baby",   30.0);
        Product boysTShirt   = new Product(4L, "Boys T-Shirt",  "Boys",   40.0);
        Product boysSneakers = new Product(5L, "Boys Sneakers", "Boys",   80.0);

        Customer alice = new Customer(1L, "Alice", 2);
        Customer bob   = new Customer(2L, "Bob",   1);

        Order order1 = new Order(1L, "DELIVERED",
                LocalDate.of(2021, 2, 15), LocalDate.of(2021, 3, 1),
                List.of(javaBook, babyToy), alice);       // totale: 180.0

        Order order2 = new Order(2L, "PENDING",
                LocalDate.of(2021, 3, 10), LocalDate.of(2021, 4, 5),
                List.of(boysTShirt), alice);              // totale: 40.0

        Order order3 = new Order(3L, "DELIVERED",
                LocalDate.of(2021, 3, 1), LocalDate.of(2021, 3, 20),
                List.of(cheapBook, boysSneakers), bob);   // totale: 130.0

        List<Product> products = List.of(javaBook, cheapBook, babyToy, boysTShirt, boysSneakers);
        List<Order>   orders   = List.of(order1, order2, order3);

        // Es1
        System.out.println("=== Ordini raggruppati per cliente ===");

        Map<Customer, List<Order>> ex1 = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer));

        ex1.forEach((customer, orderList) -> {
            System.out.println("  " + customer.getName() + ":");
            orderList.forEach(o -> System.out.println("    Ordine #" + o.getId()));
        });

        // Es2
        System.out.println("\n=== Totale acquisti per cliente ===");

        Map<Customer, Double> ex2 = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomer,
                        Collectors.summingDouble(Order::calculateTotal)
                ));

        ex2.forEach((customer, total) ->
                System.out.println("  " + customer.getName() + " → €" + total));

        // Es3
        System.out.println("\n=== Prodotto più costoso ===");

        products.stream()
                .max(Comparator.comparingDouble(Product::getPrice))
                .ifPresent(p -> System.out.println("  " + p.getName() + " — €" + p.getPrice()));

        // Es4
        System.out.println("\n=== Media importi ordini ===");

        double ex4 = orders.stream()
                .collect(Collectors.averagingDouble(Order::calculateTotal));

        System.out.println("  Media: €" + ex4);

        System.out.println("\n=== Somma importi per categoria ===");

        Map<String, Double> ex5 = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,                        // chiave: categoria
                        Collectors.summingDouble(Product::getPrice)  // valore: somma prezzi
                ));

        ex5.forEach((category, total) ->
                System.out.println("  " + category + " → €" + total));
} }