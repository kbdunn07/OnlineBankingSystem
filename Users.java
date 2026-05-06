package BankingPackage;
import java.util.ArrayList;


public class Users {
    String username;
    String password;
    int id;

    ArrayList<Accounts> accounts = new ArrayList<>();
    ArrayList<Cards> cards = new ArrayList<>();

    public Users(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public ArrayList<Accounts> getAccounts() {
        return accounts;
    }

    public void addAccount(Accounts account) {
        accounts.add(account);
    }

    public int getId() {
        return id;
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
