package org.gamix;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.gamix.DAO.UserDAO;
import org.gamix.models.User;

import java.io.*;

public class Update {
	String imagePath, bgPath;
	private JFrame uScreen;
	private UserProfile uProfileInstance;
	private JLabel usernameLabel, emailLabel, iconLabel, bgLabel, updateTitle, updateSubTitle;
	private JTextField usernameField, emailField;
	private JButton updateButton, backButton, iconButton, bgButton;
	
	public void getUpdate(UserProfile uProfileInstance) {
		this.uProfileInstance = uProfileInstance;
	}
	
	public JLabel updateTitle() {
		updateTitle = new JLabel("Atualize suas informações");
		updateTitle.setForeground(Color.white);
		updateTitle.setHorizontalAlignment(SwingConstants.CENTER);
		int fontSize = 20;
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
	public JLabel iconLabel() {
		iconLabel = new JLabel("Foto de Perfil");
		iconLabel.setForeground(Color.WHITE);
		iconLabel.setBounds(26, 340, 400, 33);
		return iconLabel;
	}
	public JLabel bgLabel() {
		bgLabel = new JLabel("Foto de Fundo");
		bgLabel.setForeground(Color.WHITE);
		bgLabel.setBounds(26, 420, 400, 33);
		return bgLabel;
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
				boolean isValid = true;
				if (usernameField.getText() != null && !usernameField.getText().equals("")) {
					if (DAO.validateUsername(usernameField.getText())) {
						if (!DAO.isDuplicateUsername(usernameField.getText())) {
							partialUserInput.setUsername(usernameField.getText());
						} else {
							JOptionPane.showMessageDialog(null, "Atualização falhou. Nome já em uso.");
							isValid = false;
						}
					} else {
						JOptionPane.showMessageDialog(null, "Atualização falhou. Nome inválido.");
						isValid = false;
					}
				}
				if (emailField.getText() != null && !emailField.getText().equals("")) {
					if (DAO.validateEmail(emailField.getText())) {
						if (!DAO.isDuplicateEmail(emailField.getText())) {
							partialUserInput.setEmail(emailField.getText());
						} else {
							JOptionPane.showMessageDialog(null, "Atualização falhou. Email já em uso.");
							isValid = false;
						}
					}  else {
						JOptionPane.showMessageDialog(null, "Atualização falhou. Email inválido.");
						isValid = false;
					}
				}
				if (imagePath != null) {
					partialUserInput.setIcon(imagePath);
				}
				
				if (bgPath != null) {
					partialUserInput.setBg(bgPath);
				}
				
				if (isValid) {
					DAO.updateUser(partialUserInput);
					uProfileInstance.uProfileScreen(DAO);
					uScreen.setVisible(false);
				}
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
	public JButton iconButton() {
		iconButton = new JButton("Selecione uma imagem");
		iconButton.setBounds(26, 370, 200, 44);
		iconButton.setBackground(Color.decode("#68589D"));
		iconButton.setForeground(Color.WHITE);
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
				filechooser.setDialogTitle("Escolha uma imagem");
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de Imagem", "jpg", "jpeg", "png");
				filechooser.setFileFilter(filter);
				
				int userSeletion = filechooser.showOpenDialog(null);
				
				if (userSeletion == JFileChooser.APPROVE_OPTION) {
					File selectedfile = filechooser.getSelectedFile();
					
					String fileName = selectedfile.getName();
					String fileExtetion = fileName.substring(fileName.lastIndexOf(".") + 1);
					
					if (fileExtetion.equals("jpg") || fileExtetion.equals("jpeg") || fileExtetion.equals("png")) {
						imagePath = selectedfile.getAbsolutePath();
						iconButton.setText(fileName);
					} else {
						JOptionPane.showMessageDialog(null, "Selecione um arquivo de imagem válido (jpg, jpeg ou png).");
					}			
				}
			}
		};
		
		iconButton.addActionListener(listener);
		return iconButton;
	}
	
	public JButton bgButton() {
		bgButton = new JButton("Selecione uma imagem");
		bgButton.setBounds(26, 450, 200, 44);
		bgButton.setBackground(Color.decode("#68589D"));
		bgButton.setForeground(Color.WHITE);
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
				filechooser.setDialogTitle("Escolha uma imagem");
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de Imagem", "jpg", "jpeg", "png");
				filechooser.setFileFilter(filter);
			
				
				int userSeletion = filechooser.showOpenDialog(null);
				
				if (userSeletion == JFileChooser.APPROVE_OPTION) {
					File selectedfile = filechooser.getSelectedFile();
					
					String fileName = selectedfile.getName();
	                String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
	                
	                if ("jpg".equals(fileExtension) || "jpeg".equals(fileExtension) || "png".equals(fileExtension) ) {
	                    bgPath = selectedfile.getAbsolutePath();
	                    bgButton.setText(fileName);
	                } else {
	                    JOptionPane.showMessageDialog(null, "Selecione um arquivo de imagem válido (jpg, jpeg ou png).");
	                }
					
					bgPath = selectedfile.getAbsolutePath();
				} 
			}
		};
		
		bgButton.addActionListener(listener);
		return bgButton;
	}

	public void uScreen(UserDAO DAO) {
		uScreen = new JFrame();
		uScreen.setTitle("Gamix • Update");
		uScreen.setBounds(0, 0, 360, 800);
		uScreen.setResizable(false);
		uScreen.setLayout(null);
		uScreen.setLocationRelativeTo(null);
		uScreen.getContentPane().setBackground(Color.decode("#1C2034"));
		Image logo = Toolkit.getDefaultToolkit().getImage("./resources/LogoIcon.png");
		uScreen.setIconImage(logo);
		
		uScreen.getContentPane().add(updateTitle());
		uScreen.getContentPane().add(updateSubTitle());
		uScreen.getContentPane().add(usernameLabel());
		uScreen.getContentPane().add(emailLabel());
		uScreen.getContentPane().add(usernameField());
		uScreen.getContentPane().add(emailField());
		uScreen.getContentPane().add(updateButton(DAO));
		uScreen.getContentPane().add(backButton(DAO));
		uScreen.getContentPane().add(iconButton());
		uScreen.getContentPane().add(bgButton());
		uScreen.getContentPane().add(iconLabel());
		uScreen.getContentPane().add(bgLabel());
		
		
		uScreen.setVisible(true);
	}
}
