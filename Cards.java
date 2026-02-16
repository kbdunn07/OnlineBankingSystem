package javaPackage;
import java.util.ArrayList;
import javaPackage.Users;
import javaPackage.Accounts;
import javaPackage.Transactions;

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
		this.cardNum = "" + (int)(Math.random() * (9999 - 1000 + 1) + 1000) + (int)(Math.random() * (9999 - 1000 + 1) + 1000) + (int)(Math.random() * (9999 - 1000 + 1) + 1000) + (int)(Math.random() * (9999 - 1000 + 1) + 1000);
		this.CVV = (int)(Math.random() * (999 - 100 + 1) + 100);
		user.cards.add(this);
		account.cards.add(this);
	}
	
	public String display() {
		return user.getName() + " | " + type + " | " + cardNum + " | " + CVV + " | " + expiration + "\n";
	}
	
}
