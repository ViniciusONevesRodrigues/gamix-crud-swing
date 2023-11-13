package org.gamix;

import java.awt.*;

import javax.swing.*;

public class UserProfile {
	private JFrame uProfileScreen;
	
	public void uProfileScreen() {
		uProfileScreen = new JFrame();
		uProfileScreen.setTitle("Gamix • Home");
		uProfileScreen.setBounds(0, 0, 360, 800);
		uProfileScreen.setLayout(null);
		uProfileScreen.setResizable(false);
		uProfileScreen.setLocationRelativeTo(null);
		uProfileScreen.getContentPane().setBackground(Color.decode("#1C2034"));;
		
		
		uProfileScreen.setVisible(true);
	}
}
