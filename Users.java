package BankingPackage;
import java.util.ArrayList;


public class Users {
    String username;
    String password;

    ArrayList<Accounts> accounts = new ArrayList<>();
    ArrayList<Cards> cards = new ArrayList<>();

    public Users(String username, String password) {

        this.username = username;
        this.password = password;
    }

    public ArrayList<Accounts> getAccounts() {
        return accounts;
    }

    public void addAccount(Accounts account) {
        accounts.add(account);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String display() {
        String result = username + " | " + password + "\n";
        for (Accounts a : accounts) {
            result += "\n" + a.display();
        }
        return result;
    }

}
