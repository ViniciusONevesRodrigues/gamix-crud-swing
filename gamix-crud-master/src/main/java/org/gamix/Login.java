package org.gamix;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.gamix.DAO.UserDAO;
import org.gamix.models.User;

public class Login  {
	
	private Register registerInstance;
	private Home homeInstance;
	private JFrame loginScreen;
	private JLabel usernameOrEmailLabel, passwordLabel, loginTitle, logoGamix, redirectRegister;
	private JTextField usernameOrEmailField;
	private JPasswordField passwordField;
	private JButton loginButton;
	
    public Login(Register registerInstance, Home homeInstance) {
        this.registerInstance = registerInstance;
        this.homeInstance = homeInstance;
    }
    
	
	public JLabel logoGamix() {
		ImageIcon imageIcon = new ImageIcon("./resources/android-chrome-150x150.png"); 
        logoGamix = new JLabel(imageIcon);
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
		usernameOrEmailLabel = new JLabel("Nome ou Email ");
		usernameOrEmailLabel.setForeground(Color.white);
		usernameOrEmailLabel.setBounds(26, 320, 200, 33);
		return usernameOrEmailLabel;
	}
	public JLabel passwordLabel() {
		passwordLabel = new JLabel("Senha ");
		passwordLabel.setForeground(Color.white);
		passwordLabel.setBounds(26, 420, 200, 33);
		return passwordLabel;
	}
	public JTextField usernameOrEmailField() {
		usernameOrEmailField = new JTextField(20);
		usernameOrEmailField.setBackground(Color.decode("#333333"));
		usernameOrEmailField.setForeground(Color.white);
		usernameOrEmailField.setBounds(26, 350, 307, 36);
		usernameOrEmailField.setCaretColor(Color.WHITE);
		return usernameOrEmailField;
	}
	public JPasswordField passwordField() {
		passwordField = new JPasswordField(20);
		passwordField.setBackground(Color.decode("#333333"));
		passwordField.setForeground(Color.white);
		passwordField.setBounds(26, 450, 307, 36);
		passwordField.setCaretColor(Color.WHITE);
		return passwordField;
	}
	public JButton loginButton(UserDAO DAO) {
		loginButton = new JButton("Conectar-se");
		loginButton.setBounds(26,627,307,47);
		loginButton.setBackground(Color.decode("#68589D"));
		loginButton.setForeground(Color.white);
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User loggedInUser = DAO.login(usernameOrEmailField.getText(), new String(passwordField.getPassword()));
				if (loggedInUser != null) {
					homeInstance.homeScreen(DAO);
				} else {
					JOptionPane.showMessageDialog(null, "Login falhou. Verifique suas credenciais.");
				}
			}
		};
		loginButton.addActionListener(listener);
		return loginButton;
	}
	public JLabel redirectRegister(UserDAO DAO) {
	    redirectRegister = new JLabel("Não tem conta? ");
	    redirectRegister.setForeground(Color.white);
	    redirectRegister.setBounds(130, 700, 200, 33);

	    MouseAdapter listener = new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	if (e.getButton()==1) {
		        	registerInstance.registerScreen(DAO);
		            loginScreen.setVisible(false);
	        	}
	        }
	    };
	    
	    redirectRegister.addMouseListener(listener);
	    return redirectRegister;
	}

	
	
	public void loginScreen(UserDAO DAO) {
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
		loginScreen.getContentPane().add(loginButton(DAO));
		loginScreen.getContentPane().add(redirectRegister(DAO));
		
		loginScreen.setVisible(true);
	}

}
