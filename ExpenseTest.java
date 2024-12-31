
import java.util.ArrayList;
import java.util.Collections;

public class ExpenseTest {
    public static void main(String[] args) {
        // 1. Test Constructor Validations
        testConstructor();

        // 2. Test Getters
        testGetters();

        // 3. Test toString Method
        testToString();

        // 4. Test Comparators (Optional but Recommended)
        testComparators();
    }

    // Test Constructor Validations
    private static void testConstructor() {
        System.out.println("\n=== Testing Constructor Validation ===");
        try {
            Expense validExpense = new Expense("2024-01-01", 50.0, "Food");
            System.out.println("✅ Valid expense created: " + validExpense);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Unexpected exception: " + e.getMessage());
        }

        try {
            Expense invalidDate = new Expense("", 50.0, "Food");
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Caught invalid date: " + e.getMessage());
        }

        try {
            Expense invalidAmount = new Expense("2024-01-01", -10.0, "Food");
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Caught negative amount: " + e.getMessage());
        }

        try {
            Expense invalidCategory = new Expense("2024-01-01", 50.0, "");
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Caught invalid category: " + e.getMessage());
        }
    }

    // Test Getters
    private static void testGetters() {
        System.out.println("\n=== Testing Getters ===");
        Expense expense = new Expense("2024-01-02", 75.5, "Transport");
        System.out.println("Date: " + expense.getDate() + " (Expected: 2024-01-02)");
        System.out.println("Amount: $" + expense.getAmount() + " (Expected: 75.5)");
        System.out.println("Category: " + expense.getCategory() + " (Expected: Transport)");
    }

    // Test toString Method
    private static void testToString() {
        System.out.println("\n=== Testing toString Method ===");
        Expense expense = new Expense("2024-01-03", 100.0, "Groceries");
        System.out.println(expense.toString());
        System.out.println("Expected Output: Expense [Date: 2024-01-03, Amount: $100.00, Category: Groceries]");
    }

    // Test Comparators
    private static void testComparators() {
        System.out.println("\n=== Testing Comparators ===");
        ArrayList<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense("2024-01-03", 100.0, "Groceries"));
        expenses.add(new Expense("2024-01-01", 50.0, "Food"));
        expenses.add(new Expense("2024-01-02", 75.0, "Transport"));

        System.out.println("\nSorting by Date:");
        Collections.sort(expenses, Expense.BY_DATE);
        for (Expense expense : expenses) {
            System.out.println(expense);
        }

        System.out.println("\nSorting by Amount:");
        Collections.sort(expenses, Expense.BY_AMOUNT);
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }
}