package services;

import Abstractions.Expirable;
import Abstractions.Shippable;
import models.Cart;
import models.CartItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CheckoutService {

    public static void checkout(Cart cart) {
        List<CartItem> items = new ArrayList<>(cart.getItems());

        if (items.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        LocalDate now = LocalDate.now();

        validateExpirables(items, now);

        List<CartItem> shippableItems = getShippableItems(items);

        double subtotal = cart.getTotalPrice();
        double shippingFee = shippableItems.isEmpty() ? 0 : 30;
        double total = subtotal + shippingFee;

        validateBalance(cart, total);
        cart.getCustomer().deduct(total);

        if (!shippableItems.isEmpty()) {
            ShippingService.ship(shippableItems);
        }

        printReceipt(items, subtotal, shippingFee, total);
    }

    // Validate expired products
    private static void validateExpirables(List<CartItem> items, LocalDate now) {
        for (CartItem item : items) {
            if (item.getProduct() instanceof Expirable expirable) {
                if (expirable.isExpired(now)) {
                    throw new IllegalStateException(item.getProduct().getName() + " is expired");
                }
            }
        }
    }

    // Collect shippable items
    private static List<CartItem> getShippableItems(List<CartItem> items) {
        List<CartItem> shippables = new ArrayList<>();
        for (CartItem item : items) {
            if (item.getProduct() instanceof Shippable) {
                shippables.add(item);
            }
        }
        return shippables;
    }

    // Check customer balance
    private static void validateBalance(Cart cart, double total) {
        if (cart.getCustomer().getBalance() < total) {
            throw new IllegalStateException("Insufficient balance");
        }
    }

    // Print checkout receipt
    private static void printReceipt(List<CartItem> items, double subtotal, double shipping, double total) {
        System.out.println("** Checkout receipt **");
        for (CartItem item : items) {
            String label = item.getQuantity() + "x " + item.getProduct().getName();
            System.out.printf("%-15s %.0f%n", label, item.getSubtotal());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f%n", subtotal);
        System.out.printf("Shipping %.0f%n", shipping);
        System.out.printf("Amount %.0f%n\n", total);
    }
}
