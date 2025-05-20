package management;

import java.time.LocalDate;
import java.util.Scanner;

public class ExpenseTrackerApp {

    private static final String FILE_NAME = "data/transactions.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TransactionManager manager = new TransactionManager();

        
        FileHandler.loadFromFile(manager.getAll(), FILE_NAME);

        while (true) {
            System.out.println("\n=== Expense Tracker by monthly  ===");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Monthly Summary");
            System.out.print("Choose option: ");
            int option = sc.nextInt();
            sc.nextLine(); 

            if (option == 1 || option == 2) {
                String type = (option == 1) ? "Income" : "Expense";
                String category = "";

                if (type.equals("Income")) {
                    System.out.println("Choose category: 1. Salary  2. Business");
                    int cat = sc.nextInt();
                    category = (cat == 1) ? "Salary" : "Business";
                } else {
                    System.out.println("Choose category: 1. Food  2. Rent  3. Travel");
                    int cat = sc.nextInt();
                    category = switch (cat) {
                        case 1 -> "Food";
                        case 2 -> "Rent";
                        default -> "Travel";
                    };
                }

                System.out.print("Enter Amount: ");
                double amount = sc.nextDouble();
                sc.nextLine();

                System.out.print("Enter Date (yyyy-MM-dd): ");
                LocalDate date = LocalDate.parse(sc.nextLine());

                Transaction t = new Transaction(type, category, amount, date);
                manager.add(t);

                
                FileHandler.saveToFile(manager.getAll(), FILE_NAME);

                System.out.println(type + " added and saved: " + t);

            } else if (option == 3) {
                System.out.print("Enter Year: ");
                int year = sc.nextInt();
                System.out.print("Enter Month (1-12): ");
                int month = sc.nextInt();
                manager.showMonthlySummary(year, month);

            }  else {
                System.out.println(" Invalid option.");
            }
        }
    }
}
