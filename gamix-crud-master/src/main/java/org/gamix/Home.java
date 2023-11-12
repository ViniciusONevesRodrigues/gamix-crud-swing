package org.gamix;

import java.awt.*;

import javax.swing.*;



public class Home {
	
	private JFrame homeScreen;
	
	public void homeScreen() {
		homeScreen = new JFrame();
		homeScreen.setTitle("Gamix • Home");
		homeScreen.setBounds( 0, 0, 360, 800);
		homeScreen.getContentPane().setBackground(Color.decode("#1C2034"));
		homeScreen.setLocationRelativeTo(null);
		homeScreen.setLayout(null);
		homeScreen.setResizable(false);
		
		homeScreen.setVisible(true);
	}
	
}
