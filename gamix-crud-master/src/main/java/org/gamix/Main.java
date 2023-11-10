package org.gamix;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.gamix.DAO.UserDAO;
import org.gamix.models.PasswordUser;
import org.gamix.models.User;

public class Main {
	
    public class loginUser extends JFrame {
    	
    	private static final long serialVersionUID = 1L;
    	private JFrame loginScreen;
    	UserDAO userDAO = new UserDAO();
    	private JLabel usernameOrEmailLabel, passwordLabel, loginTitle, logoGamix;
    	private JTextField usernameOrEmailField;
    	private JPasswordField passwordField;
    	private JButton loginButton;
    	
    	public JLabel logoGamix() {
    		ImageIcon imageIcon = new ImageIcon("./resources/android-chrome-150x150.png"); 
            JLabel logoGamix = new JLabel(imageIcon);
            logoGamix.setBounds(100, 50, imageIcon.getIconWidth(), imageIcon.getIconHeight());
            return logoGamix;		
    	}
    	public JLabel loginTitle() {
    		loginTitle = new JLabel("Acesse sua conta");
    		loginTitle.setForeground(Color.white);
    		loginTitle.setHorizontalAlignment(SwingConstants.CENTER);
    		int fontSize = 27;
    		loginTitle.setFont(new Font("Roboto", Font.BOLD, fontSize));
    		loginTitle.setBounds(26, 200, 300, 50);
    		return loginTitle;
    	}
    	public JLabel usernameOrEmailLabel() {
    		usernameOrEmailLabel = new JLabel("Nome ou Email: ");
    		usernameOrEmailLabel.setForeground(Color.white);
    		usernameOrEmailLabel.setBounds(26, 320, 200, 33);
    		return usernameOrEmailLabel;
    	}
    	public JLabel passwordLabel() {
    		passwordLabel = new JLabel("Senha: ");
    		passwordLabel.setForeground(Color.white);
    		passwordLabel.setBounds(26, 420, 200, 33);
    		return passwordLabel;
    	}
    	public JTextField usernameOrEmailField() {
    		usernameOrEmailField = new JTextField(20);
    		usernameOrEmailField.setBackground(Color.decode("#333333"));
    		usernameOrEmailField.setForeground(Color.white);
    		usernameOrEmailField.setBounds(26, 350, 307, 36);
    		return usernameOrEmailField;
    	}
    	public JPasswordField passwordField() {
    		passwordField = new JPasswordField(20);
    		passwordField.setBackground(Color.decode("#333333"));
    		passwordField.setForeground(Color.white);
    		passwordField.setBounds(26, 450, 307, 36);
    		return passwordField;
    	}
    	public JButton loginButton() {
    		loginButton = new JButton("Conectar-se");
    		loginButton.setBounds(26,627,307,47);
    		loginButton.setBackground(Color.decode("#68589D"));
    		loginButton.setForeground(Color.white);
    		loginButton.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				User loggedInUser = userDAO.login(usernameOrEmailField.getText(), new String(passwordField.getPassword()));
    				if (loggedInUser != null) {
    					
    				} else {
    					JOptionPane.showMessageDialog(loginUser.this, "Login falhou. Verifique suas credenciais.");
    				}
    			}
    		});
    		return loginButton;
    	}
    	
    	public void loginScreen() {
    		loginScreen = new JFrame();
    		loginScreen.setTitle("Gamix • Login");
    		loginScreen.setBounds(0, 0, 360, 800);
    		loginScreen.setLocationRelativeTo(null);
    		loginScreen.setLayout(null);
    		loginScreen.setResizable(false);
    		loginScreen.getContentPane().setBackground(Color.decode("#1C2034"));
    		
    		loginScreen.getContentPane().add(logoGamix());
    		loginScreen.getContentPane().add(loginTitle());
    		loginScreen.getContentPane().add(usernameOrEmailLabel());
    		loginScreen.getContentPane().add(usernameOrEmailField());
    		loginScreen.getContentPane().add(passwordLabel());
    		loginScreen.getContentPane().add(passwordField());
    		loginScreen.getContentPane().add(loginButton());
    		
    		loginScreen.setVisible(true);
    	}
    }
    
    public static void main(String[] args) {
        Main mainInstance = new Main();
        
        mainInstance.new loginUser().loginScreen();
        
    }
	
}
