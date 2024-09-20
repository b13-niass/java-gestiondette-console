package com.example.odc.views;

import com.example.odc.config.ApplicationContextProvider;
import com.example.odc.entities.User;
import com.example.odc.enums.Role;
import com.example.odc.services.UserService;
import com.example.odc.validators.CommonValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.Console;
import java.util.Scanner;

@Component
public class LoginView {

    private Scanner scanner;
    private boolean loggedIn;
    private UserService userService;
    private User authUser = null;
    private Console console;

    public LoginView(@Qualifier("userServiceImp") UserService userService) {
        scanner = new Scanner(System.in);
        this.userService = userService;
        this.console = System.console();
    }

    public void displayLoginScreen() {
        System.out.println("\n************************************");
        System.out.println("          Bienvenue dans l'App      ");
        System.out.println("************************************");
        System.out.println("         Veuillez vous connecter      ");
        System.out.println("************************************");

        while (!loggedIn) {
            String username = prompt("Nom d'utilisateur : ");
            String password = promptPassword("Mot de passe : ");

            handleLogin(username, password);
        }
        this.authUser = User.builder().role(Role.BOUTIQUIER).build();
        // Handle role-based logic after successful login
        if (this.authUser != null) {
            switch (this.authUser.getRole()) {
                case Role.BOUTIQUIER:
                    BoutiquierView boutiquierView = ApplicationContextProvider.getBean(BoutiquierView.class);
                    boutiquierView.displayMenu();
                    break;
                case Role.CLIENT:
                    ClientView clientView = new ClientView();
                    clientView.displayMenu();
                    break;
                case Role.ADMIN:
                    System.out.println("Je suis Admin");
                    break;
                default:
                    System.out.println("Je suis autre chose");
                    break;
            }
        }
    }

    public String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public String promptPassword(String message) {
        if (console != null) {
            char[] passwordArray = console.readPassword(message);
            return new String(passwordArray).trim();
        } else {
            // Fall back to regular input if console is not available (e.g., in some IDEs)
            System.out.print(message);
            return scanner.nextLine().trim();
        }
    }

    public void handleLogin(String username, String password) {
        this.loggedIn = validateCredentials(username, password);
//        clearConsole();
        if (!this.loggedIn) {
            System.out.println("\nNom d'utilisateur ou mot de passe incorrect.");
        }
    }

    public boolean validateCredentials(String username, String password) {
        username = CommonValidator.filterField(username);
        password = CommonValidator.filterField(password);

        if (CommonValidator.isEmptyField(username) || CommonValidator.isEmptyField(password)) {
            System.out.println("\n Le username et le password sont obligatoire");
            return false;
        }
        System.out.println(username);
        System.out.println(password);
        System.out.println(this.userService.authenticate(username, password));
        // Authenticate using the service
        if (this.userService.authenticate(username, password)) {
            this.authUser = this.userService.findByLogin(username);
            return true;
        } else {
            return false;
        }
    }

    public static void clearConsole() {
        // Clear console method, depending on the environment
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
