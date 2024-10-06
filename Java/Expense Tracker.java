import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

class Expense {
    private String category;
    private double amount;
    private String date;

    public Expense(String category, double amount) {
        this.category = category;
        this.amount = amount;
        this.date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "category='" + category + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }
}

class ExpenseTracker {
    private ArrayList<Expense> expenses;

    public ExpenseTracker() {
        expenses = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        System.out.println("Expense added: " + expense);
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        System.out.println("All Expenses:");
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    public void generateSummary() {
        double total = 0;
        int foodCount = 0, entertainmentCount = 0, utilitiesCount = 0;

        for (Expense expense : expenses) {
            total += expense.getAmount();
            switch (expense.getCategory().toLowerCase()) {
                case "food":
                    foodCount++;
                    break;
                case "entertainment":
                    entertainmentCount++;
                    break;
                case "utilities":
                    utilitiesCount++;
                    break;
            }
        }
        System.out.println("Summary Report:");
        System.out.println("Total Expenses: " + total);
        System.out.println("Food Expenses Count: " + foodCount);
        System.out.println("Entertainment Expenses Count: " + entertainmentCount);
        System.out.println("Utilities Expenses Count: " + utilitiesCount);
    }

    public void deleteExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            System.out.println("Expense deleted: " + expenses.remove(index));
        } else {
            System.out.println("Invalid index.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ExpenseTracker tracker = new ExpenseTracker();
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("\n1. Add Expense\n2. View Expenses\n3. Generate Summary\n4. Delete Expense\n5. Exit");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    tracker.addExpense(new Expense(category, amount));
                    break;
                case "2":
                    tracker.viewExpenses();
                    break;
                case "3":
                    tracker.generateSummary();
                    break;
                case "4":
                    System.out.print("Enter index of expense to delete: ");
                    int index = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    tracker.deleteExpense(index);
                    break;
            }
        } while (!choice.equals("5"));

        scanner.close();
    }
}
