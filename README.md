# e-commerce System

This project is a simple Java e-commerce system.

## What I Did

- I used the **Strategy Design Pattern**.
- I made two services:
  - `CheckoutService` – handles buying products.
  - `ShippingService` – handles shipping products.

- I made an **abstract class** `Product` to help reuse common product logic.

## Product Types

There are 4 product types:

1. **ExpirableProduct** – has an expiration date.
2. **ShippableProduct** – has weight and can be shipped.
3. **FullProduct** – both expirable and shippable.
4. **BasicProduct** – not expirable and not shippable.

## Tests Included

- Happy case (buy and checkout works)
- Product quantity is too low
- Customer has not enough money
- Product is already expired

## Running the Task

- In `Main.java`, you can call different test cases like:
   ```java
   happyCase();
   insufficientQuantityCase();
   insufficientBalanceCase();
   expiredProductCase();
   ```

## Screenshots of the application running

![Screenshot 1](Screenshots/Screenshot%202025-07-08%20225058.png)

![Screenshot 2](Screenshots/Screenshot%202025-07-08%20225111.png)

![Screenshot 3](Screenshots/Screenshot%202025-07-08%20225126.png)

![Screenshot 4](Screenshots/Screenshot%202025-07-08%20225139.png)

