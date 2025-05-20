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
        System.out.println("\n--- Monthly Summary for " + Month.of(month) + " " + year + " ---");

        for (Transaction t : transactions) {
            if (t.getDate().getYear() == year && t.getDate().getMonthValue() == month) {
                if (t.getType().equalsIgnoreCase("Income")) {
                    income += t.getAmount();
                } else {
                    expense += t.getAmount();
                }
            }
        }

        System.out.println("Total Income: ₹" + income);
        System.out.println("Total Expense: ₹" + expense);
        System.out.println("Net Savings: ₹" + (income - expense));
    }
}
