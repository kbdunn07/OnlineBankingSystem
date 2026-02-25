package javaPackage;

public class Transactions {
	double amount;
	String type; // Purchase, Deposit, Withdrawal, Transfer
	Accounts account;
	Cards card;
	
	public Transactions(double amount, Accounts account, String type, Cards card) {
		this.amount = amount;
		this.type = type;
		this.account = account;
		this.card = card;
		
		if (type.equalsIgnoreCase("Deposit")) {
			account.setBalance(amount + account.balance);
		}
		
		else if (type.equalsIgnoreCase("Withdrawal")) {
			account.setBalance(account.balance - amount);
		}
		
		else if (type.equalsIgnoreCase("Purchase")) {
			account.setBalance(account.balance - amount);
		}
		
		account.transactions.add(this);
		card.transactions.add(this);
		
	}
	
	public Transactions(double amount, Accounts account, String type) {
		this.amount = amount;
		this.type = type;
		this.account = account;
	}
	
	
	
}
