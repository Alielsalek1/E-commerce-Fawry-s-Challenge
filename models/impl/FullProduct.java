package models.impl;

import Abstractions.Product;
import Abstractions.Expirable;
import Abstractions.Shippable;
import java.time.LocalDate;

// Shippable and Expirable products
public class FullProduct extends Product implements Expirable, Shippable {
    private LocalDate expirationDate;
    private double weight;

    public FullProduct(String name, double price, int quantity, LocalDate expirationDate, double weight) {
        super(name, price, quantity);
        this.expirationDate = expirationDate;
        this.weight = weight;
    }

    @Override
    public boolean isExpired(LocalDate date) {
        return expirationDate != null && date.isAfter(expirationDate);
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
