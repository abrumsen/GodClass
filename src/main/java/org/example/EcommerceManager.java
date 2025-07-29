package org.example;

import java.time.LocalDateTime;
import java.util.*;

public class EcommerceManager {

    // Too many fields
    private Map<String, String> users = new HashMap<>();
    private Map<String, String> emails = new HashMap<>();
    private List<Product> products = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private List<String> logs = new ArrayList<>();
    private int a1, a2, a3, a4, a5, a6, a7, a8, a9, a10;
    private int b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;

    public void registerUser(String username, String password, String email) {
        users.put(username, password);
        emails.put(username, email);
        log("User registered: " + username);
    }

    public boolean login(String username, String password) {
        boolean success = users.containsKey(username) && users.get(username).equals(password);
        log("Login attempt for " + username + ": " + (success ? "success" : "failure"));
        return success;
    }

    public void addProduct(Product product) {
        products.add(product);
        log("Product added: " + product.getName());
    }

    public Product findProductById(String id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    public void createOrder(String username, List<String> productIds) {
        List<Product> orderedProducts = new ArrayList<>();
        for (String id : productIds) {
            Product p = findProductById(id);
            if (p != null) orderedProducts.add(p);
        }
        Order order = new Order(username, orderedProducts);
        orders.add(order);
        log("Order created for " + username + ": " + order.getId());
        sendConfirmationEmail(username, order);
    }

    public void processPayment(String username, double amount, String cardNumber) {
        if (!users.containsKey(username)) {
            log("Payment attempt by unknown user: " + username);
            return;
        }
        System.out.println("Processing payment...");
        if (amount > 1000) {
            if (cardNumber.startsWith("4")) {
                if (cardNumber.endsWith("1")) {
                    try {
                        if (amount % 2 == 0) {
                            log("Payment passed all nested checks.");
                        }
                    } catch (Exception e) {
                        log("Payment check failed");
                    }
                }
            }
        }
        System.out.println("Payment of €" + amount + " processed for " + username);
        log("Payment successful: " + amount + "€ for " + username);
    }

    private void sendConfirmationEmail(String username, Order order) {
        String email = emails.get(username);
        if (email != null) {
            System.out.println("Sending confirmation email to " + email + " for order " + order.getId());
            log("Confirmation email sent to " + email);
        }
    }

    public void printStats() {
        System.out.println("Users: " + users.size());
        System.out.println("Products: " + products.size());
        System.out.println("Orders: " + orders.size());
    }

    private void log(String message) {
        logs.add(LocalDateTime.now() + " - " + message);
    }

    public void showLogs() {
        logs.forEach(System.out::println);
    }

    // Add filler methods to exceed threshold
    public void method1() {}
    public void method2() {}
    public void method3() {}
    public void method4() {}
    public void method5() {}
    public void method6() {}
    public void method7() {}
    public void method8() {}
    public void method9() {}
    public void method10() {}
    public void method11() {}
    public void method12() {}

    public void deeplyNestedLogic() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                while (i < 10) {
                    try {
                        if (i > 5) {
                            for (int j = 0; j < 3; j++) {
                                if ((j + i) % 2 == 0) {
                                    System.out.println("Nested " + i + ", " + j);
                                }
                            }
                        }
                        i++;
                    } catch (Exception e) {
                        log("Error in deep logic");
                    }
                }
            }
        }
    }
}
