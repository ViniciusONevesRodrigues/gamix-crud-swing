package org.gamix;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.gamix.DAO.UserDAO;

public class UserProfile {
	private JFrame uProfileScreen;
	private JLabel userImage, userName;
//	private JButton updateButton, deleteButton;
	
	
	public JLabel userImage() {
		ImageIcon image = new ImageIcon("./resources/user2.png");
		userImage = new JLabel(image);
		userImage.setBounds(120, 80, 100, 100);
		
		
		MouseAdapter listener = new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if (e.getButton()==1) {
					
				}
			}
		};
	
		userImage.addMouseListener(listener);
		return userImage;
	}
	

	public JLabel userName(UserDAO DAO) {
		if (DAO.getLogedInUser() != null) {
			String currentUser = DAO.getLogedInUser().getUsername();
			userName = new JLabel(currentUser);
			userName.setBounds(120, 200, 30, 30);
			userName.setFont(new Font("Roboto", Font.BOLD, 15));
			userName.setForeground(Color.WHITE);
			return userName;
		} else {
			userName = new JLabel("Desconhecido");
			userName.setBounds(120, 200, 200, 200);
			userName.setFont(new Font("Roboto", Font.BOLD, 15));
			userName.setForeground(Color.WHITE);
			return userName;
		}
	}
	
	public void uProfileScreen(UserDAO DAO) {
		uProfileScreen = new JFrame();
		uProfileScreen.setTitle("Gamix • Home");
		uProfileScreen.setBounds(0, 0, 360, 800);
		uProfileScreen.setLayout(null);
		uProfileScreen.setResizable(false);
		uProfileScreen.setLocationRelativeTo(null);
		uProfileScreen.getContentPane().setBackground(Color.decode("#1C2034"));;
		
		
		uProfileScreen.getContentPane().add(userImage());
		uProfileScreen.getContentPane().add(userName(DAO));
		
		uProfileScreen.setVisible(true);
	}
}
