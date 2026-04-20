package javaPackage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankingSystemFrame extends JFrame implements ActionListener {
	
	CardLayout mainCard;
	GridBagConstraints layout = null;
	JLabel loginLabel;
	JLabel usernameLabel;
	JLabel passwordLabel;
	JTextField usernameField;
	JTextField passwordField;
	JButton loginButton;
	JButton createAccountButton;
	JPanel mainPanel;
	
	BankingSystemFrame() {
		setTitle("Banking System");
		mainCard = new CardLayout();
		mainPanel = new JPanel(mainCard);
		
		JPanel loginPage = createLoginPage();
		JPanel createAccountPage = createAccountPage();
		
		mainPanel.add(loginPage, "login");
		mainPanel.add(createAccountPage, "create account");
		add(mainPanel);
		
	}
	
	private JPanel createAccountPage() {
		JPanel panel = new JPanel();

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> 
        {
        	mainCard.show(mainPanel, "login");
        });

        panel.add(new JLabel("Create Account Page"));
        panel.add(backButton);

        return panel;
	}
	
	private JPanel createLoginPage() {
		JPanel panel = new JPanel();
		createAccountButton = new JButton("Create New Account");
		createAccountButton.addActionListener(e -> 
		{
			mainCard.show(mainPanel, "create account");
		});
		panel.add(createAccountButton);
		
		return panel;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		
	}
	
	public static void main(String[] args) {
        BankingSystemFrame frame = new BankingSystemFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setVisible(true);
    }

}
