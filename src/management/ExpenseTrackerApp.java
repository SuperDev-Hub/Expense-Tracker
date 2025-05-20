package management;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ExpenseTrackerApp {

    private static final String FILE_NAME = "data/transactions.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TransactionManager manager = new TransactionManager();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        FileHandler.loadFromFile(manager.getAll(), FILE_NAME);

        while (true) {
            System.out.println("\n=== Expense Tracker by Monthly ===");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Monthly Summary");
            System.out.print("Choose option: ");
            int option = sc.nextInt();
            sc.nextLine();

            if (option == 1) {
                System.out.println("Choose category: 1. Salary  2. Business  3. Other");
                int cat = sc.nextInt();
                sc.nextLine();

                String category;
                if (cat == 1) {
                    category = "Salary";
                } else if (cat == 2) {
                    category = "Business";
                } else if (cat == 3) {
                    category = "Other";
                } else {
                    System.out.println("Invalid income category.");
                    continue;
                }

                System.out.print("Enter Amount: ");
                double amount = sc.nextDouble();
                sc.nextLine();

                System.out.print("Enter Date (dd-MM-yyyy): ");
                LocalDate date = LocalDate.parse(sc.nextLine(), formatter);

                if (date.isAfter(LocalDate.now())) {
                    System.out.println("Future date not allowed.");
                    continue;
                }

                Transaction t = new Transaction("Income", category, amount, date);
                manager.add(t);
                FileHandler.saveToFile(manager.getAll(), FILE_NAME);
                System.out.println("Income added and saved: " + t);

            } else if (option == 2) {
                System.out.print("Enter Date (dd-MM-yyyy): ");
                LocalDate date = LocalDate.parse(sc.nextLine(), formatter);

                if (date.isAfter(LocalDate.now())) {
                    System.out.println(" Future date not allowed.");
                    continue;
                }

                boolean hasIncomeForMonth = manager.getAll().stream()
                        .anyMatch(t -> t.getType().equalsIgnoreCase("Income") &&
                                t.getDate().getYear() == date.getYear() &&
                                t.getDate().getMonthValue() == date.getMonthValue());

                if (!hasIncomeForMonth) {
                    System.out.println("No income found for " + date.getMonth() + " " + date.getYear() + ". Please add income first.");
                    continue;
                }

                System.out.println("Choose category: 1. Food  2. Rent  3. Travel  4. Other");
                int cat = sc.nextInt();
                sc.nextLine();

                String category;
                if (cat == 1) {
                    category = "Food";
                } else if (cat == 2) {
                    category = "Rent";
                } else if (cat == 3) {
                    category = "Travel";
                } else if (cat == 4) {
                    category = "Other";
                } else {
                    System.out.println("Invalid expense category.");
                    continue;
                }

                System.out.print("Enter Amount: ");
                double amount = sc.nextDouble();
                sc.nextLine();

                Transaction t = new Transaction("Expense", category, amount, date);
                manager.add(t);
                FileHandler.saveToFile(manager.getAll(), FILE_NAME);
                System.out.println("Expense added and saved: " + t);

            } else if (option == 3) {
                System.out.print("Enter Year: ");
                int year = sc.nextInt();
                System.out.print("Enter Month (1-12): ");
                int month = sc.nextInt();
                manager.showMonthlySummary(year, month);

            } else {
                System.out.println("Invalid option.");
            }
        }
    }
}
