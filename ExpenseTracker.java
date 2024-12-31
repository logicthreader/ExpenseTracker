package project1;

import java.io.FileWriter; // To write data to a file
import java.io.FileReader; // To read data from a file
import java.io.BufferedWriter; // To write data efficiently
import java.io.BufferedReader; // To read data efficiently
import java.io.IOException; // To handle file I/O exceptions
import java.util.ArrayList;

/**
 * Manages a list of expenses, allowing operations such as adding, viewing,
 * deleting, and calculating totals.
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

        tracker.addExpense("2024-01-01", 50.0, "Food");
        tracker.addExpense("2024-01-02", 20.0, "Transport");
        tracker.addExpense("2024-01-01", 30.0, "Groceries");

        System.out.println("Search by Date (2024-01-01):");
        System.out.println(tracker.searchByDate("2024-01-01"));

        System.out.println("Search by Category (Food):");
        System.out.println(tracker.searchByCategory("Food"));

        System.out.println("Search by Amount (20.0):");
        System.out.println(tracker.searchByAmount(20.0));
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
        for (Expense expense : expenses)
        {
            if (expense.getDate().compareTo(startDate) >= 0 && expense.getDate().compareTo(endDate) <= 0)
            {
                result.add(expense);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No expenses found in the given date range.");
        }
        return result;
    }

}