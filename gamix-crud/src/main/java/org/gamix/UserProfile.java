package org.gamix;

import java.awt.*;
import java.awt.event.*;


import javax.swing.*;

import org.gamix.DAO.UserDAO;

public class UserProfile {
	
	private Update uInstance;
	private Login loginInstance;
	private Home homeInstance;
	private JToolBar nav;
	private JFrame uProfileScreen;
	private JLabel userImage, userBG, userName, mGlasses, iUser, logout;
	private JButton updateButton, deleteButton, fixbtn;
	
	public UserProfile(Update uInstance, Login loginInstance, Home homeInstance) {
		this.uInstance = uInstance;
		this.loginInstance = loginInstance;
		this.homeInstance = homeInstance;
	}
	
	public ImageIcon resizeIcon(String imagePath, int width, int height) {
	    ImageIcon originalIcon = new ImageIcon(imagePath);
	    Image image = originalIcon.getImage();
	    Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
	    return new ImageIcon(newimg);
	}
	
	public JToolBar nav(UserDAO DAO) {
		nav = new JToolBar();
		mGlasses = new JLabel(resizeIcon("./resources/glass.png", 24, 24));
		mGlasses.setBounds(140, 715, 0, 0);
		iUser = new JLabel(resizeIcon("./resources/user.png", 24, 24));
		iUser.setBounds(120, 715, 0, 0);
		logout = new JLabel(resizeIcon("./resources/logout.png", 24, 24));
		logout.setBounds(120, 715, 0, 0);
		nav.setBounds(0, 715, 360, 50);
		nav.setBackground(Color.decode("#68589D"));
		nav.setFloatable(false);
		
		MouseAdapter Ulistener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton()==1) {
					uProfileScreen.setVisible(false);
					homeInstance.homeScreen(DAO);
				}
			}
		};
		
		mGlasses.addMouseListener(Ulistener);
		
		MouseAdapter Llistener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton()==1) {
					uProfileScreen.setVisible(false);
					loginInstance.loginScreen(DAO);
				}
			}
		};
		
		logout.addMouseListener(Llistener);
		
		nav.add(Box.createRigidArea(new Dimension(20, 0)));
		nav.add(mGlasses);
		nav.add(Box.createRigidArea(new Dimension(120, 0)));
		nav.add(iUser);
		nav.add(Box.createRigidArea(new Dimension(100, 0)));
		nav.add(logout);
		
		return nav;
	}
	public JLabel userImage(UserDAO DAO)  {
		if (DAO.getLogedInUser().getIcon() == null) {
			userImage = new JLabel(resizeIcon("./resources/userProfile.png", 100, 100));
		} else {
			userImage = new JLabel(resizeIcon(DAO.getLogedInUser().getIcon(), 100, 100));
		}
		userImage.setBounds(120, 120, 100, 100);
		userImage.setHorizontalAlignment(SwingConstants.CENTER);
			
		MouseAdapter listener = new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if (e.getButton()==1) {
					uInstance.uScreen(DAO);
					uProfileScreen.setVisible(false);
				}
			}
		};
	
		userImage.addMouseListener(listener);
		return userImage;
	}
	
	public JLabel userBG(UserDAO DAO) {
		if (DAO.getLogedInUser().getBg() == null) {
			userBG = new JLabel(resizeIcon("./resources/UserBg.jpg", 400, 200));
		} else {
			userBG = new JLabel(resizeIcon(DAO.getLogedInUser().getBg(), 400, 200));
		}
		
		userBG.setBounds(0, 0, 400, 200);
		
		MouseAdapter listener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton()==1) {
					uInstance.uScreen(DAO);
					uProfileScreen.setVisible(false);
				}
			}
		};
		
		userBG.addMouseListener(listener);
		return userBG;
	}
	

	public JLabel userName(UserDAO DAO) {
		if (DAO.getLogedInUser() != null) {
			String currentUser = DAO.getLogedInUser().getUsername();
			userName = new JLabel(currentUser);
			userName.setBounds(70, 220, 200, 30);
			userName.setFont(new Font("Roboto", Font.BOLD, 15));
			userName.setForeground(Color.WHITE);
			userName.setHorizontalAlignment(SwingConstants.CENTER);
			return userName;
		} else {
			userName = new JLabel("Guest");
			userName.setBounds(75, 220, 200, 30);
			userName.setFont(new Font("Roboto", Font.BOLD, 15));
			userName.setForeground(Color.WHITE);
			userName.setHorizontalAlignment(SwingConstants.CENTER);
			return userName;
		}
	}
	public JButton fixbtn() {
		fixbtn = new JButton(".");
		return fixbtn;
	}
	public JButton updateButton(UserDAO DAO) {
		updateButton = new JButton("Atualizar Informações");
		updateButton.setBounds(70, 300, 200, 70);
		updateButton.setBackground(Color.decode("#68589D"));
		updateButton.setForeground(Color.WHITE);
		updateButton.setHorizontalAlignment(SwingConstants.CENTER);
				
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uInstance.uScreen(DAO);
				uProfileScreen.setVisible(false);
			}
		};
		
		updateButton.addActionListener(listener);
		return updateButton;
	}
	public JButton deleteButton(UserDAO DAO) {
		deleteButton = new JButton("Deletar a conta");
		deleteButton.setBounds(70, 400, 200, 70);
		deleteButton.setBackground(Color.decode("#68589D"));
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resultado = JOptionPane.showConfirmDialog(deleteButton, "Deseja mesmo deletar a conta?","Confirmação", JOptionPane.YES_NO_OPTION);
				if (resultado == JOptionPane.YES_OPTION) {
					DAO.deleteAccount();
					loginInstance.loginScreen(DAO);
					uProfileScreen.setVisible(false);
				}
			}
		};
		
		deleteButton.addActionListener(listener);
		return deleteButton;
	}
	
	public void uProfileScreen(UserDAO DAO) {
		uProfileScreen = new JFrame();
		uProfileScreen.setTitle("Gamix • Profile");
		uProfileScreen.setBounds(0, 0, 360, 800);
		uProfileScreen.setLayout(null);
		uProfileScreen.setResizable(false);
		uProfileScreen.setLocationRelativeTo(null);
		uProfileScreen.getContentPane().setBackground(Color.decode("#1C2034"));
		Image logo = Toolkit.getDefaultToolkit().getImage("./resources/LogoIcon.png");
		uProfileScreen.setIconImage(logo);
		
		uProfileScreen.getContentPane().add(nav(DAO));
		uProfileScreen.getContentPane().add(userImage(DAO));
		uProfileScreen.getContentPane().add(userBG(DAO));
		uProfileScreen.getContentPane().add(userName(DAO));
		uProfileScreen.getContentPane().add(fixbtn());
		uProfileScreen.getContentPane().add(updateButton(DAO));
		uProfileScreen.getContentPane().add(deleteButton(DAO));
		
		uProfileScreen.setVisible(true);
	}
}