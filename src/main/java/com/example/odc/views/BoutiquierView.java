package com.example.odc.views;

import com.example.odc.entities.*;
import com.example.odc.enums.Role;
import com.example.odc.services.*;
import com.example.odc.validators.InputValidator;
import com.example.odc.views.listtemplate.ClientPrintListTemp;
import com.example.odc.views.listtemplate.DettePrintListTemp;
import com.example.odc.views.listtemplate.PaiementPrintListTemp;
import org.fusesource.jansi.AnsiConsole;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static org.fusesource.jansi.Ansi.ansi;

import java.util.*;

@Component
public class BoutiquierView {

    private Scanner scanner;
    private boolean isLoggedIn;
    private ArticleService articleService;
    private UserService userService;
    private ClientService clientService;
    private ArticleDetteService articleDetteService;
    private DetteService detteService;
    private PaiementService paiementService;

    public BoutiquierView(
            @Qualifier("articleServiceImp") ArticleService articleService,
            @Qualifier("userServiceImp") UserService userService,
            @Qualifier("clientServiceImp") ClientService clientService,
            @Qualifier("articleDetteServiceImp") ArticleDetteService articleDetteService,
            @Qualifier("detteServiceImp") DetteService detteService,
            @Qualifier("paiementServiceImp") PaiementService paiementService) {
        scanner = new Scanner(System.in);
        isLoggedIn = true;
        this.articleService = articleService;
        this.userService = userService;
        this.clientService = clientService;
        this.articleDetteService = articleDetteService;
        this.detteService = detteService;
        this.paiementService = paiementService;
    }

    public void displayMenu() {
        while (isLoggedIn) {
            clearConsole();
            System.out.println("\n************** Menu Boutiquier **************");
            System.out.println("1- Ajouter un Client");
            System.out.println("2- Lister les Clients");
            System.out.println("3- Créer un compte user pour un Client");
            System.out.println("4- Créer une dette pour un Client");
            System.out.println("5- Lister les dettes d'un Client");
            System.out.println("6- Effectuer un paiement pour une dette");
            System.out.println("7- Lister les Paiements d'une dette");
            System.out.println("8- Logout");
            System.out.println("********************************************");

            int choice = promptChoice();

            handleMenuChoice(choice);
        }
    }

