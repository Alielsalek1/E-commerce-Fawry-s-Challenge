import models.Cart;
import models.Customer;
import services.CheckoutService;
import services.ShippingService;
import java.time.LocalDate;
import models.impl.FullProduct;
import models.impl.ShippableProduct;
import models.impl.BasicProduct;

public class Main {
    public static void main(String[] args) {
    // 4 tests that include common errors and the happy case
//        happyCase();
        insufficientQuantityCase();
//        insufficientBalanceCase();
//        expiredProductCase();

    }

    public static void happyCase() {
        Customer customer = new Customer("Ali", 1000);
        Cart cart = new Cart(customer);

        FullProduct cheese = new FullProduct("Cheese", 100, 3, LocalDate.of(2025,7,10), 0.2);
        FullProduct biscuit = new FullProduct("Biscuits", 150, 3, LocalDate.of(2025,8,1), 0.7);
        BasicProduct apple = new BasicProduct("Apple", 1, 3);

        cart.addProduct(cheese, 2);
        cart.addProduct(biscuit, 1);
        cart.addProduct(apple, 1);

        try {
            CheckoutService.checkout(cart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void insufficientQuantityCase() {
        Customer customer = new Customer("Ali", 500);
        Cart cart = new Cart(customer);
        ShippableProduct tv = new ShippableProduct("TV", 300, 1, 10.0);


        try {
            cart.addProduct(tv, 5); // only 1 available
            CheckoutService.checkout(cart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void insufficientBalanceCase() {
        Customer customer = new Customer("Ali", 200);
        Cart cart = new Cart(customer);
        FullProduct biscuit = new FullProduct("Biscuits", 150, 10, LocalDate.of(2025, 8, 1), 0.7);

        cart.addProduct(biscuit, 2); // total = 300 > balance

        try {
            CheckoutService.checkout(cart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void expiredProductCase() {
        Customer customer = new Customer("Ali", 500);
        Cart cart = new Cart(customer);
        FullProduct expiredCheese = new FullProduct("Cheese", 100, 10, LocalDate.of(2024, 1, 1), 0.2);

        cart.addProduct(expiredCheese, 1); // expired

        try {
            CheckoutService.checkout(cart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
