package org.gamix;

import org.gamix.DAO.UserDAO;

public class Main {  
    public static void main(String[] args) {
    	UserDAO DAO = new UserDAO();
    	Register register = new Register();
    	Home home = new Home();
    	Update update = new Update();
    	Login login = new Login(register, home);
    	UserProfile profile = new UserProfile(update, login, home);
    	
    	register.setRegister(login);
    	home.setHome(login, profile);
    	update.getUpdate(profile);
  	
    	login.loginScreen(DAO);
    }	
}
