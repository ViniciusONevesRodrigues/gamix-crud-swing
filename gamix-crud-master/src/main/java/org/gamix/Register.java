package org.gamix;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.gamix.DAO.UserDAO;
import org.gamix.models.PasswordUser;
import org.gamix.models.User;

public class Register {
	
	private Login loginInstance;
	private JFrame registerScreen;
	private JLabel logoGamix, registerTitle, usernameLabel, emailLabel, passwordLabel, redirectLogin;
	private JTextField usernameField, emailField;
	private JPasswordField passwordField;
	private JButton registerButton;
		
	public void setRegister(Login login) {
	    this.loginInstance = login;
	}
	
	public JLabel logoGamix() {
		ImageIcon imageIcon = new ImageIcon("./resources/android-chrome-150x150.png");
		logoGamix = new JLabel(imageIcon);
		logoGamix.setBounds(100, 50, imageIcon.getIconWidth(), imageIcon.getIconHeight());
		return logoGamix;
	}
	public JLabel registerTitle() {
		registerTitle = new JLabel("Criar uma conta");
		registerTitle.setBounds(26, 200, 300, 50);
		registerTitle.setForeground(Color.white);
		registerTitle.setHorizontalAlignment(SwingConstants.CENTER);
		int fontSize = 27;
		registerTitle.setFont(new Font("Roboto", Font.BOLD, fontSize));
		return registerTitle;
	}
	public JLabel usernameLabel() {
		usernameLabel = new JLabel("Nome do usuário");
		usernameLabel.setForeground(Color.white);
		usernameLabel.setBounds(26, 280, 200, 33);
		return usernameLabel;
	}
	public JLabel emailLabel() {
		emailLabel = new JLabel("Email");
		emailLabel.setForeground(Color.white);
		emailLabel.setBounds(26, 360, 200, 33);
		return emailLabel;
	}
	public JLabel passwordLabel() {
		passwordLabel = new JLabel("Senha");
		passwordLabel.setForeground(Color.white);
		passwordLabel.setBounds(26, 440, 200, 33);
		return passwordLabel;
	}
	public JTextField usernameField() {
		usernameField = new JTextField(20);
		usernameField.setBackground(Color.decode("#333333"));
		usernameField.setForeground(Color.white);
		usernameField.setBounds(26, 310, 307, 36);
		usernameField.setCaretColor(Color.WHITE);
		return usernameField;
	}
	public JTextField emailField() {
		emailField = new JTextField(20);
		emailField.setBackground(Color.decode("#333333"));
		emailField.setForeground(Color.white);
		emailField.setBounds(26, 390, 307, 36);
		emailField.setCaretColor(Color.WHITE);
		return emailField;
	}
	public JPasswordField passwordField() {
		passwordField = new JPasswordField(20);
		passwordField.setBackground(Color.decode("#333333"));
		passwordField.setForeground(Color.white);
		passwordField.setBounds(26, 470, 307, 36);
		passwordField.setCaretColor(Color.WHITE);
		return passwordField;
	}
	
	public JButton registerButton(UserDAO DAO) {
		registerButton = new JButton("Registrar-se");
		registerButton.setBounds(26,627,307,47);
		registerButton.setBackground(Color.decode("#68589D"));
		registerButton.setForeground(Color.white);

	    ActionListener listener = new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            User user = new User();
	            boolean isValid = true;

	            if (DAO.validateUsername(usernameField.getText())) {
	                user.setUsername(usernameField.getText());
	            } else {
	            	JOptionPane.showMessageDialog(null, "Nome inválido, tente novamente");
	            	isValid = false;
	            }

	            if (isValid && DAO.validateEmail(emailField.getText())) {
	                user.setEmail(emailField.getText());
	            } else {
	            	JOptionPane.showMessageDialog(null, "Email inválido, tente novamente");
	            	isValid = false;
	            }

	            PasswordUser passwordUser = new PasswordUser(user);

	            if (isValid && !DAO.validatePasswordMinimumLength(new String(passwordField.getPassword()))) {
	            	JOptionPane.showMessageDialog(null, "A senha deve conter pelo menos 8 caracteres.");
	                isValid = false;
	            }
	            if (isValid && !DAO.validatePasswordNumber(new String(passwordField.getPassword()))) {
	            	JOptionPane.showMessageDialog(null, "A senha deve conter pelo menos um número.");
	                isValid = false;
	            }
	            if (isValid && !DAO.validatePasswordSpecialChar(new String(passwordField.getPassword()))) {
	            	JOptionPane.showMessageDialog(null, "A senha deve conter pelo menos um caractere especial.");
	                isValid = false;
	            }
	            if (isValid && !DAO.validatePasswordUppercase(new String(passwordField.getPassword()))) {
	            	JOptionPane.showMessageDialog(null, "A senha deve conter pelo menos uma letra maiúscula.");
	                isValid = false;
	            }

	            if (isValid) {
	                passwordUser.setPassword(new String(passwordField.getPassword()));
	            }

	            user.setPasswordUser(passwordUser);

	            if (isValid) {
	                int insertFeedback = DAO.insertUser(user);
	                switch (insertFeedback) {
	                case 0:
	                	JOptionPane.showMessageDialog(null, "Cadastro completo com sucesso.");
	                	break;
	                case 1:
	                	JOptionPane.showMessageDialog(null, "Limite de usuários atingido. Não é possível cadastrar mais usuários.");
	                	break;
	                case 2:
	                	JOptionPane.showMessageDialog(null, "Username ou e-mail já em uso. Tente novamente.");
	                	break;
	                }
	            }
	        }
	    };

	    registerButton.addActionListener(listener);
	    return registerButton;
	}
	public JLabel redirectLogin(UserDAO DAO) {
		redirectLogin = new JLabel("Já tem uma conta?");
		redirectLogin.setForeground(Color.white);
		redirectLogin.setBounds(130, 700, 200, 33);
		
		MouseAdapter listener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton()==1) {
					loginInstance.loginScreen(DAO);
					registerScreen.setVisible(false);
				}
			}
		};
		redirectLogin.addMouseListener(listener);
		return redirectLogin;
	}

	
	public void registerScreen(UserDAO DAO) {
		registerScreen = new JFrame();
		registerScreen.setTitle("Gamix • Cadastro");
		registerScreen.setBounds(0, 0, 360, 800);
		registerScreen.setResizable(false);
		registerScreen.setLocationRelativeTo(null);
		registerScreen.setLayout(null);
		registerScreen.getContentPane().setBackground(Color.decode("#1C2034"));
		
		registerScreen.getContentPane().add(logoGamix());
		registerScreen.getContentPane().add(registerTitle());
		registerScreen.getContentPane().add(usernameLabel());
		registerScreen.getContentPane().add(usernameField());
		registerScreen.getContentPane().add(emailLabel());
		registerScreen.getContentPane().add(emailField());
		registerScreen.getContentPane().add(passwordLabel());
		registerScreen.getContentPane().add(passwordField());
		registerScreen.getContentPane().add(registerButton(DAO));
		registerScreen.getContentPane().add(redirectLogin(DAO));
	    		
		registerScreen.setVisible(true);
	}
}
