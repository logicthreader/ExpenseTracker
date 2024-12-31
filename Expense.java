
import java.util.Comparator;

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
    private final String date;      // Date of the expense (e.g., "2024-01-01")
    private final double amount;    // Amount spent
    private final String category;  // Category of the expense (e.g., "Food", "Transport")

    /**
     * Constructs an Expense object with the specified date, amount, and category.
     *
     * @param date     The date of the expense.
     * @param amount   The amount spent (must be non-negative).
     * @param category The category of the expense.
     * @throws IllegalArgumentException if date/category is null/empty or amount is negative.
     */
    public Expense(String date, double amount, String category) {
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("Date cannot be null or empty");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }

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
        return String.format("Expense [Date: %s, Amount: $%.2f, Category: %s]", date, amount, category);
    }

    // Comparators for sorting
    public static final Comparator<Expense> BY_DATE = Comparator.comparing(Expense::getDate);
    public static final Comparator<Expense> BY_AMOUNT = Comparator.comparingDouble(Expense::getAmount);
}