package BankingPackage;
import java.util.ArrayList;

public class Cards {
    Users user;
    Accounts account;
    String type;
    String cardNum;
    String expiration;
    int CVV;
    ArrayList<Transactions> transactions = new ArrayList<>();

    public Cards(Users user, Accounts account, String type) {
        this.user = user;
        this.account = account;
        this.type = type;
        this.expiration = (int)(Math.random() * (12) + 1) + "/" + (int)(Math.random() * (2040 - 2026 + 1) + 2026);
        this.cardNum = "";
        for (int i = 0; i < 16; i++) {
            this.cardNum += (int)(Math.random() * 10);
        }
        this.cardNum = this.cardNum.substring(0, 4) + " " + this.cardNum.substring(4, 8) + " " + this.cardNum.substring(8,12) + " " + this.cardNum.substring(12,16);
        this.CVV = (int)(Math.random() * (999 - 100 + 1) + 100);
        user.cards.add(this);
    }
    public int getCVV() {
        return CVV;
    }

    public String getExpiration() {
        return expiration;
    }

    public String getCardNumber() {
        return cardNum;
    }

    public String display() {
        return user.getUsername() + " | " + type + " | " + cardNum + " | " + CVV + " | " + expiration + "\n";
    }

}
