package management;


import java.io.*;
import java.util.List;

public class FileHandler {

    public static void loadFromFile(List<Transaction> list, String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("No data file found. a new file will be created.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Transaction t = Transaction.fromString(line);
                if (t != null) list.add(t);
            }
            System.out.println("Loaded " + list.size() + " transactions from file.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void saveToFile(List<Transaction> list, String filename) {
        try {
            File file = new File(filename);
            file.getParentFile().mkdirs(); 
            try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
                for (Transaction t : list) {
                    pw.println(t.toString());
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving : " + e.getMessage());
        }
    }
}
