import java.util.ArrayList;
import java.util.Scanner;

class Expense {
    private String description;
    private double amount;

    Expense(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }
}

class ExpenseTracker {
    private ArrayList<Expense> expenses = new ArrayList<>();

    public void addExpense(String description, double amount) {
        expenses.add(new Expense(description, amount));
        System.out.println("Expense added successfully!\n");
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to show.\n");
            return;
        }

        System.out.println("All Expenses:");
        for (int i = 0; i < expenses.size(); i++) {
            Expense e = expenses.get(i);
            System.out.printf("%d. %s - $%.2f%n", i + 1, e.getDescription(), e.getAmount());
        }
        System.out.println();
    }

    public double totalExpenses() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        return total;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseTracker tracker = new ExpenseTracker();
        boolean running = true;

        while (running) {
            System.out.println("=== Personal Expense Tracker ===");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Show Total Expenses");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.\n");
                scanner.next(); // Clear invalid input
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter description: ");
                    String desc = scanner.nextLine();

                    double amt;
                    while (true) {
                        System.out.print("Enter amount: $");
                        if (scanner.hasNextDouble()) {
                            amt = scanner.nextDouble();
                            scanner.nextLine(); // Consume newline
                            break;
                        } else {
                            System.out.println("Invalid amount. Please enter a number.");
                            scanner.next(); // Clear invalid input
                        }
                    }

                    tracker.addExpense(desc, amt);
                    break;
                case 2:
                    tracker.viewExpenses();
                    break;
                case 3:
                    System.out.printf("Total Expenses: $%.2f%n%n", tracker.totalExpenses());
                    break;
                case 4:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.\n");
            }
        }

        scanner.close();
    }
}
