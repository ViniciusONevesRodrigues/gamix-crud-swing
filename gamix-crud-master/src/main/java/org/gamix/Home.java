package org.gamix;

import java.awt.*;
import java.awt.event.*;

import java.util.List;

import javax.swing.*;

import org.gamix.DAO.UserDAO;
import org.gamix.models.User;

public class Home {
	
	private Login loginInstance;
	private UserProfile uProfileInstance;
	private JFrame homeScreen;
	private JLabel mGlasses, iUser, logout; 
	private JTextField searchInput;
	private JButton searchAll, searchBy;
	private JToolBar nav;
	private JList<String> userList;
	private DefaultListModel<String> listModel;
	
	public void setHome(Login login, UserProfile profile) {
		this.loginInstance = login;
		this.uProfileInstance = profile;
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
					uProfileInstance.uProfileScreen(DAO);
					homeScreen.setVisible(false);
				}
			}
		};
		
		iUser.addMouseListener(Ulistener);
		
		MouseAdapter Llistener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton()==1) {
					loginInstance.loginScreen(DAO);
					homeScreen.setVisible(false);
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
	
	public JTextField searchInput() {
		searchInput = new JTextField("Procurar");
		searchInput.setBounds(26, 50, 300, 36);
		searchInput.setForeground(Color.WHITE);
		searchInput.setBackground(Color.decode("#333333"));
		searchInput.setCaretColor(Color.WHITE);
		
		MouseAdapter listener = new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if (e.getButton()==1) {
					searchInput.setText("");
				}
			}
		};
		searchInput.addMouseListener(listener);
		return searchInput;
	}
 
	public JButton searchAll(UserDAO DAO) {
		DAO.teste();
		searchAll = new JButton("Buscar Todos");
		searchAll.setBounds(26, 90, 150, 44);
		searchAll.setBackground(Color.decode("#68589D"));
		searchAll.setForeground(Color.WHITE);
		
		userList = new JList<>();
        listModel = new DefaultListModel<>();
        userList.setModel(listModel);
        userList.setBackground(Color.decode("#1C2034"));
        userList.setForeground(Color.WHITE);
        userList.setFont(new Font("Roboto", Font.BOLD, 14));
        userList.setToolTipText("html");
        
        JScrollPane scrollPane = new JScrollPane(userList);
        scrollPane.setBounds(26, 150, 300, 500);
        homeScreen.add(scrollPane);
        
        ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.clear();
				
				List<User> allUsers = DAO.findAllUsers();
				for (User user : allUsers) {
					String userInfo = 
						    "<html><hr>" +
						    	 "ID: " + user.getId() + "<br>" +
						    	 "Nome: " + user.getUsername() + "<br>" +
						    	 "Email: " + user.getEmail() + "<br>" +
						    "<hr></html>";
					
					listModel.addElement(userInfo);
				}
			}
		};
        
		searchAll.addActionListener(listener);
		return searchAll;
	}
	
	public JButton searchBy(UserDAO DAO) {
		searchBy = new JButton("Buscar");
		searchBy.setBounds(175, 90, 150, 44);
		searchBy.setBackground(Color.decode("#68589D"));
		searchBy.setForeground(Color.WHITE);
		
		userList = new JList<>();
        listModel = new DefaultListModel<>();
        userList.setModel(listModel);
        userList.setBackground(Color.decode("#1C2034"));
        userList.setForeground(Color.WHITE);
        userList.setFont(new Font("Roboto", Font.BOLD, 14));
        userList.setToolTipText("html");
        
        JScrollPane scrollPane = new JScrollPane(userList);
        scrollPane.setBounds(26, 150, 300, 500);
        homeScreen.add(scrollPane);
        
        ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.clear();
				
			
				List<User> allUsers;
				if (!searchInput.getText().contains("@")) {
					allUsers = DAO.findByUsername(searchInput.getText());
				} else {
					allUsers = DAO.findByEmail(searchInput.getText());
				}
				for (User user : allUsers) {
					String userInfo = 
						    "<html><hr>" +
						    	 "ID: " + user.getId() + "<br>" +
						    	 "Nome: " + user.getUsername() + "<br>" +
						    	 "Email: " + user.getEmail() + "<br>" +
						    "<hr></html>";
					
					listModel.addElement(userInfo);
				}
			}
		};
        
		searchBy.addActionListener(listener);
		return searchBy;
	}
	
	public void homeScreen(UserDAO DAO) {
		homeScreen = new JFrame();
		homeScreen.setTitle("Gamix • Home");
		homeScreen.setBounds( 0, 0, 360, 800);
		homeScreen.getContentPane().setBackground(Color.decode("#1C2034"));
		homeScreen.setLocationRelativeTo(null);
		homeScreen.setLayout(null);
		homeScreen.setResizable(false);
		
		homeScreen.getContentPane().add(searchInput());
		homeScreen.getContentPane().add(searchAll(DAO));
		homeScreen.getContentPane().add(searchBy(DAO));
		homeScreen.getContentPane().add(nav(DAO));
		
		homeScreen.setVisible(true);
	}
	
}
