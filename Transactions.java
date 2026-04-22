package BankingPackage;

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
            account.setBalance(account.getBalance() + amount);
        } else if (type.equalsIgnoreCase("Withdrawal") || type.equalsIgnoreCase("Purchase")) {

            if (amount > account.getBalance()) {
                throw new IllegalArgumentException("Insufficient funds");
            }

            account.setBalance(account.getBalance() - amount);
        }

        account.transactions.add(this);

        if (card != null) {
            card.transactions.add(this);
        }
    }
}
