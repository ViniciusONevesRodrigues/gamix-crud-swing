package org.gamix;

import java.util.Scanner;

import org.gamix.DAO.UserDAO;
import org.gamix.models.PasswordUser;
import org.gamix.models.User;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        Scanner scanner = new Scanner(System.in);

        
        while (true) {
            int choice = showMenu(scanner);

            if (choice == 1) {
                User loggedInUser = login(scanner, userDAO);
                if (loggedInUser != null) {
                    System.out.println("Bem-vindo(a), " + loggedInUser.getUsername() + "!");
                }
            } else if (choice == 2) {
                User newUser = registerUser(scanner);
                userDAO.insertUser(newUser);
            } else if (choice == 3) {
                userDAO.findAllUsers();
            } else if (choice == 4) {
                findUserByUsername(scanner, userDAO);
            } else if (choice == 5) {
                findUserByEmail(scanner, userDAO);
            } else if (choice == 6) {
            	updateUser(scanner, userDAO);
            } else if (choice == 7) {
                deleteAccount(scanner, userDAO);
            } else if (choice == 8) {
                System.exit(0);
            } else {
                System.out.println("Escolha inválida.");
            }
        }
    }

    public static int showMenu(Scanner scanner) {
        int res;
        
        while (true) {
            System.out.println("\nSelecione a opção desejada: ");
            System.out.println("[1] Login");
            System.out.println("[2] Cadastro");
            System.out.println("[3] Mostrar todos os usuários");
            System.out.println("[4] Encontrar usuário por nome de usuário");
            System.out.println("[5] Encontrar usuário por e-mail");
            System.out.println("[6] Mudar informações de um usuário");
            System.out.println("[7] Excluir conta de usuário");
            System.out.println("[8] Sair");
            
            if (scanner.hasNextInt()) {
                res = scanner.nextInt();
                if (res >= 1 && res <= 8) {
                    break;  
                } else {
                    System.out.println("\n" + "Opção inválida. Digite um número entre 1 e 8.");
                }
            } else {
                System.out.println("\n" + "Entrada inválida. Digite um número entre 1 e 8.");
                scanner.next();  
            }
        }
        return res;
    }


    public static User login(Scanner scanner, UserDAO userDAO) {
        System.out.print("\nInforme o nome de usuário ou e-mail: ");
        String usernameOrEmail = scanner.next();
        System.out.print("Informe a senha: ");
        String password = scanner.next();
        User loggedInUser = userDAO.login(usernameOrEmail, password);
        if (loggedInUser == null) {
            System.out.println("Login falhou. Verifique suas credenciais.");
        }
        return loggedInUser;
    }

    public static User registerUser(Scanner scanner) {
        User user = new User();
        
        scanner.nextLine();
        while (true) {
	        System.out.print("\nInforme o nome de usuário: ");
	        String username = scanner.nextLine();
	        if (UserDAO.validateUsername(username)) {
	        	user.setUsername(username);
	        	break;
	        } else {
	        	System.out.println("Nome inválido, tente novamente");
	        }
        }
        
        while (true) {
	        System.out.print("Informe o e-mail: ");
	        String email = scanner.nextLine();
	        if (UserDAO.validateEmail(email)) {
	        	user.setEmail(email);
	        	break;
	        } else {
	        	System.out.println("Email inválido, tente novamente");
	        }
        }
        
        while (true) {
        	System.out.print("Novo icon (ou pressione ENTER para informar depois): ");
            String icon = scanner.nextLine();
            if (!icon.isEmpty()) {
            	if (UserDAO.validateUrl(icon)) {
            		user.setIcon(icon);
            		break;
            	} else {
            		System.out.println("URL inválida, tente novamente");
            	}
            } else {
            	break;
            }
        }

        PasswordUser passwordUser = new PasswordUser(user);
        
        while (true) {
        	System.out.print("Informe a senha: ");
        	String password = scanner.nextLine();
	        boolean isValid = true;
	        
	        if (!UserDAO.validatePasswordMinimumLength(password)) {
	        	System.out.println("A senha deve conter pelo menos 8 caracteres.");
	        	isValid = false;
	        }
	        if (!UserDAO.validatePasswordNumber(password)) {
	            System.out.println("A senha deve conter pelo menos um número.");
	            isValid = false;
	        }
	        if (!UserDAO.validatePasswordSpecialChar(password)) {
	            System.out.println("A senha deve conter pelo menos um caractere especial.");
	            isValid = false;
	        }
	        if (!UserDAO.validatePasswordUppercase(password)) {
	            System.out.println("A senha deve conter pelo menos uma letra maiúscula.");
	            isValid = false;
	        }
	        if (isValid) {
	        	passwordUser.setPassword(password);
	        	break;
	        }
        }

        user.setPasswordUser(passwordUser); 
        
        return user;
    }


    public static void findUserByUsername(Scanner scanner, UserDAO userDAO) {
        System.out.print("\nInforme o nome de usuário a ser encontrado: ");
        String username = scanner.next();
        userDAO.findByUsername(username);
    }

    public static void findUserByEmail(Scanner scanner, UserDAO userDAO) {
        System.out.print("\nInforme o e-mail a ser encontrado: ");
        String email = scanner.next();
        userDAO.findByEmail(email);
    }
    public static void updateUser(Scanner scanner, UserDAO userDAO) {
    	User partialUserInput = new User();
    	System.out.print("\nInforme o ID do usuário: ");
        Integer idParameter = scanner.nextInt();
        scanner.nextLine();
        
        if (UserDAO.validateIdInput(idParameter)) {
	        while (true) {
		        System.out.print("Novo username (ou pressione ENTER para manter este campo): ");
		        String usernameInput = scanner.nextLine();
		        if (!usernameInput.isEmpty()) {
		        	if (UserDAO.validateUsername(usernameInput)) {
		        		partialUserInput.setUsername(usernameInput);
		        		break;
		        	} else {
			        	System.out.println("Nome inválido, tente novamente");
		        	}
		        }
		        break;
	       }
	       
	        
	        while (true) {
		        System.out.print("Novo email (ou pressione ENTER para manter este campo): ");
		        String emailInput = scanner.nextLine();
		        if (!emailInput.isEmpty()) {
		        	if (UserDAO.validateEmail(emailInput)) {
		        		partialUserInput.setEmail(emailInput);
		        		break;
		        	} else {
		        		System.out.println("Email inválido, tente novamente");
		        	}
		        }
		        break;
	        }
	        
	        while (true) {
		        System.out.print("Novo icon (ou pressione ENTER para manter este campo): ");
		        String iconInput = scanner.nextLine();
		        if (!iconInput.isEmpty()) {
		        	if (UserDAO.validateUrl(iconInput)) {
		        		partialUserInput.setIcon(iconInput);
		        		break;
		        	} else {
		        		System.out.println("URL inválida, tente novamente");
		        	}
		        } else {
		        	break;
		        }
	        }
	        
	        
	        userDAO.updateUser(idParameter, partialUserInput);  
	    }
    }

    public static void deleteAccount(Scanner scanner, UserDAO userDAO) {
        System.out.print("\nInforme o ID da conta a ser excluída: ");
        int idToDelete = scanner.nextInt();
        userDAO.deleteAccount(idToDelete);
    }
    
}
