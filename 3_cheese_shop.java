/* Develop a program "Cheese shop" that contains multiple classes:

1. Cheese 

2. CheeseShop - gives access for the shop owner to add/remove different cheeses, gives access for the customer to buy different cheeses,
  remove cheese from cart, should be possible to get all the cheeses from the cart or available cheeses in the store

3. Main - User UI, accesses CheeseShop to buy or to add different cheeses
*/


Cheese.java

  public class Cheese {
    private int id;
    private String name;
    private double cost;

    public Cheese(int id, String name, double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Cost: " + cost + "€";
    }

CheeseShop.java

import java.util.ArrayList;
import java.util.List;

public class CheeseShop {
    private List<Cheese> availableCheeses = new ArrayList<>();
    private List<Cheese> cart = new ArrayList<>();

    public void addCheese(Cheese cheese) {
        availableCheeses.add(cheese);
    }

    public void removeCheese(int id) {
        availableCheeses.removeIf(cheese -> cheese.getId() == id);
    }

    public void addToCart(int id) {
        for (Cheese cheese : availableCheeses) {
            if (cheese.getId() == id) {
                cart.add(cheese);
                return;
            }
        }
        System.out.println("Cheese not found in the store");
    }

    public void removeFromCart(int id) {
        cart.removeIf(cheese -> cheese.getId() == id);
    }

    public List<Cheese> getAvailableCheeses() {
        return availableCheeses;
    }

    public List<Cheese> getCart() {
        return cart;
    }
}

Main.java

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static CheeseShop cheeseShop = new CheeseShop();

    public static void main(String[] args) {
        while (true) {
            System.out.println("This is Cheese Shop. What would you like to do:");
            System.out.println("1. Add cheese to the shop");
            System.out.println("2. Remove cheese from the shop");
            System.out.println("3. Add cheese to the cart");
            System.out.println("4. Remove cheese from the cart");
            System.out.println("5. View available cheeses");
            System.out.println("6. View cheeses in the cart");
            System.out.println("x. Exit");
            System.out.print("Choose an option 1-6 or x: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addCheese();
                    break;
                case "2":
                    removeCheese();
                    break;
                case "3":
                    addToCart();
                    break;
                case "4":
                    removeFromCart();
                    break;
                case "5":
                    viewAvailableCheeses();
                    break;
                case "6":
                    viewCart();
                    break;
                case "x":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid entry, please try again");
            }
        }
    }

    private static void addCheese() {
        System.out.print("Enter cheese ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
      
        System.out.print("Enter cheese name: ");
        String name = scanner.nextLine();
      
        System.out.print("Enter cheese cost: ");
        double cost = scanner.nextDouble();
        scanner.nextLine();
      
        Cheese cheese = new Cheese(id, name, cost);
        cheeseShop.addCheese(cheese);
        System.out.println("Cheese added to the shop");
    }

    private static void removeCheese() {
        System.out.print("Enter cheese ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        cheeseShop.removeCheese(id);
        System.out.println("Cheese removed from the shop");
    }

    private static void addToCart() {
        System.out.print("Enter cheese ID to add to the cart: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        cheeseShop.addToCart(id);
    }

    private static void removeFromCart() {
        System.out.print("Enter cheese ID to remove from cart: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        cheeseShop.removeFromCart(id);
    }

    private static void viewAvailableCheeses() {
        System.out.println("Available Cheeses are:");
        for (Cheese cheese : cheeseShop.getAvailableCheeses()) {
            System.out.println(cheese);
        }
    }

    private static void viewCart() {
        System.out.println("Cheeses in the Cart are:");
        for (Cheese cheese : cheeseShop.getCart()) {
            System.out.println(cheese);
        }
    }
}

