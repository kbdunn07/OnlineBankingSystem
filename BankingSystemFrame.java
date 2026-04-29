package javaPackage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

//real coders don't use comments so they have no idea what they are coding when they come back

public class BankingSystemFrame extends JFrame {

    private CardLayout mainCard;
    private JPanel mainPanel;
    private ArrayList<Users> users = new ArrayList<>();
    private Users currentUser;

    public BankingSystemFrame() {
        setTitle("Banking System");

        mainCard = new CardLayout();
        mainPanel = new JPanel(mainCard);

        mainPanel.add(createLoginPage(), "login");
        mainPanel.add(createAccountPage(), "create");
        //mainPanel.add(createUserHomePage(), "user home");
        mainPanel.add(createLoggedInAccountPage(), "create account from home");

        add(mainPanel);
    }

    private void openAccountPage(Accounts acc) {

        JPanel panel = new JPanel(new BorderLayout());

        JLabel title = new JLabel(acc.getAccName(), SwingConstants.CENTER);
        title.setFont(new Font(Font.DIALOG, Font.BOLD, 24));

        JLabel info = new JLabel("Account #: " + acc.accNum + " | Balance: $" + acc.getBalance(), SwingConstants.CENTER);

        JPanel top = new JPanel(new GridLayout(2, 1));
        top.add(title);
        top.add(info);

        JPanel transactionsPanel = new JPanel();
        transactionsPanel.setLayout(new BoxLayout(transactionsPanel, BoxLayout.Y_AXIS));

        for (Transactions t : acc.transactions) {
            JLabel label;
            if (t.type.equalsIgnoreCase("Deposit")) {
                label = new JLabel("Deposit: +$" + t.amount);
            } else {
                label = new JLabel(t.type + ": -$" + t.amount);
            }
            transactionsPanel.add(label);
        }

        JScrollPane transactionScroll = new JScrollPane(transactionsPanel);

        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));

        for (Cards c : acc.cards) {
        	
        	/*
            JLabel cardLabel = new JLabel("Card: " + c.getCardNumber() + " | CVV: " + c.getCVV() + " | Expiration: " + c.getExpiration());
            cardsPanel.add(cardLabel);
            */
        	
        	JPanel card = new JPanel();
        	card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        	card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createTitledBorder(c.type),
                    BorderFactory.createEmptyBorder(5,10,5,10)
            ));
        	card.setPreferredSize(new Dimension(120, 100));
        	
        	card.add(new JLabel("Card Number: " + c.cardNum));
        	card.add(new JLabel("CVV: " + c.CVV));
        	card.add(new JLabel("Expiration: " + c.expiration));
        	cardsPanel.add(card);
        	
        	/// for future Kyler, add preferred column count, as well as set scrollpane to exact preferred dimensionA
        }

        JScrollPane cardScroll = new JScrollPane(cardsPanel);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel transactionsHeader = new JLabel("Transactions");
        transactionsHeader.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel cardsHeader = new JLabel("Cards");
        cardsHeader.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(transactionsHeader);
        centerPanel.add(transactionScroll);
        centerPanel.add(cardsHeader);
        centerPanel.add(cardScroll);

        JButton backButton = new JButton("Back");
        JButton addTransaction = new JButton("Add Transaction");
        JButton addCard = new JButton("Add Card");

        backButton.addActionListener(e -> {
            mainCard.show(mainPanel, "user home");
        });

        addTransaction.addActionListener(e -> {
            JPanel txPage = createAddTransactionPage(acc);
            mainPanel.add(txPage, "add transaction");
            mainCard.show(mainPanel, "add transaction");
        });

        addCard.addActionListener(e -> {
            JPanel cardPage = createAddCardPage(acc);

            mainPanel.add(cardPage, "add card");
            mainCard.show(mainPanel, "add card");

            mainPanel.revalidate();
            mainPanel.repaint();
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.add(backButton);
        bottomPanel.add(addTransaction);
        bottomPanel.add(addCard);

        panel.add(top, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        mainPanel.add(panel, "account page");
        mainCard.show(mainPanel, "account page");
        mainCard.show(mainPanel, "account page");
    }
    private JPanel createAddCardPage(Accounts acc) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        String[] cardTypes = {"Credit", "Debit"};
        JComboBox<String> typeDropdown = new JComboBox<>(cardTypes);

        JButton createButton = new JButton("Create Card");
        JButton backButton = new JButton("Back");

        layout.insets = new Insets(10, 10, 10, 10);
        layout.fill = GridBagConstraints.HORIZONTAL;

        JLabel header = new JLabel("Add Card");
        header.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
        header.setHorizontalAlignment(SwingConstants.CENTER);

        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridwidth = 2;
        panel.add(header, layout);

        layout.gridwidth = 1;

        layout.gridx = 0;
        layout.gridy = 1;
        layout.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Card Type:"), layout);

        layout.gridx = 1;
        layout.anchor = GridBagConstraints.WEST;
        panel.add(typeDropdown, layout);

        layout.gridy = 2;

        layout.gridx = 0;
        layout.anchor = GridBagConstraints.EAST;
        panel.add(backButton, layout);

        layout.gridx = 1;
        layout.anchor = GridBagConstraints.WEST;
        panel.add(createButton, layout);

        backButton.addActionListener(e -> {
            openAccountPage(acc);
        });

        createButton.addActionListener(e -> {
            String type = (String) typeDropdown.getSelectedItem();
            Cards newCard = new Cards(currentUser, acc, type);
            acc.cards.add(newCard);
            openAccountPage(acc);
        });

        return panel;
    }
    private Users authenticate(String username, String password) {
        for (Users user : users) {
            if (user.getUsername().equals(username) &&
                    user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private JPanel createLoginPage() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        JTextField usernameField = new JTextField(15);
        JTextField passwordField = new JTextField(15);
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel header = new JLabel("Account Login");

        JButton loginButton = new JButton("Login");
        JButton createAccountButton = new JButton("Create New Account");

        loginButton.addActionListener(e -> {
            Users user = authenticate(usernameField.getText(), passwordField.getText());

            if (user != null) {
                currentUser = user;
                mainPanel.add(createUserHomePage(), "user home");
                mainCard.show(mainPanel, "user home");
            } else {
                JOptionPane.showMessageDialog(this, "Username or Password is incorrect.");
                usernameField.setText("");
                passwordField.setText("");
            }
        });

        createAccountButton.addActionListener(e -> {
            mainCard.show(mainPanel, "create");
        });

        layout.insets = new Insets(10, 10, 10, 10);
        layout.fill = GridBagConstraints.HORIZONTAL;

        header.setFont(new Font(Font.DIALOG, Font.BOLD, 28));
        header.setForeground(new Color(0, 0, 140));
        header.setHorizontalAlignment(SwingConstants.CENTER);

        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridwidth = 2;
        panel.add(header, layout);

        layout.gridwidth = 1;

        layout.gridx = 0;
        layout.gridy = 1;
        layout.anchor = GridBagConstraints.EAST;
        panel.add(usernameLabel, layout);

        layout.gridx = 1;
        layout.anchor = GridBagConstraints.WEST;
        panel.add(usernameField, layout);

        layout.gridx = 0;
        layout.gridy = 2;
        layout.anchor = GridBagConstraints.EAST;
        panel.add(passwordLabel, layout);

        layout.gridx = 1;
        layout.anchor = GridBagConstraints.WEST;
        panel.add(passwordField, layout);

        layout.gridx = 0;
        layout.gridy = 3;
        layout.gridwidth = 2;
        layout.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, layout);

        layout.gridy = 4;
        panel.add(createAccountButton, layout);

        return panel;
    }

    private JPanel createAccountPage() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        JTextField newUsername = new JTextField(15);
        JTextField newPassword = new JTextField(15);

        JButton createButton = new JButton("Create Account");
        JButton backButton = new JButton("Back");

        createButton.addActionListener(e -> {
            boolean created = false;
            String username = newUsername.getText();
            String password = newPassword.getText();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter all fields.");
                return;
            }

            for (Users user : users) {
                if (user.getUsername().equals(username)) {
                    JOptionPane.showMessageDialog(this, "Username already exists.");
                    return;
                }
            }

            users.add(new Users(username, password));
            JOptionPane.showMessageDialog(this, "Account has been created.");
            created = true;

            if (created) {
                mainCard.show(mainPanel, "login");
            }
        });

        backButton.addActionListener(e -> {
            mainCard.show(mainPanel, "login");
        });

        layout.insets = new Insets(10, 10, 10, 10);
        layout.fill = GridBagConstraints.HORIZONTAL;

        JLabel header = new JLabel("Create Account");

        header.setFont(new Font(Font.DIALOG, Font.BOLD, 28));
        header.setForeground(new Color(0, 0, 140));
        header.setHorizontalAlignment(SwingConstants.CENTER);

        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridwidth = 2;
        panel.add(header, layout);

        layout.gridwidth = 1;

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        layout.gridx = 0;
        layout.gridy = 1;
        layout.anchor = GridBagConstraints.EAST;
        panel.add(usernameLabel, layout);

        layout.gridx = 1;
        layout.anchor = GridBagConstraints.WEST;
        panel.add(newUsername, layout);

        layout.gridx = 0;
        layout.gridy = 2;
        layout.anchor = GridBagConstraints.EAST;
        panel.add(passwordLabel, layout);

        layout.gridx = 1;
        layout.anchor = GridBagConstraints.WEST;
        panel.add(newPassword, layout);

        layout.gridx = 0;
        layout.gridy = 3;
        layout.gridwidth = 2;
        layout.anchor = GridBagConstraints.CENTER;
        panel.add(createButton, layout);

        layout.gridy = 4;
        panel.add(backButton, layout);

        return panel;
    }

    private JPanel createUserHomePage() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel header = new JLabel();
        header.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
        header.setForeground(new Color(0, 0, 140));
        header.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel accountsPanel = new JPanel();
        accountsPanel.setLayout(new GridLayout(0,3,5,5));

        JScrollPane scrollPane = new JScrollPane(accountsPanel);

        JButton createAccountButton = new JButton("Create New Account");
        JButton backButton = new JButton("Back");

        createAccountButton.addActionListener(e -> {
            mainCard.show(mainPanel, "create account from home");
        });

        backButton.addActionListener(e -> {
            mainCard.show(mainPanel,  "login");
        });

        if (currentUser != null) {
            header.setText("Welcome, " + currentUser.getUsername());

            if (currentUser.getAccounts().isEmpty()) {
                JLabel empty = new JLabel("No accounts found.");
                empty.setAlignmentX(Component.CENTER_ALIGNMENT);
                accountsPanel.setLayout(new BorderLayout());
                accountsPanel.add(empty, BorderLayout.NORTH);
            } else {
                for (Accounts acc : currentUser.getAccounts()) {

                    JPanel card = new JPanel();
                    card.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
                    card.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createTitledBorder(acc.getAccName()),
                            BorderFactory.createEmptyBorder(5,10,5,10)
                    ));
                    card.setPreferredSize(new Dimension(120, 100));

                    card.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            openAccountPage(acc);
                        }
                    });

                    card.add(new JLabel("Account #: " + acc.accNum));
                    card.add(new JLabel("Routing #: " + acc.routeNum));
                    card.add(new JLabel("Name: " + acc.getAccName()));
                    card.add(new JLabel("Balance: $" + acc.getBalance()));

                    accountsPanel.add(card);
                }
                accountsPanel.revalidate();
                accountsPanel.repaint();
            }
        } else {
            header.setText("No user logged in");
        }

        panel.add(header, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();

        if (currentUser != null) {
            buttonPanel.add(createAccountButton);
            buttonPanel.add(backButton);
        }

        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createAddTransactionPage(Accounts acc) {

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        JTextField amountField = new JTextField(15);

        String[] types = {"Deposit", "Withdrawal", "Purchase"};
        JComboBox<String> typeBox = new JComboBox<>(types);

        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Back");

        layout.insets = new Insets(10, 10, 10, 10);
        layout.fill = GridBagConstraints.HORIZONTAL;

        JLabel header = new JLabel("Add Transaction");
        header.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
        header.setHorizontalAlignment(SwingConstants.CENTER);

        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridwidth = 2;
        panel.add(header, layout);

        layout.gridwidth = 1;

        layout.gridx = 0;
        layout.gridy = 1;
        panel.add(new JLabel("Amount:"), layout);

        layout.gridx = 1;
        panel.add(amountField, layout);

        layout.gridx = 0;
        layout.gridy = 2;
        panel.add(new JLabel("Type:"), layout);

        layout.gridx = 1;
        panel.add(typeBox, layout);

        submitButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                String type = (String) typeBox.getSelectedItem();

                if ((type.equalsIgnoreCase("Withdrawal") || type.equalsIgnoreCase("Purchase"))
                        && amount > acc.getBalance()) {

                    JOptionPane.showMessageDialog(this, "Not enough funds brokie.");
                    return;
                }
                new Transactions(amount, acc, type, null);

                openAccountPage(acc);

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        });

        backButton.addActionListener(e -> {
            openAccountPage(acc);
        });

        layout.gridx = 0;
        layout.gridy = 3;
        layout.gridwidth = 2;
        panel.add(submitButton, layout);

        layout.gridy = 4;
        panel.add(backButton, layout);

        return panel;
    }

    private JPanel createLoggedInAccountPage() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        JTextField accountNameField = new JTextField(15);
        JTextField balanceField = new JTextField(15);

        JButton createButton = new JButton("Create Account");
        JButton backButton = new JButton("Back");

        layout.insets = new Insets(10, 10, 10, 10);
        layout.fill = GridBagConstraints.HORIZONTAL;

        JLabel header = new JLabel("Create Bank Account");
        header.setFont(new Font(Font.DIALOG, Font.BOLD, 28));
        header.setForeground(new Color(0, 0, 140));
        header.setHorizontalAlignment(SwingConstants.CENTER);

        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridwidth = 2;
        panel.add(header, layout);

        layout.gridwidth = 1;

        JLabel nameLabel = new JLabel("Account Name:");
        JLabel balanceLabel = new JLabel("Starting Balance:");

        layout.gridx = 0;
        layout.gridy = 1;
        panel.add(nameLabel, layout);

        layout.gridx = 1;
        panel.add(accountNameField, layout);

        layout.gridx = 0;
        layout.gridy = 2;
        panel.add(balanceLabel, layout);

        layout.gridx = 1;
        panel.add(balanceField, layout);

        createButton.addActionListener(e -> {
            try {
                String name = accountNameField.getText();
                double balance = Double.parseDouble(balanceField.getText());

                if (currentUser != null) {
                    Accounts acc = new Accounts(currentUser, name, balance);
                    currentUser.addAccount(acc);
                    accountNameField.setText("");
                    balanceField.setText("");
                }
                mainPanel.add(createUserHomePage(), "user home");
                mainCard.show(mainPanel, "user home");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        });

        backButton.addActionListener(e -> {
            mainCard.show(mainPanel, "user home");
        });

        layout.gridx = 0;
        layout.gridy = 3;
        layout.gridwidth = 2;
        panel.add(createButton, layout);

        layout.gridy = 4;
        panel.add(backButton, layout);

        return panel;

    }

    public static void main(String[] args) {

        BankingSystemFrame mainFrame = new BankingSystemFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 680);
        mainFrame.setVisible(true);

    }
}
