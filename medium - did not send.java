/*   

MEDIUM 

Develop a program "Cheese shop" that contains multiple classes:

CheeseShop - gives access for the shop owner to add/remove different cheeses, gives access for the customer to buy different cheeses, remove cheese from cart, should be possible to get all the cheeses from the cart or available cheeses in the store;

Main - User UI, accesses CheeseService and CheeseShop to buy or to add different cheeses;

CheeseService - gives access for the shop owner to add different cheeses (should remove this functionality from CheeseShop);

Customer - contains money the customer has, contains the items the customer owns. Whenever the customer buys something, money is reduced. If customer doesn't have any money left, then notify the user about it

***
  
Cheese class:manages the details of each cheese item (ID, name, cost);

CheeseService class: manages the inventory of cheeses in the shop;

CheeseShop class: manages the customer's cart;

Customer class: manages the customer's money and owned cheeses;

Main class: provides a user interface to interact with the shop, including adding/removing cheeses from the shop, adding/removing cheeses from the cart, and viewing available cheeses, cart contents, and customer information

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

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Cost: €" + cost;
    }
}

CheeseService.java

import java.util.ArrayList;
import java.util.List;

public class CheeseService {
    private List<Cheese> availableCheeses = new ArrayList<>();

    public void addCheese(Cheese cheese) {
        availableCheeses.add(cheese);
        System.out.println(cheese.getName() + " added to the shop");
    }

    public void removeCheese(int id) {
        availableCheeses.removeIf(cheese -> cheese.getId() == id);
        System.out.println("Cheese removed from the shop");
    }

    public List<Cheese> getAvailableCheeses() {
        return availableCheeses;
    }
}

CheeseShop.java

import java.util.ArrayList;
import java.util.List;

public class CheeseShop {
    private List<Cheese> cart = new ArrayList<>();

    public void addToCart(Cheese cheese, Customer customer) {
        if (customer.getMoney() >= cheese.getCost()) {
            cart.add(cheese);
            customer.setMoney(customer.getMoney() - cheese.getCost());
            customer.addCheese(cheese);
            System.out.println(cheese.getName() + " added to cart");
        } else {
            System.out.println("Not enough money to buy " + cheese.getName());
        }
    }

    public void removeFromCart(int id, Customer customer) {
        for (Cheese cheese : cart) {
            if (cheese.getId() == id) {
                cart.remove(cheese);
                customer.setMoney(customer.getMoney() + cheese.getCost());
                customer.removeCheese(cheese);
                System.out.println(cheese.getName() + " removed from cart");
                return;
            }
        }
        System.out.println("Cheese not found in cart");
    }

    public List<Cheese> getCart() {
        return cart;
    }
}


Customer.java
  
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private double money;
    private List<Cheese> boughtCheeses = new ArrayList<>();

    public Customer(String name, double money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public List<Cheese> getBoughtCheeses() {
        return boughtCheeses;
    }

    public void addCheese(Cheese cheese) {
        boughtCheeses.add(cheese);
    }

    public void removeCheese(Cheese cheese) {
        boughtCheeses.remove(cheese);
    }
}

Main.java

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static CheeseService cheeseService = new CheeseService();
    private static CheeseShop cheeseShop = new CheeseShop();
    private static Customer customer = new Customer("Mari Maasikas", 50.00);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Cheese Shop Menu:");
            System.out.println("1. Add cheese to shop");
            System.out.println("2. Remove cheese from shop");
            System.out.println("3. Add cheese to cart");
            System.out.println("4. Remove cheese from cart");
            System.out.println("5. View available cheeses");
            System.out.println("6. View cheeses in cart");
            System.out.println("7. View customer balance and bought cheeses");
            System.out.println("x. Exit");
            System.out.print("Choose an option 1-7 or x: ");
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
                case "7":
                    viewCustomerInfo();
                    break;
                case "x":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again");
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
        cheeseService.addCheese(cheese);
    }

    private static void removeCheese() {
        System.out.print("Enter cheese ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        cheeseService.removeCheese(id);
    }

    private static void addToCart() {
        System.out.print("Enter cheese ID to add to cart: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (Cheese cheese : cheeseService.getAvailableCheeses()) {
            if (cheese.getId() == id) {
                cheeseShop.addToCart(cheese, customer);
                return;
            }
        }
        System.out.println("Cheese not found");
    }

    private static void removeFromCart() {
        System.out.print("Enter cheese ID to remove from cart: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        cheeseShop.removeFromCart(id, customer);
    }

    private static void viewAvailableCheeses() {
        System.out.println("Available Cheeses:");
        for (Cheese cheese : cheeseService.getAvailableCheeses()) {
            System.out.println(cheese);
        }
    }

    private static void viewCart() {
        System.out.println("Cheeses in Cart:");
        for (Cheese cheese : cheeseShop.getCart()) {
            System.out.println(cheese);
        }
    }

    private static void viewCustomerInfo() {
        System.out.println("Customer: " + customer.getName());
        System.out.println("Balance: €" + customer.getMoney());
        System.out.println("Bought Cheeses: ");
        for (Cheese cheese : customer.getBoughtCheeses()) {
            System.out.println(cheese);
        }
    }
}

