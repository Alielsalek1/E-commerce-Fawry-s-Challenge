package models.impl;

import Abstractions.Product;

// non Shippable or Expirable
public class BasicProduct extends Product {
    public BasicProduct(String name, double price, int quantity) {
        super(name, price, quantity);
    }
}
