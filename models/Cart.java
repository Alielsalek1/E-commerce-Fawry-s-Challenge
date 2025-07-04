package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Abstractions.Product;
import Abstractions.Expirable;
import Abstractions.Shippable;
import services.ShippingService;

public class Cart {
    private Customer customer;
    private List<CartItem> items = new ArrayList<>();

    public Cart(Customer customer) {
        this.customer = customer;
    }

    public void addProduct(Product product, int quantity) {
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Quantity exceeds available stock");
        }
        product.reduceQuantity(quantity);
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return items.stream()
                .mapToDouble(CartItem::getSubtotal)
                .sum();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
