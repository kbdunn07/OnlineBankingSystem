package javaPackage;
import javaPackage.Accounts;
import javaPackage.Cards;
import java.util.ArrayList;


public class Users {
	String name;
	String password;
	ArrayList<Accounts> accounts = new ArrayList<>();
	ArrayList<Cards> cards = new ArrayList<>();
	
	public Users(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public String display() {
		String result = name + " | " + password + "\n";
		for (Accounts a : accounts) {
			result += "\n" + a.display();
		}
		return result;
	}
	
}
