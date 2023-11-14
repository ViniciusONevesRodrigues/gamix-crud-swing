package org.gamix;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.gamix.DAO.UserDAO;
import org.gamix.models.User;

public class Update {
	private JFrame uScreen;
	private UserProfile uProfileInstance;
	private JLabel usernameLabel, emailLabel, updateTitle, updateSubTitle;
	private JTextField usernameField, emailField;
	private JButton updateButton, backButton;
	
	public void getUpdate(UserProfile uProfileInstance) {
		this.uProfileInstance = uProfileInstance;
	}
	
	public JLabel updateTitle() {
		updateTitle = new JLabel("Atualize suas informações");
		updateTitle.setForeground(Color.white);
		updateTitle.setHorizontalAlignment(SwingConstants.CENTER);
		int fontSize = 24;
		updateTitle.setFont(new Font("Roboto", Font.BOLD, fontSize));
		updateTitle.setBounds(26, 100, 300, 50);
		return updateTitle;
	}
	public JLabel updateSubTitle() {
		updateSubTitle = new JLabel("(Se não quiser fazer alterações, deixe em branco)");
		updateSubTitle.setForeground(Color.white);
		updateSubTitle.setHorizontalAlignment(SwingConstants.CENTER);
		int fontSize = 12;
		updateSubTitle.setFont(new Font("Roboto", Font.BOLD, fontSize));
		updateSubTitle.setBounds(26, 130, 300, 50);
		return updateSubTitle;
	}
	public JLabel usernameLabel() {
		usernameLabel = new JLabel("Nome do usuário");
		usernameLabel.setForeground(Color.white);
		usernameLabel.setBounds(26, 180, 300, 33);
		return usernameLabel;
	}
	public JLabel emailLabel() {
		emailLabel = new JLabel("Email");
		emailLabel.setForeground(Color.white);
		emailLabel.setBounds(26, 260, 300, 33);
		return emailLabel;
	}
	public JTextField usernameField() {
		usernameField = new JTextField(20);
		usernameField.setBackground(Color.decode("#333333"));
		usernameField.setForeground(Color.white);
		usernameField.setBounds(26, 210, 307, 36);
		usernameField.setCaretColor(Color.WHITE);
		return usernameField;
	}
	public JTextField emailField() {
		emailField = new JTextField(20);
		emailField.setBackground(Color.decode("#333333"));
		emailField.setForeground(Color.white);
		emailField.setBounds(26, 290, 307, 36);
		emailField.setCaretColor(Color.WHITE);
		return emailField;
	}
	public JButton updateButton(UserDAO DAO) {
		User partialUserInput = new User();
		updateButton = new JButton("Atualizar");
		updateButton.setBounds(26, 530, 150, 44);
		updateButton.setBackground(Color.decode("#68589D"));
		updateButton.setForeground(Color.WHITE);
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (usernameField.getText() != null &&  !usernameField.getText().equals("")) {
						if (DAO.validateUsername(usernameField.getText())) {
							if (!DAO.isDuplicateUsername(usernameField.getText())) {
								partialUserInput.setUsername(usernameField.getText());
							} else {
								JOptionPane.showMessageDialog(null, "Atualização falhou. Nome já em uso.");
							}
						}  else {
							JOptionPane.showMessageDialog(null, "Atualização falhou. Nome inválido.");
						}
					}
				if (emailField.getText() != null && !emailField.getText().equals("")) {
					if (DAO.validateUsername(emailField.getText())) {
						if (!DAO.isDuplicateEmail(emailField.getText())) {
							partialUserInput.setEmail(emailField.getText());
						} else {
							JOptionPane.showMessageDialog(null, "Atualização falhou. Email já em uso.");
						}
					}  else {
						JOptionPane.showMessageDialog(null, "Atualização falhou. Email inválido.");
					}
				}

				DAO.updateUser(partialUserInput);
			}	
		};
		
		updateButton.addActionListener(listener);
		return updateButton;
	}
	public JButton backButton(UserDAO DAO) {
		backButton = new JButton("Cancelar");
		backButton.setBounds(175, 530, 150, 44);
		backButton.setBackground(Color.decode("#68589D"));
		backButton.setForeground(Color.WHITE);
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uProfileInstance.uProfileScreen(DAO);
				uScreen.setVisible(false);
			}
		};
		
		backButton.addActionListener(listener);
		return backButton;
	}

	public void uScreen(UserDAO DAO) {
		uScreen = new JFrame();
		uScreen.setTitle("Gamix • Update");
		uScreen.setBounds(0, 0, 360, 800);
		uScreen.setResizable(false);
		uScreen.setLayout(null);
		uScreen.setLocationRelativeTo(null);
		uScreen.getContentPane().setBackground(Color.decode("#1C2034"));
		
		uScreen.getContentPane().add(updateTitle());
		uScreen.getContentPane().add(updateSubTitle());
		uScreen.getContentPane().add(usernameLabel());
		uScreen.getContentPane().add(emailLabel());
		uScreen.getContentPane().add(usernameField());
		uScreen.getContentPane().add(emailField());
		uScreen.getContentPane().add(updateButton(DAO));
		uScreen.getContentPane().add(backButton(DAO));
		
		
		uScreen.setVisible(true);
	}
}
