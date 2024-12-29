package project1;

/**
 * Represents an expense entry with details about date, amount, and category.
 * Used in the Expense Tracker application to store and manage expense records.
 * 
 * @author logicthreader
 * @version 1.0
 * @since 2024-01-01
 */
public class Expense {

    // Attributes
    private String date; // Date of the expense (e.g., "2024-01-01")
    private double amount; // Amount spent
    private String category; // Category of the expense (e.g., "Food", "Transport")

    /**
     * Constructs an Expense object with the specified date, amount, and category.
     *
     * @param date     The date of the expense.
     * @param amount   The amount spent.
     * @param category The category of the expense.
     */
    public Expense(String date, double amount, String category) {
        this.date = date;
        this.amount = amount;
        this.category = category;
    }

    /**
     * Returns the amount of the expense.
     *
     * @return The expense amount.
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Returns the date of the expense.
     *
     * @return The expense date.
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Returns the category of the expense.
     *
     * @return The expense category.
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Returns a string representation of the expense.
     *
     * @return A formatted string with expense details.
     */
    @Override
    public String toString() {
        return "Expense [Category: " + category + ", Amount: $" + amount + ", Date: " + date + "]";
    }
}