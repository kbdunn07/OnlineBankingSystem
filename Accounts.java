package javaPackage;
import javaPackage.Cards;
import javaPackage.Users;
import javaPackage.Transactions;
import java.util.ArrayList;


public class Accounts {
	Users user;
	String accName;
	int accNum;
	int routeNum;
	double balance;
	ArrayList<Cards> cards = new ArrayList<>();
	ArrayList<Transactions> transactions = new ArrayList<>();
	
	public Accounts(Users user, String accName, double balance) {
		this.user = user;
		this.accName = accName;
		this.balance = balance;
		this.accNum = (int)(Math.random() * (999999999 + 1));
		this.routeNum = (int)(Math.random() * (999999999 + 1));
		user.accounts.add(this);
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public String getAccName() {
		return this.accName;
	}
	
	
	public String display() {
		String result = "";
		result += accName + " | " + user.getName() + "\nAccount Number: " + accNum + "\nRouting Number: " + routeNum + "\n$" + balance + "\n";
		result += "Cards on Account:\n";
		for (Cards c : cards) {
			result += c.display();
		}
		return result;
	}
	
}
