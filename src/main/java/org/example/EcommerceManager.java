package org.example;

import java.time.LocalDateTime;
import java.util.*;

public class EcommerceManager {

    private Map<String, String> users = new HashMap<>();
    private Map<String, String> emails = new HashMap<>();
    private List<Product> products = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private List<String> logs = new ArrayList<>();

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

    private void sendConfirmationEmail(String username, Order order) {
        String email = emails.get(username);
        if (email != null) {
            System.out.println("Sending confirmation email to " + email + " for order " + order.getId());
            log("Confirmation email sent to " + email);
        }
    }

    public void processPayment(String username, double amount, String cardNumber) {
        if (!users.containsKey(username)) {
            log("Payment attempt by unknown user: " + username);
            return;
        }
        System.out.println("Payment of €" + amount + " processed for " + username);
        log("Payment successful: " + amount + "€ for " + username);
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

    public static void main(String[] args) {
        EcommerceManager app = new EcommerceManager();
        app.registerUser("alice", "1234", "alice@example.com");

        app.addProduct(new Product("P001", "Laptop", 1200.00));
        app.addProduct(new Product("P002", "Mouse", 25.99));

        app.login("alice", "1234");

        app.createOrder("alice", Arrays.asList("P001", "P002"));
        app.processPayment("alice", 1225.99, "4111111111111111");

        app.printStats();
        app.showLogs();
    }
}
