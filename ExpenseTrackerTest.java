import java.util.ArrayList;

/**
 * Test class for ExpenseTracker to validate core functionalities.
 * 
 * @author logicthreader
 * @version 1.0
 * @since 2024-01-01
 */
public class ExpenseTrackerTest {

    public static void main(String[] args) {
        ExpenseTracker tracker = new ExpenseTracker();
        
        // === Test 1: Add Expenses ===
        System.out.println("=== Test 1: Add Expenses ===");
        tracker.addExpense("2024-01-01", 50.0, "Food");
        tracker.addExpense("2024-01-02", 20.0, "Transport");
        tracker.addExpense("2024-01-03", 30.0, "Groceries");
        tracker.viewExpenses();

        // === Test 2: Calculate Total Expenses ===
        System.out.println("\n=== Test 2: Calculate Total Expenses ===");
        System.out.println("Total Expenses: $" + tracker.getTotalExpenses());

        // === Test 3: Delete Expense ===
        System.out.println("\n=== Test 3: Delete Expense ===");
        tracker.deleteExpense(1); // Delete the second expense
        tracker.viewExpenses();
        System.out.println("Total Expenses after deletion: $" + tracker.getTotalExpenses());

        // === Test 4: Search by Date ===
        System.out.println("\n=== Test 4: Search by Date ===");
        ArrayList<Expense> searchByDate = tracker.searchByDate("2024-01-01");
        System.out.println("Expenses on 2024-01-01: " + searchByDate);

        // === Test 5: Search by Category ===
        System.out.println("\n=== Test 5: Search by Category ===");
        ArrayList<Expense> searchByCategory = tracker.searchByCategory("Groceries");
        System.out.println("Expenses in 'Groceries' category: " + searchByCategory);

        // === Test 6: Search by Amount ===
        System.out.println("\n=== Test 6: Search by Amount ===");
        ArrayList<Expense> searchByAmount = tracker.searchByAmount(50.0);
        System.out.println("Expenses with amount $50.0: " + searchByAmount);

        // === Test 7: Get Total By Category ===
        System.out.println("\n=== Test 7: Total By Category ===");
        double totalInFood = tracker.getTotalByCategory("Food");
        System.out.println("Total in 'Food' category: $" + totalInFood);

        // === Test 8: Get Expenses in Date Range ===
        System.out.println("\n=== Test 8: Expenses in Date Range ===");
        ArrayList<Expense> rangeExpenses = tracker.getExpensesInDateRange("2024-01-01", "2024-01-03");
        System.out.println("Expenses from 2024-01-01 to 2024-01-03: " + rangeExpenses);

        // === Test 9: Save to File ===
        System.out.println("\n=== Test 9: Save to File ===");
        tracker.saveToFile("test_expenses.txt");

        // === Test 10: Load from File ===
        System.out.println("\n=== Test 10: Load from File ===");
        ExpenseTracker newTracker = new ExpenseTracker();
        newTracker.loadFromFile("test_expenses.txt");
        newTracker.viewExpenses();

        System.out.println("\nAll tests completed successfully!");
    }
}