package org.gamix;

import org.gamix.DAO.UserDAO;

public class Main {  
    public static void main(String[] args) {
    	UserDAO DAO = new UserDAO();
	    Register register = new Register();
    	Home home = new Home();
    	UserProfile profile = new UserProfile();
    	Login login = new Login(register, home);
    	
    	register.setRegister(login);
    	home.setHome(login, profile);
  	
//    	login.loginScreen(DAO);
    	home.homeScreen(DAO);
    }	
}
