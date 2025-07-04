package models.impl;

import Abstractions.Product;
import Abstractions.Expirable;
import java.time.LocalDate;

public class ExpirableProduct extends Product implements Expirable {
    private LocalDate expirationDate;

    public ExpirableProduct(String name, double price, int quantity, LocalDate expirationDate) {
        super(name, price, quantity);
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean isExpired(LocalDate date) {
        return expirationDate != null && date.isAfter(expirationDate);
    }
}
