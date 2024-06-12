/* Develop a simple book management application with ArrayList.
   User should be able to add a book to ArrayList.
   User should be able to remove a book from ArrayList.

Easy: Work with String in ArrayList. All the actions should be available for user.

Medium: Work with String User should be able to repeat all the actions infinitely.

NOT DONE Hard: Create a Book class and work with Book object to the ArrayList.*/


Easy+Medium:
Main.java

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static BookManager bookManager = new BookManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Press 1 to add a book");
            System.out.println("Press 2 to remove a book");
            System.out.println("Press 3 to show the book list");
            System.out.println("Press x to quit");
            
            String userInput = scanner.nextLine();
            

            if(userInput.equals("1")) {
                addBook();
            } else if(userInput.equals("2")) {
                removeBook();
            } else if(userInput.equals("3")) {
                showBookList();
            } else if(userInput.equals("x")) {
                break;
            } else {
                System.out.println("Invalid input, please try again");
            }
        }
        scanner.close();
    }

    public static void addBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the book you want to add:");
        String name = scanner.nextLine();

        bookManager.addBook(name);
    }

    public static void removeBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the book you want to remove:");
        String name = scanner.nextLine();

        bookManager.removeBook(name);
    }

    public static void showBookList() {
        ArrayList<String> books = bookManager.getBooks();
        System.out.println("Your book list:");
        for(String book : books) {
            System.out.println(book);
        }
    }
}

BookManager.java

   import java.util.ArrayList;

public class BookManager {
    private ArrayList<String> books = new ArrayList<>();

    public void addBook(String book) {
        books.add(book);
    }

    public void removeBook(String book) {
        if (books.contains(book)) {
            books.remove(book);
        } else {
            System.out.println("The book is not found in the list");
        }
    }

    public ArrayList<String> getBooks() {
        return books;
    }
}
