package org.gamix.DAO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.gamix.models.User;

public class UserDAO {

    private User vector[] = new User[10];
    private int userCount = 0;
    private static int idCount = 1;
    private User logedUser;
    
    
    
    public User login(String usernameOrEmail, String password) {
        for (User user : vector) {
            if (user != null && (user.getUsername().equals(usernameOrEmail) || user.getEmail().equals(usernameOrEmail)) &&
                user.getPasswordUser().getPassword().equals(password)) {
            	logedUser = user;
                return user;
            }
        }
        return null; 
    }

    public void insertUser(User user) {
        if (!isDuplicateUsername(user.getUsername()) && !isDuplicateEmail(user.getEmail())) {
            if (userCount < vector.length) {
                user.setId(idCount++);
                vector[userCount++] = user;
                System.out.println("Usuário cadastrado com sucesso.");
            } else {
                System.out.println("Limite de usuários atingido. Não é possível cadastrar mais usuários.");
            }
        } else {
            System.out.println("Username ou e-mail já em uso. Tente novamente.");
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

    public void findAllUsers() {
    	boolean exist = false;
        for (User user : vector) {
            if (user != null) {
            	System.out.println("\n" + "--------------------------------------");
                System.out.println("ID: " + user.getId());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Icon: " + user.getIcon());
                System.out.println("Password: " + user.getPasswordUser().getPassword());
                System.out.println("--------------------------------------");
                exist = true;
            } 
        }
        if (!exist) {
        	System.out.println("\nNenhum usuário registrado");
        }
    }

    public void findByEmail(String email) {
    	boolean exist = false;
        for (User user : vector) {
            if (user != null && user.getEmail().equals(email)) {
            	System.out.println("\n" + "--------------------------------------");
                System.out.println("ID: " + user.getId());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Icon: " + user.getIcon());
                System.out.println("Password: " + user.getPasswordUser().getPassword());
                System.out.println("--------------------------------------");
                exist = true;
            } 
        }
        if (!exist) {
        	System.out.println("\nEsse usuário não existe");
        }
    }

    public void findByUsername(String username) {
    	boolean exist = false;
        for (User user : vector) {
            if (user != null && user.getUsername().equals(username)) {
            	System.out.println("\n" + "--------------------------------------");
                System.out.println("ID: " + user.getId());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Icon: " + user.getIcon());
                System.out.println("Password: " + user.getPasswordUser().getPassword());
                System.out.println("--------------------------------------");
                exist = true;
            }
        }
        if (!exist) {
        	System.out.println("\nEsse usuário não existe");
        }
    }

    public void updateUser(Integer id, User partialUserInput) {
    	boolean exist = false;
        for (User user : vector) {
            if (user != null && user.getId().equals(id)) {
                user.partialUserInput(partialUserInput);
                exist = true;
                break;
            }
        }
        if (exist == false) {
        	System.out.println("Não existe usuário com esse ID");
        }
    }

    public void deleteAccount(Integer id) {
    	boolean exist = false;
        for (int i = 0; i < userCount; i++) {
            if (vector[i] != null && vector[i].getId().equals(id)) {
                vector[i] = null;
                exist = true;
                System.out.println("Conta excluída com sucesso!");
                userCount--;
          
                for (int j = i; j < userCount; j++) {
                    vector[j] = vector[j + 1];
                }
                vector[userCount] = null; 
                break;
            }
        }
        if (exist == false) {
        	System.out.println("Não existe usuário com esse ID");
        }
    }
    
    public static boolean validateEmail(String email) {
    	final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    	final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean validateUrl(String icon) {
    	final String URL_PATTERN = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    	final Pattern pattern = Pattern.compile(URL_PATTERN);
    	Matcher matcher = pattern.matcher(icon);
    	return matcher.matches();
    }
    public static boolean validatePasswordUppercase(String password) {
    	final String UPPERCASE_PATTERN = (".*[A-Z].*");
    	final Pattern pattern = Pattern.compile(UPPERCASE_PATTERN);
    	Matcher matcher = pattern.matcher(password);
    	return matcher.matches();
    }
    public static boolean validatePasswordNumber(String password) {
    	final String NUMBER_PATTERN = (".*\\d.*");
    	final Pattern pattern = Pattern.compile(NUMBER_PATTERN);
    	Matcher matcher = pattern.matcher(password);
    	return matcher.matches();
    }
    public static boolean validatePasswordSpecialChar(String password) {
    	final String SPECIALCHAR_PATTERN = (".*[!@#$%^&*].*");
    	final Pattern pattern = Pattern.compile(SPECIALCHAR_PATTERN);
    	Matcher matcher = pattern.matcher(password);
    	return matcher.matches();
    }
    public static boolean validatePasswordMinimumLength(String password) {
    	final String MINIMUMLENGTH_PATTERN = ".{8,}";
    	final Pattern pattern = Pattern.compile(MINIMUMLENGTH_PATTERN);
    	Matcher matcher = pattern.matcher(password);
    	return matcher.matches();
    }
    public static boolean validateUsername(String username) {
    	final String USERNAME_PATTERN = "^[a-zA-Z0-9_]+$";
    	final Pattern pattern = Pattern.compile(USERNAME_PATTERN);
    	Matcher matcher = pattern.matcher(username);
    	return matcher.matches();
    }
    public static boolean validateIdInput(int id) {
        if (idCount -1 != 0 && id < idCount) {
            return true;
        } else {
            System.out.print("Não existe nenhum usuário com esse ID");
            System.out.print("\n");
            return false;
        }
    }
}


    
