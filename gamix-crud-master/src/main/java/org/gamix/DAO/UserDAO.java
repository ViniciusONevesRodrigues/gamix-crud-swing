package org.gamix.DAO;

import java.util.*;
import java.util.regex.*;

import org.gamix.models.User;

public class UserDAO {

    private User vector[] = new User[10];
    private int userCount = 0;
    private static int idCount = 1;
    private User logedInUser;
    
    
    public User login(String usernameOrEmail, String password) {
        for (User user : vector) {
            if (user != null && (user.getUsername().equals(usernameOrEmail) || user.getEmail().equals(usernameOrEmail)) &&
                user.getPasswordUser().getPassword().equals(password)) {
            	logedInUser = user;
                return user;
            }
        }
        return null; 
    }

    public int insertUser(User user) {
        if (!isDuplicateUsername(user.getUsername()) && !isDuplicateEmail(user.getEmail())) {
            if (userCount < vector.length) {
                user.setId(idCount++);
                vector[userCount++] = user;
                return 0;
            } else {
                return 1;
            }
        } else {
            return 2;
        }
    }

    public boolean isDuplicateUsername(String username) {
        for (int i = 0; i < userCount; i++) {
            if (vector[i] != null && vector[i].getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean isDuplicateEmail(String email) {
        for (int i = 0; i < userCount; i++) {
            if (vector[i] != null && vector[i].getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public List<User> findAllUsers() {
    	List<User> allUsers = new ArrayList<>();	
        for (User user : vector) {
            if (user != null) {
            	allUsers.add(user);
            } 
        }
        return allUsers;
    }

    public List<User> findByEmail(String email) {
    	List<User> allUsers = new ArrayList<>();
        for (User user : vector) {
            if (user != null && user.getEmail().contains(email)) {
            	allUsers.add(user);
            } 
        }
        return allUsers;
    }

    public List<User> findByUsername(String username) {
    	List<User> allUsers = new ArrayList<>();
        for (User user : vector) {
            if (user != null && user.getUsername().contains(username)) {
            	allUsers.add(user);
            }
        }
        return allUsers;
    }

    public void updateUser(User partialUserInput) {
        for (User user : vector) {
            if (user.equals(logedInUser)) {
                user.partialUserInput(partialUserInput);
                break;
            }
        }
    }

    public void deleteAccount() {
        for (int i = 0; i < userCount; i++) {
            if (vector[i] != null && vector[i] == logedInUser) {
                vector[i] = null;
                logedInUser = null;
                userCount--;
          
                for (int j = i; j < userCount; j++) {
                    vector[j] = vector[j + 1];
                }
                vector[userCount] = null; 
                break;
            }
        }
    }
    
    public boolean validateEmail(String email) {
    	final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    	final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public boolean validateDiretorio(String icon) {
    	final String URL_PATTERN = "^.*\\.(jpg|jpeg|png|gif|bmp)$";
    	final Pattern pattern = Pattern.compile(URL_PATTERN);
    	Matcher matcher = pattern.matcher(icon);
    	return matcher.matches();
    }
    public boolean validatePasswordUppercase(String password) {
    	final String UPPERCASE_PATTERN = (".*[A-Z].*");
    	final Pattern pattern = Pattern.compile(UPPERCASE_PATTERN);
    	Matcher matcher = pattern.matcher(password);
    	return matcher.matches();
    }
    public boolean validatePasswordNumber(String password) {
    	final String NUMBER_PATTERN = (".*\\d.*");
    	final Pattern pattern = Pattern.compile(NUMBER_PATTERN);
    	Matcher matcher = pattern.matcher(password);
    	return matcher.matches();
    }
    public boolean validatePasswordSpecialChar(String password) {
    	final String SPECIALCHAR_PATTERN = (".*[!@#$%^&*].*");
    	final Pattern pattern = Pattern.compile(SPECIALCHAR_PATTERN);
    	Matcher matcher = pattern.matcher(password);
    	return matcher.matches();
    }
    public boolean validatePasswordMinimumLength(String password) {
    	final String MINIMUMLENGTH_PATTERN = ".{8,}";
    	final Pattern pattern = Pattern.compile(MINIMUMLENGTH_PATTERN);
    	Matcher matcher = pattern.matcher(password);
    	return matcher.matches();
    }
    public boolean validateUsername(String username) {
    	final String USERNAME_PATTERN = "^[a-zA-Z0-9_ ]+$";
    	final Pattern pattern = Pattern.compile(USERNAME_PATTERN);
    	Matcher matcher = pattern.matcher(username);
    	return matcher.matches();
    }
    public boolean validateIdInput(int id) {
        if (idCount -1 != 0 && id < idCount) {
            return true;
        } else {
            System.out.print("Não existe nenhum usuário com esse ID");
            System.out.print("\n");
            return false;
        }
    }
    public User getLogedInUser() {
    	return this.logedInUser;
    }
}


    
