package org.example;

import java.time.LocalDateTime;
import java.util.*;

public class EcommerceManager {

    // 40+ fields (mostly dummy)
    private Map<String, String> users = new HashMap<>();
    private Map<String, String> emails = new HashMap<>();
    private List<String> logs = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private int a1, a2, a3, a4, a5, a6, a7, a8, a9, a10;
    private int b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;
    private int c1, c2, c3, c4, c5, c6, c7, c8, c9, c10;
    private int d1, d2, d3, d4, d5;

    // User Management
    public void registerUser(String username, String password, String email) {
        users.put(username, password);
        emails.put(username, email);
        log("Registered " + username);
    }

    public boolean login(String username, String password) {
        boolean success = users.containsKey(username) && users.get(username).equals(password);
        log("Login attempt for " + username + ": " + (success ? "success" : "fail"));
        return success;
    }

    // Product Management
    public void addProduct(Product product) {
        products.add(product);
        log("Added product " + product.getName());
    }

    public Product findProductById(String id) {
        for (Product p : products) {
            if (p.getId().equals(id)) return p;
        }
        return null;
    }

    // Order Management
    public void createOrder(String username, List<String> productIds) {
        List<Product> prods = new ArrayList<>();
        for (String id : productIds) {
            Product p = findProductById(id);
            if (p != null) prods.add(p);
        }
        Order order = new Order(username, prods);
        orders.add(order);
        log("Order created for " + username + " #" + order.getId());
        log(username + "Your order #" + order.getId() + " has been placed.");
    }

    // Payment Processing with crazy nested logic
    public void processPayment(String username, double amount, String cardNumber) {
        if (!users.containsKey(username)) {
            log("Unknown user " + username + " payment attempt");
            return;
        }

        if (amount <= 0) {
            log("Invalid payment amount: " + amount);
            return;
        }

        if (cardNumber == null || cardNumber.length() < 12) {
            log("Invalid card number");
            return;
        }

        // Nested condition hell
        if (amount > 1000) {
            if (cardNumber.startsWith("4")) {
                if (cardNumber.endsWith("1")) {
                    try {
                        if (amount % 2 == 0) {
                            if (amount < 5000) {
                                log("High value payment processed");
                                nestedDeepChecks(amount, cardNumber);
                            } else {
                                log("Payment too high");
                            }
                        } else {
                            log("Amount not even");
                        }
                    } catch (Exception e) {
                        log("Payment exception: " + e.getMessage());
                    }
                }
            }
        } else {
            log("Regular payment processed");
        }
    }

    private void nestedDeepChecks(double amount, String cardNumber) {
        for (int i = 0; i < 5; i++) {
            if (amount > 1000 && i % 2 == 0) {
                int j = 0;
                while (j < 3) {
                    if (cardNumber.contains("" + i)) {
                        for (int k = 0; k < 2; k++) {
                            if (k + j == i) {
                                log("Nested check passed at " + i + "," + j + "," + k);
                            } else if (k == 1) {
                                log("Nested check alternative at " + i + "," + j + "," + k);
                            }
                        }
                    }
                    j++;
                }
            }
        }
    }

    // Long method with switch & nested ifs
    public void processCommand(String cmd) {
        switch (cmd.toLowerCase()) {
            case "start":
                log("Starting system...");
                if (users.size() > 0) {
                    if (orders.size() > 10) {
                        log("Lots of orders");
                    } else {
                        log("Few orders");
                    }
                } else {
                    log("No users yet");
                }
                break;
            case "stop":
                log("Stopping system...");
                break;
            case "restart":
                log("Restarting system...");
                processCommand("stop");
                processCommand("start");
                break;
            case "status":
                log("Status: Users=" + users.size() + ", Orders=" + orders.size());
                break;
            default:
                log("Unknown command: " + cmd);
        }
    }

    // Logging
    private void log(String message) {
        logs.add(LocalDateTime.now() + ": " + message);
    }

    public void showLogs() {
        for (String log : logs) System.out.println(log);
    }

    // Tons of dummy methods to explode method count (total > 50)
    public void method1() {} public void method2() {} public void method3() {}
    public void method4() {} public void method5() {} public void method6() {}
    public void method7() {} public void method8() {} public void method9() {}
    public void method10() {} public void method11() {} public void method12() {}
    public void method13() {} public void method14() {} public void method15() {}
    public void method16() {} public void method17() {} public void method18() {}
    public void method19() {} public void method20() {} public void method21() {}
    public void method22() {} public void method23() {} public void method24() {}
    public void method25() {} public void method26() {} public void method27() {}
    public void method28() {} public void method29() {} public void method30() {}
    public void method31() {} public void method32() {} public void method33() {}
    public void method34() {} public void method35() {} public void method36() {}
    public void method37() {} public void method38() {} public void method39() {}
    public void method40() {} public void method41() {} public void method42() {}
    public void method43() {} public void method44() {} public void method45() {}
    public void method46() {} public void method47() {} public void method48() {}
    public void method49() {} public void method50() {}

    // Dummy inner classes for completeness
    public static class Product {
        private String id;
        private String name;

        public Product(String id, String name) { this.id = id; this.name = name; }
        public String getId() { return id; }
        public String getName() { return name; }
    }

    public static class Order {
        private static int counter = 0;
        private final String id;
        private final String username;
        private final List<Product> products;

        public Order(String username, List<Product> products) {
            this.username = username;
            this.products = products;
            this.id = String.valueOf(++counter);
        }
        public String getId() { return id; }
        public String getUsername() { return username; }
        public List<Product> getProducts() { return products; }
    }
}
