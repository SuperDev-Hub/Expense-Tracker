package management;

import java.time.Month;
import java.util.*;

public class TransactionManager {
    private List<Transaction> transactions = new ArrayList<>();

    public void add(Transaction t) {
        transactions.add(t);
    }

    public List<Transaction> getAll() {
        return transactions;
    }

    public void showMonthlySummary(int year, int month) {
        double income = 0, expense = 0;
        boolean hasTransactions = false;

        for (Transaction t : transactions) {
            if (t.getDate().getYear() == year && t.getDate().getMonthValue() == month) {
                hasTransactions = true;
                if (t.getType().equalsIgnoreCase("Income")) {
                    income += t.getAmount();
                } else if (t.getType().equalsIgnoreCase("Expense")) {
                    expense += t.getAmount();
                }
            }
        }

        System.out.println("\n--- Monthly Summary for " + Month.of(month) + " " + year + " ---");

        if (!hasTransactions) {
            System.out.println(" No transactions found for this month.");
            return;
        }

        System.out.printf(" Total Income  : ₹%.2f%n", income);
        System.out.printf(" Total Expense : ₹%.2f%n", expense);
        System.out.printf(" Net Savings   : ₹%.2f%n", (income - expense));
    }
}
