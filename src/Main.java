import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        try (Connection connection = DatabaseUtil.getConnection();
             Scanner scanner = new Scanner(System.in)) {
            ShoppingCart shoppingCart = new ShoppingCart();
            Manipulations manipulations = new Manipulations();
            System.out.println("Connecting to database...");
            System.out.println("Connected to database successfully.");

            UserRegistrationService userService = new UserRegistrationService();
            SellerRegistrationService sellerService = new SellerRegistrationService();

            boolean userLogIn = false;

            while (!userLogIn) {
                System.out.println("\n1. Register");
                System.out.println("2. Login");
                System.out.print("Enter your choice (1 or 2): ");
                int Choice = scanner.nextInt();
                scanner.nextLine();

                if (Choice == 1) {
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    if (userService.registerUser(username, password)) {
                        System.out.println("Registration successful.");
                    } else {
                        System.out.println("Registration failed.");
                    }
                } else if (Choice == 2) {
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    if (userService.loginUser(username, password)) {
                        System.out.println("Login successful.");
                        userLogIn = true;
                    } else {
                        System.out.println("Login failed. Please check your username and password.");
                    }
                }
            }
            if (userLogIn) {
                while (true) {
                    System.out.println("\n3. View all books");
                    System.out.println("4. Add a new book");
                    System.out.println("5. Search for a book by title");
                    System.out.println("6. Exit");
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            System.out.print("Enter username: ");
                            String username = scanner.nextLine();
                            System.out.print("Enter password: ");
                            String password = scanner.nextLine();

                            if (userService.registerUser(username, password)) {
                                System.out.println("User registration successful.");

                                System.out.print("Do you want to register as a seller? (yes/no): ");
                                String registerAsSeller = scanner.nextLine().toLowerCase();
                                if (registerAsSeller.equals("yes")) {
                                    if (sellerService.registerSeller(username)) {
                                        System.out.println("Seller registration successful.");
                                        // Add books for sale
                                        System.out.print("Do you want to add books for sale? (yes/no): ");
                                        String addBooksForSale = scanner.nextLine().toLowerCase();
                                        if (addBooksForSale.equals("yes")) {
                                            addBooksForSale(username, scanner, sellerService);
                                        }
                                    } else {
                                        System.out.println("Failed to register as a seller.");
                                    }
                                }
                            } else {
                                System.out.println("User registration failed.");
                            }
                            break;
                        case 3:
                            Manipulations.viewAllBooks();
                            break;
                        case 4:
                            manipulations.addNewBook();
                            break;
                        case 5:
                            manipulations.searchBookByTitle();
                            break;
                        case 6:
                            System.out.println("Exiting...");
                            return;
                        default:
                            System.out.println("Invalid choice, please try again.");
                        case 7:
                            System.out.print("Enter the title of the book you like: ");
                            String likedBook = scanner.nextLine();
                            shoppingCart.addLikedBook(likedBook);
                            System.out.println("Book added to Liked Books.");
                            break;
                        case 8:
                            System.out.print("Enter the title of the book you want to buy: ");
                            String desiredBook = scanner.nextLine();
                            shoppingCart.addDesiredBook(desiredBook);
                            System.out.println("Book added to Desired Books.");
                            break;
                        case 9:
                            System.out.println("Liked Books:");
                            for (String book : shoppingCart.getLikedBooks()) {
                                System.out.println(book);
                            }
                            break;
                        case 10:
                            System.out.println("Desired Books:");
                            for (String book : shoppingCart.getDesiredBooks()) {
                                System.out.println(book);
                            }
                            break;
                        case 11:
                            System.out.print("Enter the title of the book: ");
                            String bookTitle = scanner.nextLine();
                            List<Seller> sellers = Manipulations.getSellersForBook(bookTitle);
                            Manipulations.displaySellerInfo(sellers);
                            break;
                        case 12:
                            BookCondition condition = Manipulations.selectBookCondition();
                            System.out.println("Selected book condition: " + condition);
                            break;
                        case 13:
                            System.out.print("Enter the name of the seller: ");
                            String sellerName = scanner.nextLine();
                            System.out.print("Enter your review: ");
                            String reviewText = scanner.nextLine();
                            Manipulations.leaveReviewForSeller(sellerName, reviewText);
                            break;
                    }
                }
            }
            Manipulations.viewAllBooks();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
private static void addBooksForSale(String username, Scanner scanner, SellerRegistrationService sellerService) {
    while (true) {
        System.out.print("Enter the title of the book: ");
        String bookTitle = scanner.nextLine();
        System.out.print("Enter the price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter the delivery time (in days): ");
        int deliveryTime = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the condition of the book (good/used/e-book): ");
        BookCondition condition = BookCondition.valueOf(scanner.nextLine().toUpperCase());

        if (sellerService.addBookForSale(username, bookTitle, price, deliveryTime, condition)) {
            System.out.println("Book added for sale successfully.");
        } else {
            System.out.println("Failed to add book for sale.");
        }

        System.out.print("Do you want to add another book for sale? (yes/no): ");
        String addAnotherBook = scanner.nextLine().toLowerCase();
        if (!addAnotherBook.equals("yes")) {
            break;
        }
    }
}
}
