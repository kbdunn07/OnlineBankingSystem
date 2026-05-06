package BankingPackage;
import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    public static Connection connect() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/banking_system",
                    "root",
                    "g8m3t1m3"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Connection conn = Database.connect();

        if (conn != null) {
            System.out.println("Connected successfully!");
        } else {
            System.out.println("Connection failed.");
        }
    }
}
