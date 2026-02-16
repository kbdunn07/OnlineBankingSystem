package javaPackage;
import javaPackage.Accounts;
import javaPackage.Users;
import javaPackage.Transactions;
import javaPackage.Cards;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		Users user1 = new Users("Kyler Dunn", "12345a");
		Accounts acc1 = new Accounts(user1, "Kyler's Account", 10000);
		Accounts acc2 = new Accounts(user1, "Kyler's Account 2", 50106.12);
		Cards card1 = new Cards(user1, acc1, "Debit");
		System.out.print(user1.display());
	}
}
