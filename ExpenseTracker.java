
import java.io.FileWriter; // To write data to a file
import java.io.FileReader; // To read data from a file
import java.io.BufferedWriter; // To write data efficiently
import java.io.File;
import java.io.BufferedReader; // To read data efficiently
import java.io.IOException; // To handle file I/O exceptions
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages a list of expenses, allowing operations such as adding, viewing,
 * deleting, searching, and generating reports.
 * 
 * @author logicthreader
 * @version 1.0
 * @since 2024-01-01
 */
public class ExpenseTracker {

    // Attributes
    private ArrayList<Expense> expenses; // List to store Expense objects

    /**
     * Constructor to initialize the expense list.
     */
    public ExpenseTracker() {
        expenses = new ArrayList<>();
    }

    /**
     * Adds a new expense to the tracker.
     * 
     * @param date     The date of the expense.
     * @param amount   The amount spent.
     * @param category The category of the expense.
     */
    public void addExpense(String date, double amount, String category) {
        Expense expense = new Expense(date, amount, category);
        expenses.add(expense);
    }

    /**
     * Displays all expenses in the tracker.
     */
    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to display");
            return;
        }
        for (int i = 0; i < expenses.size(); i++) {
            System.out.println(expenses.get(i));
        }
    }

    /**
     * Deletes an expense by its index.
     * 
     * @param index The index of the expense to delete.
     */
    public void deleteExpense(int index) {

        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
        } else {
            System.out.println("Invalid index. Cannot delete expense.");
        }
    }

    /**
     * Calculates and returns the total amount of all expenses.
     * 
     * @return The total sum of all expenses.
     */
    public double getTotalExpenses() {

        double total = 0.0;
        for (int i = 0; i < expenses.size(); i++) {
            total += expenses.get(i).getAmount();
        }
        return total;
    }

    /**
     * Saves all expenses to a file.
     * 
     * @param filename The name of the file where expenses will be saved.
     */
    public void saveToFile(String filename) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Expense expense : expenses) {
                writer.write(expense.getDate() + "," + expense.getAmount() + "," + expense.getCategory());
                writer.newLine(); // Move to the next line for each expense
            }
            System.out.println("Expenses saved successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Error in saving to file: " + e.getMessage());
        }

    }

    /**
     * Loads expenses from a file into the tracker.
     * 
     * @param filename The name of the file to load expenses from.
     */
    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            expenses.clear(); // Clear existing data to avoid duplicates

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Split line by commas
                if (parts.length != 3) {
                    System.err.println("Skipping malformed line: " + line);
                    continue;
                }
                if (parts.length == 3) {
                    String date = parts[0];
                    double amount = Double.parseDouble(parts[1]); // Correct conversion
                    String category = parts[2];
                    expenses.add(new Expense(date, amount, category));
                }
            }
            System.out.println("Expenses loaded successfully from " + filename);
        } catch (IOException e) {
            System.err.println("Error loading from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format in file: " + e.getMessage());
        }
    }

    /**
     * Main method for testing the ExpenseTracker class.
     */
    public static void main(String[] args) {
        ExpenseTracker tracker = new ExpenseTracker();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("=== Expense Tracker Menu ===");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Delete Expense by Index");
            System.out.println("4. Search Expenses");
            System.out.println("   a. By Date");
            System.out.println("   b. By Category");
            System.out.println("   c. By Amount");
            System.out.println("5. Generate Reports");
            System.out.println("   a. Total by Category");
            System.out.println("   b. Expenses in Date Range");
            System.out.println("6. Save Expenses to File");
            System.out.println("7. Load Expenses from File");
            System.out.println("8. Exit");
            System.out.print("Please enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Enter date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();

                    System.out.println("Enter amount: ");
                    double amount = Double.parseDouble(scanner.nextLine());

                    System.out.println("Enter category: ");
                    String category = scanner.nextLine();

                    tracker.addExpense(date, amount, category);
                    System.out.println("Expense added successfully!");
                    break;
                case "2":
                    tracker.viewExpenses();
                    break;
                case "3":
                    System.out.println("Enter the index of the expense to be deleted: ");
                    int index = Integer.parseInt(scanner.nextLine());
                    tracker.deleteExpense(index);
                    System.out.println("If your index is valid, the expense will be deleted.");
                    break;
                case "4a":
                    System.out.println("Enter date (YYYY-MM-DD): ");
                    String searchDate = scanner.nextLine();
                    System.out.println(tracker.searchByDate(searchDate));
                    break;
                case "4b":
                    System.out.println("Enter category: ");
                    String searchCategory = scanner.nextLine();
                    System.out.println(tracker.searchByCategory(searchCategory));
                    break;
                case "4c":
                    System.out.println("Enter amount: ");
                    double searchAmount = Double.parseDouble(scanner.nextLine());
                    System.out.println(tracker.searchByAmount(searchAmount));
                    break;
                case "5a":
                    System.out.println("Enter category to calculate total expenses: ");
                    String reportCategory = scanner.nextLine();
                    double totalCategoryExpenses = tracker.getTotalByCategory(reportCategory);
                    System.out
                            .println("Total expenses for category '" + reportCategory + "': $" + totalCategoryExpenses);
                    break;
                case "5b":
                    System.out.println("Enter start date (YYYY-MM-DD): ");
                    String startDate = scanner.nextLine();
                    System.out.println("Enter end date (YYYY-MM-DD): ");
                    String endDate = scanner.nextLine();
                    ArrayList<Expense> expensesInRange = tracker.getExpensesInDateRange(startDate, endDate);

                    if (expensesInRange.isEmpty()) {
                        System.out.println("No expenses found in the specified date range.");
                    } else {
                        System.out.println("Expenses from " + startDate + " to " + endDate + ":");
                        for (Expense expense : expensesInRange) {
                            System.out.println(expense);
                        }
                    }
                    break;
                case "6":
                    String saveFilename = getValidFilename(scanner, "save");
                    tracker.saveToFile(saveFilename);
                    System.out.println("Expenses successfully saved to " + saveFilename);
                    break;
                case "7":
                    String loadFileName = getValidFilename(scanner, "load");
                    if (!fileExists(loadFileName)) {
                        System.out.println("Error: File does not exist or is not a valid file.");
                        break;
                    }
                    tracker.loadFromFile(loadFileName);
                    System.out.println("Expenses loaded successfully from: " + loadFileName);
                    break;
                case "8":
                    exit = true;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    /**
     * Finds expenses by date.
     * 
     * @param date The date to search for.
     * @return List of matching expenses.
     */
    public ArrayList<Expense> searchByDate(String date) {
        ArrayList<Expense> result = new ArrayList<>();
        for (Expense expense : expenses) {
            if (expense.getDate().equals(date)) {
                result.add(expense);
            }
        }
        return result;
    }

    /**
     * Finds expenses by category.
     * 
     * @param category The category to search for.
     * @return List of matching expenses.
     */
    public ArrayList<Expense> searchByCategory(String category) {
        ArrayList<Expense> result = new ArrayList<>();
        for (Expense expense : expenses) {
            if (expense.getCategory().equals(category)) {
                result.add(expense);
            }
        }
        return result;

    }

    /**
     * Finds expenses by amount.
     * 
     * @param amount The amount to search for.
     * @return List of matching expenses.
     */
    public ArrayList<Expense> searchByAmount(double amount) {
        ArrayList<Expense> result = new ArrayList<>();
        for (Expense expense : expenses) {
            if (expense.getAmount() == amount) {
                result.add(expense);
            }
        }
        return result;
    }

    /**
     * Calculates the total expenses for a specific category.
     * 
     * @param category The category to calculate the total for.
     * @return The total sum of expenses in the given category.
     */
    public double getTotalByCategory(String category) {
        double sum = 0.0;
        for (Expense expense : expenses) {
            if (expense.getCategory().equals(category)) {
                sum += expense.getAmount();
            }
        }
        return sum;
    }

    /**
     * Retrieves all expenses within a given date range.
     * 
     * @param startDate The start date of the range (inclusive).
     * @param endDate   The end date of the range (inclusive).
     * @return A list of expenses within the specified date range.
     */
    public ArrayList<Expense> getExpensesInDateRange(String startDate, String endDate) {
        ArrayList<Expense> result = new ArrayList<>();
        for (Expense expense : expenses) {
            if (expense.getDate().compareTo(startDate) >= 0 && expense.getDate().compareTo(endDate) <= 0) {
                result.add(expense);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No expenses found in the given date range.");
        }
        return result;
    }

    private static boolean isValidFilename(String filename) {
        return filename != null && !filename.trim().isEmpty() && filename.matches("^[a-zA-Z0-9._-]+\\.(txt|csv)$");
    }

    /**
 * Prompts the user until a valid filename is entered.
 * 
 * @param scanner The Scanner object for user input.
 * @param operation The operation being performed (save or load).
 * @return A valid filename entered by the user.
 */
private static String getValidFilename(Scanner scanner, String operation) {
    String filename;
    do {
        System.out.println("Enter a valid filename to " + operation + " (.txt or .csv): ");
        filename = scanner.nextLine();
        if (!isValidFilename(filename)) {
            System.out.println("❌ Invalid filename. Please try again.");
        }
    } while (!isValidFilename(filename));
    return filename;
}

    /**
     * Checks if the given file exists and is a valid file.
     * 
     * @param filename The filename to check.
     * @return True if the file exists and is valid, false otherwise.
     */
    private static boolean fileExists(String filename) {
        File file = new File(filename);
        return file.exists() && file.isFile();
    }

}