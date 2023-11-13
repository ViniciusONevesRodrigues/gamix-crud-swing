package org.gamix;

import org.gamix.DAO.UserDAO;

public class Main {  
    public static void main(String[] args) {
    	UserDAO DAO = new UserDAO();
//    	Register register = new Register();
    	Home home = new Home();
//    	Login login = new Login(register, home);
//    	
//    	register.setLogin(login);
//    	
//    	login.loginScreen(DAO);
    	home.homeScreen(DAO);
    }	
}
