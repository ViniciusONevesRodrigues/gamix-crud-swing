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
    		ActionListener listener = new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				User loggedInUser = userDAO.login(usernameOrEmailField.getText(), new String(passwordField.getPassword()));
    				if (loggedInUser != null) {
    					
    				} else {
    					JOptionPane.showMessageDialog(loginUser.this, "Login falhou. Verifique suas credenciais.");
    				}
    			}
    		};
    		loginButton.addActionListener(listener);
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
    
    public class cadastroUser extends JFrame {
    	
    	UserDAO userDAO = new UserDAO();
    	private JFrame cadastroScreen;
    	private JLabel logoGamix, cadastroTitle, usernameLabel, emailLabel, passwordLabel;
    	private JTextField usernameField, emailField;
    	private JPasswordField passwordField;
    	private JButton cadastroButton;
    	
    	public JLabel logoGamix() {
    		ImageIcon imageIcon = new ImageIcon("./resources/android-chrome-150x150.png");
    		logoGamix = new JLabel(imageIcon);
    		logoGamix.setBounds(100, 50, imageIcon.getIconWidth(), imageIcon.getIconHeight());
    		return logoGamix;
    	}
    	public JLabel cadastroTitle() {
    		cadastroTitle = new JLabel("Criar uma conta");
    		cadastroTitle.setBounds(26, 200, 300, 50);
    		cadastroTitle.setForeground(Color.white);
    		cadastroTitle.setHorizontalAlignment(SwingConstants.CENTER);
    		int fontSize = 27;
    		cadastroTitle.setFont(new Font("Roboto", Font.BOLD, fontSize));
    		return cadastroTitle;
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
    		return usernameField;
    	}
    	public JTextField emailField() {
    		emailField = new JTextField(20);
    		emailField.setBackground(Color.decode("#333333"));
    		emailField.setForeground(Color.white);
    		emailField.setBounds(26, 390, 307, 36);
    		return emailField;
    	}
    	public JPasswordField passwordField() {
    		passwordField = new JPasswordField(20);
    		passwordField.setBackground(Color.decode("#333333"));
    		passwordField.setForeground(Color.white);
    		passwordField.setBounds(26, 470, 307, 36);
    		return passwordField;
    	}
    	
    	public JButton cadastroButton() {
    	    cadastroButton = new JButton("Registrar-se");
    	    cadastroButton.setBounds(26,627,307,47);
    	    cadastroButton.setBackground(Color.decode("#68589D"));
    	    cadastroButton.setForeground(Color.white);

    	    ActionListener listener = new ActionListener() {
    	        public void actionPerformed(ActionEvent e) {
    	            User user = new User();
    	            boolean isValid = true;

    	            if (UserDAO.validateUsername(usernameField.getText())) {
    	                user.setUsername(usernameField.getText());
    	            } else {
    	                JOptionPane.showMessageDialog(cadastroUser.this, "Nome inválido, tente novamente");
    	            }

    	            if (isValid && UserDAO.validateEmail(emailField.getText())) {
    	                user.setEmail(emailField.getText());
    	            } else {
    	                JOptionPane.showMessageDialog(cadastroUser.this, "Email inválido, tente novamente");
    	            }

    	            PasswordUser passwordUser = new PasswordUser(user);

    	            if (isValid && !UserDAO.validatePasswordMinimumLength(new String(passwordField.getPassword()))) {
    	                JOptionPane.showMessageDialog(cadastroUser.this, "A senha deve conter pelo menos 8 caracteres.");
    	                isValid = false;
    	            }
    	            if (isValid && !UserDAO.validatePasswordNumber(new String(passwordField.getPassword()))) {
    	                JOptionPane.showMessageDialog(cadastroUser.this, "A senha deve conter pelo menos um número.");
    	                isValid = false;
    	            }
    	            if (isValid && !UserDAO.validatePasswordSpecialChar(new String(passwordField.getPassword()))) {
    	                JOptionPane.showMessageDialog(cadastroUser.this, "A senha deve conter pelo menos um caractere especial.");
    	                isValid = false;
    	            }
    	            if (isValid && !UserDAO.validatePasswordUppercase(new String(passwordField.getPassword()))) {
    	                JOptionPane.showMessageDialog(cadastroUser.this, "A senha deve conter pelo menos uma letra maiúscula.");
    	                isValid = false;
    	            }

    	            if (isValid) {
    	                passwordUser.setPassword(new String(passwordField.getPassword()));
    	            }

    	            user.setPasswordUser(passwordUser);

    	            if (isValid) {
    	                int insertFeedback = userDAO.insertUser(user);
    	                switch (insertFeedback) {
    	                case 1:
    	                	JOptionPane.showMessageDialog(cadastroUser.this, "Limite de usuários atingido. Não é possível cadastrar mais usuários.");
    	                	break;
    	                case 2:
    	                	JOptionPane.showMessageDialog(cadastroUser.this, "Username ou e-mail já em uso. Tente novamente.");
    	                	break;
    	                }
    	            }
    	        }
    	    };

    	    cadastroButton.addActionListener(listener);
    	    return cadastroButton;
    	}

    	
    	public void cadastroScreen() {
    		cadastroScreen = new JFrame();
    		cadastroScreen.setTitle("Gamix • Cadastro");
    		cadastroScreen.setBounds(0, 0, 360, 800);
    		cadastroScreen.setResizable(false);
    		cadastroScreen.setLocationRelativeTo(null);
    		cadastroScreen.setLayout(null);
    		cadastroScreen.getContentPane().setBackground(Color.decode("#1C2034"));
    		
    		cadastroScreen.getContentPane().add(logoGamix());
    		cadastroScreen.getContentPane().add(cadastroTitle());
    		cadastroScreen.getContentPane().add(usernameLabel());
    		cadastroScreen.getContentPane().add(usernameField());
    	    cadastroScreen.getContentPane().add(emailLabel());
    	    cadastroScreen.getContentPane().add(emailField());
    	    cadastroScreen.getContentPane().add(passwordLabel());
    	    cadastroScreen.getContentPane().add(passwordField());
    	    cadastroScreen.getContentPane().add(cadastroButton());
    	    		
    		cadastroScreen.setVisible(true);
    	}
    }
    
    public static void main(String[] args) {
        Main mainInstance = new Main();
        
//        mainInstance.new loginUser().loginScreen();
        mainInstance.new cadastroUser().cadastroScreen();
    }
	
}
