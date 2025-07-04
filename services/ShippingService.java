package services;

import Abstractions.Shippable;
import java.util.List;
import Abstractions.Product;
import models.CartItem;

public class ShippingService {
    // print shipment receipt
    public static void ship(List<CartItem> items) {
        System.out.println("** Shipment notice **");
        double totalWeight = 0;

        for (CartItem item : items) {
            Product product = item.getProduct();

            if (!(product instanceof Shippable shippable)) {
                throw new IllegalArgumentException("Product is not shippable: " + product.getName());
            }

            // shipping Receipt
            int quantity = item.getQuantity();
            double weightPerUnit = shippable.getWeight();
            double weightTotal = weightPerUnit * quantity;
            totalWeight += weightTotal;

            double weightInGrams = (int) (weightPerUnit * 1000);
            String label = quantity + "x " + product.getName();
            System.out.printf("%-14s %4.0fg%n", label, weightInGrams);
        }

        System.out.printf("Total package weight %.1fkg%n\n", totalWeight);
    }
}