    private int promptChoice() {
        System.out.print("\nVeuillez entrer votre choix: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Choix invalide. Entrez un numéro.");
            scanner.next(); // clear the invalid input
        }
        return scanner.nextInt();
    }

    private void handleMenuChoice(int choice) {
        scanner.nextLine(); // Consume newline
        switch (choice) {
            case 1:
                addClient();
                break;
            case 2:
                listClients();
                break;
            case 3:
                createUserAccountForClient();
                break;
            case 4:
                createDebtForClient();
                break;
            case 5:
                listClientDebts();
                break;
            case 6:
                effectuerPaiement();
                break;
            case 7:
                listDebtPayments();
                break;
            case 8:
                logout();
                break;
            default:
                System.out.println("Choix non valide. Veuillez réessayer.");
        }
    }

    private void addClient() {
        String reponse;
        do {
            clearConsole();
            String surnom = "";
            String telephone = "";
            System.out.println("Ajouter un Client:");
            boolean phoneExist = true;
            boolean surnomExist = true;
            while (surnomExist) {
                surnom = InputValidator.getValidName("surnom");
                Client user = this.clientService.findBySurnom(surnom);
                if (user != null) {
                    System.out.println("Ce surnom existe déjà. Veuillez entrer un autre surnom.");
                } else {
                    surnomExist = false;
                }
            }
            while (phoneExist) {
                telephone = InputValidator.getValidPhoneNumber();
                Client user = this.clientService.findByTelephone(telephone);
                if (user != null) {
                    System.out.println("Ce numéro de téléphone existe déjà. Veuillez entrer un autre numéro.");
                } else {
                    phoneExist = false;
                }
            }

            String adresse = InputValidator.getValidName("adresse");
            Client client = Client.builder()
                    .surnom(surnom)
                    .telephone(telephone)
                    .adresse(adresse).build();
            this.clientService.save(client);

            // Implémentation pour ajouter le client
            System.out.println("Client '" + surnom + " - " + telephone + " - " + adresse + "' ajouté avec succès.");

            System.out.println("Voulez-vous ajouter un autre client ? (oui(o)/non(n))");
            reponse = scanner.nextLine();
        }while(reponse.equalsIgnoreCase("o") || reponse.equalsIgnoreCase("oui"));
        promptReturnToMenu();
    }

    private void listClients() {
        clearConsole();
        System.out.println("Liste des Clients:");
        Collection<Client> clients = this.clientService.all();
//        PrintListView.printTable(clients);
        ClientPrintListTemp.printTable(clients);
        promptReturnToMenu();
    }

    private void createUserAccountForClient() {
        String reponse;
        do {
        clearConsole();
        boolean loginExist = true;
        boolean clientExist = false;
        int idClient = 0;
        String login = "";
        System.out.println("Créer un compte utilisateur pour un Client:");

        while (!clientExist){
            idClient = InputValidator.getValidInteger("Entrer l'identifiant du client");

            Client client = this.clientService.find(idClient);
            if (client == null) {
                System.out.println("Client non trouvé. Veuillez entrer un id valide.");
            } else {
                if (client.getUser() != null) {
                    System.out.println("Le client possède déjà un compte utilisateur.");
                }else {
                    clientExist = true;
                }
            }
        }

        String nom = InputValidator.getValidName("nom");
        String prenom = InputValidator.getValidName("prénom");
        while (loginExist) {
            login = InputValidator.getValidName("login");
            User user = this.userService.findByLogin(login);
            if (user!= null) {
                System.out.println("Ce login existe déjà. Veuillez entrer un autre login.");
            } else {
                loginExist = false;
            }
        }
        String password = InputValidator.getValidPassword();

        User user = User.builder()
                .login(login)
                .nom(nom)
                .prenom(prenom)
                .password(password)
                .role(Role.CLIENT).build();
        Client client = Client.builder().id(idClient).user(user).build();

        this.clientService.createAccount(client);

        // Implémentation pour créer un compte user
        System.out.println("Compte utilisateur créé pour le client '" + prenom+" - "+ nom + "'.");

        System.out.println("Voulez-vous céer un compte pour un autre client ? (oui(o)/non(n))");
            reponse = scanner.nextLine();
        }while(reponse.equalsIgnoreCase("o") || reponse.equalsIgnoreCase("oui"));
        promptReturnToMenu();
    }

    private void createDebtForClient() {
        String reponse;
        do {
        clearConsole();
        System.out.println("Créer une dette pour un Client:");
        boolean clientExist = false;
        int idClient = 0;
        Client client = null;
        double montantTotal = 0;
        while (!clientExist){
            idClient = InputValidator.getValidInteger("Entrer l'identifiant du client");

            client = this.clientService.find(idClient);
            if (client == null) {
                System.out.println("Client non trouvé. Veuillez entrer un id valide.");
            } else {
                    clientExist = true;
            }
        }

        System.out.println("Saisir les articles de la dette");
        int articleCount = 0;
        while (articleCount <= 0) {
            articleCount = InputValidator.getValidInteger("Combien d'articles avez-vous?");
            if (articleCount <= 0) {
                System.out.println("VOus devez avoir au moins un article");
            }
        }
        List<ArticleDette> articleDettes = new ArrayList<>();
        for (int i = 0; i < articleCount; i++) {
            System.out.println("Article n°" + (i + 1));
            boolean articleExist = false;
            int idArticle = 0;
            Article article = null;
            int qteVente = 0;
            while (!articleExist){
                idArticle = InputValidator.getValidInteger("Entrer l'identifiant de l'article");
                article = this.articleService.find(idArticle);
                if (article == null) {
                    System.out.println("Article non trouvé. Veuillez entrer un id valide.");
                } else {
                    articleExist = true;
                }
            }
            do {
                qteVente = InputValidator.getValidInteger("La quantité :");
                if (qteVente > article.getQuantiteEnStock() || qteVente <= 0){
                    System.out.println("La quantité acheté doit être inférieur ou égale au stock(positif)");
                }
            }while (qteVente > article.getQuantiteEnStock() || qteVente <= 0);

            double prixVente = InputValidator.getValidAmount("Le prix :");
            montantTotal += prixVente * qteVente;

            article.setQuantiteEnStock(article.getQuantiteEnStock() - qteVente);

            this.articleService.update(article.getId(), article);

            ArticleDette artD = ArticleDette.builder()
                    .qteVente(qteVente)
                    .prixVente(prixVente)
                    .article(article)
                    .build();
            articleDettes.add(artD);
        }

        Dette dette = Dette.builder()
                .montant(montantTotal)
                .client(client)
                .build();
        this.detteService.save(dette, articleDettes, Optional.empty());
        System.out.println("Dette de "+ montantTotal +" Fcfa créée pour le client '" + client.getSurnom() + "'.");
              System.out.println("Voulez-vous ajouter une autre dette pour un client ? (oui(o)/non(n))");
            reponse = scanner.nextLine();
        }while(reponse.equalsIgnoreCase("o") || reponse.equalsIgnoreCase("oui"));
        promptReturnToMenu();
    }

    private void listClientDebts() {
        clearConsole();
        boolean clientExist = false;
        int idClient = 0;
        System.out.println("Lister les dettes d'un Client:");
        while (!clientExist){
            idClient = InputValidator.getValidInteger("Entrer l'identifiant du client");

            Client client = this.clientService.find(idClient);
            if (client == null) {
                System.out.println("Client non trouvé. Veuillez entrer un id valide.");
            } else {
                clientExist = true;
            }
        }
        Client client = Client.builder().id(idClient).build();
        Collection<Dette> debts = this.detteService.findByClient(idClient);
//        PrintListView.printTable(debts);
        DettePrintListTemp.printTable(debts);
        promptReturnToMenu();
    }

    private void effectuerPaiement() {
        String reponse;
        do {
            clearConsole();
            boolean detteExist = false;
            int idDette = 0;
            Dette dette = null;
            while (!detteExist) {
                idDette = InputValidator.getValidInteger("Entrer l'identifiant de la dette");

                dette = this.detteService.find(idDette);
                if (dette == null) {
                    System.out.println("Dette non trouvé. Veuillez entrer un id valide.");
                } else {
                    detteExist = true;
                }
            }
            double montantDu = this.detteService.findMontantDu(idDette);
            if (montantDu != 0) {
                System.out.println(montantDu + "\n");
                boolean isPaiementGreater = true;
                double montantPaiement = 0;
                while (isPaiementGreater) {
                    montantPaiement = InputValidator.getValidAmount("Montant du paiement: ");
                    if (montantPaiement > montantDu) {
                        System.out.println("Montant du paiement supérieur au montant dû.");
                    } else {
                        isPaiementGreater = false;
                    }
                }
                Paiement paiement = Paiement.builder()
                        .montant(montantPaiement)
                        .dette(dette)
                        .build();
                this.paiementService.save(paiement);
                // Implémentation pour créer un compte user
                System.out.println("Le client " + paiement.getDette().getClient().getSurnom() + " a effectué un paiement");
            } else {
                System.out.println("Cette dette est déjà payée.");
            }
            System.out.println("Voulez-vous ajouter une autre dette pour un client ? (oui(o)/non(n))");
            reponse = scanner.nextLine();
        }while(reponse.equalsIgnoreCase("o") || reponse.equalsIgnoreCase("oui"));

        promptReturnToMenu();
    }

    private void listDebtPayments() {
        clearConsole();
        System.out.println("Lister les paiements d'une dette:");
        boolean detteExist = false;
        int idDette = 0;
        Dette dette = null;
        while (!detteExist){
            idDette = InputValidator.getValidInteger("Entrer l'identifiant de la dette");

            dette = this.detteService.find(idDette);
            if (dette == null) {
                System.out.println("Dette non trouvé. Veuillez entrer un id valide.");
            } else {
                detteExist = true;
            }
        }

        Collection<Paiement> payments = this.paiementService.findByDette(dette.getId());
        PaiementPrintListTemp.printTable(payments);
        // Implémentation pour lister les paiements d'une dette
        promptReturnToMenu();
    }

    private void logout() {
        System.out.println("Déconnexion réussie.");
        isLoggedIn = false;
    }

    private String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private void promptReturnToMenu() {
        System.out.println("\nAppuyez sur Entrée pour revenir au menu...");
        scanner.nextLine();
    }

    public static void clearConsole() {
        // Activer Jansi pour permettre l'usage des séquences ANSI
        AnsiConsole.systemInstall();

        // Effacer la console et replacer le curseur en haut à gauche
        System.out.print(ansi().eraseScreen().cursor(0, 0));

        // Désactiver Jansi
        AnsiConsole.systemUninstall();
    }
}
