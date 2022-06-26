package de.htwberlin.kba.vokabelduell_ui.impl;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserAlreadyExistAuthenticationException;
import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.vocab_management.export.*;
import de.htwberlin.kba.vokabelduell_ui.export.VokabellduellUi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Controller
public class VokabellduellUiController implements VokabellduellUi {

    private VokabelduellView view;
    private GameService gameService;
    private UserService userService;
    private VocabListService vocabListService;
    private RoundService roundService;
    private QuestionService questionService;
    private RequestService requestService;
    private VocabService vocabService;
    private TranslationService translationService;

    @Autowired
    public VokabellduellUiController(VokabelduellView view, VocabListService vocabListService, RoundService roundService, QuestionService questionService, GameService gameService, UserService userService, VocabService vocabService, TranslationService translationService) {
        super();
        this.view = view;
        this.vocabListService = vocabListService;
        this.roundService = roundService;
        this.questionService = questionService;
        this.gameService = gameService;
        this.userService = userService;
        this.vocabService = vocabService;
        this.translationService = translationService;
    }

    public VokabellduellUiController() {
        super();
    }
    public void setView(VokabelduellView view) {
        this.view = view;
    }
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }
    public void setVocabListService(VocabListService vocabListService) {
        this.vocabListService = vocabListService;
    }
    public void setVocabService(VocabService vocabService) {
        this.vocabService = vocabService;
    }
    public void setRoundService(RoundService roundService) {
        this.roundService = roundService;
    }
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    public void setTranslationService(TranslationService translationService) {
        this.translationService = translationService;
    }
    @Override
    public void run() throws FileNotFoundException, UserAlreadyExistAuthenticationException {

        // Login
        User currentUser = logIn();

        int input = 0;
        do {
            boolean rightInput = true;

            view.printMessage("Was möchtest du tun? \n " +
                    "1 - Neue Anfrage verschicken \n " +
                    "2 - Anfragen verwalten \n " +
                    "3 - Spiel weiterspielen \n " +
                    "4 - Vokabeln verwalten \n " +
                    "5 - Konto verwalten \n " +
                    "6 - Anwendung beenden");

            while (rightInput) {
                input = view.userInputInt();
                if (input < 7 & input > 0) {
                    rightInput = false;
                } else {
                    view.printMessage("Bitte gib eine Zahl zwischen 1 und 6 ein.");
                }
            }

            // Neue Anfrage verschicken
            if (input == 1) {

                view.printMessage("Suche dir aus der Liste von Mitspielern einen Gegner aus!");

                List<User> users = userService.getUserListWOcurrentUser(currentUser.getUserName());
                for (User user : users) {
                    view.printMessage(user.getUserName() + "\n");
                }

                String userName = view.userInputString();
                User receiver = null;

                try {
                    receiver = userService.getUserByUserName(userName);
                } catch (IllegalArgumentException e) {
                    view.printMessage("Dieser Mitspieler konnte leider nicht gefunden werden. Probiere es noch mal.");
                }

                requestService.createRequest(currentUser, receiver);
                view.printMessage("Wenn dein:e Gegner:in die Anfrage angenommen hat, kann das Spiel losgehen!");
            }

            // Anfragen verwalten
            if (input == 2) {

                view.printMessage("Gib den Benutzernamen des:r Nutzers:in ein, dessen Anfrage du annehmen oder ablehnen möchtest oder '6' zur Rückkehr ins Hauptmenü!");

                // Alle Anfragen ausgeben, wo der currentUser involviert ist, die noch nicht bearbeitet wurden
                List<Request> requests = requestService.getPendingRequestsForCurrentUser(currentUser);

                for (Request request : requests) {
                    view.printMessage(request.getRequester().getUserName() + "\n");
                }

                String userOrMenu = view.userInputString();

                for (Request request : requests) {
                    if (userOrMenu.equals(request.getRequester().getUserName())) {
                        // Status der Anfrage ändern
                        view.printMessage("Möchtest du (1) die Anfrage annehmen oder sie (2) ablehnen? Oder wähle 6 zur Rückkehr ins Hauptmenü!");
                        int requestAnswer = view.userInputInt();
                        if (requestAnswer == 1) {
                            changeRequestStatus(request, Status.ACCEPTED);
                        } else if (requestAnswer == 2) {
                            changeRequestStatus(request, Status.REJECTED);
                        }
                    }
                }
            }

            // Spiel weiterspielen
            if (input == 3) {
                // show all existing games from current user
                List<Game> games;
                boolean bol = true;

                do {
                    try {
                        games = gameService.getGamesFromCurrentUser(currentUser);
                        bol = false;
                    } catch (NullPointerException e) {
                        view.printMessage("Leider gibt es noch keine begonnen Spiele. Verschicke Anfragen oder nimm eine an, um ein neues Spiel zu starten.");
                        break;
                    }

                    view.printMessage("Gegen wen möchtest du weiterspielen? \n ");

                    for (Game game : games) {
                        if (game.getRounds().size() < 6) {
                            if (!game.getRounds().get(game.getRounds().size()-1).getLastUserPlayedName().equals(currentUser.getUserName())) {
                                view.printMessage(game.getRequester().getUserName() + " gegen " + game.getReceiver().getUserName());
                            } else {
                                view.printMessage(game.getRequester().getUserName() + " gegen " + game.getReceiver().getUserName() + "Hier ist dein Mitspieler dran.");
                            }
                        } else {
                            view.printMessage("Das Spiel wurde beendet!");
                            break;
                        }
                    }

                    // TODO HIER EXCEPTION?
                        String chosenUser = view.userInputString();
                        for (Game game : games) {
                            if (chosenUser.equals(game.getRequester().getUserName()) || chosenUser.equals(game.getReceiver().getUserName())) {
                                if (!game.getRounds().get(game.getRounds().size() - 1).getLastUserPlayedName().equals(currentUser.getUserName())) {

                                    // vocabList just needs to be chosen if old round has been finished by the other player
                                    // View Aufruf hier:
                                    VocabList vocabList = null;
                                    if (!game.getRounds().get(game.getRounds().size() - 1).getisPlayedByTwo()) {
                                        vocabList = chooseVocablist(vocabListService.getRandomVocabLists());
                                    } // no else: vocabList stays null if last existing round has been played by other player but not by current player
                                    List<Question> questions = gameService.giveQuestions(game, currentUser, vocabList);
                                    // Schleife koordiniert den View Aufruf:
                                    for (int j = 0; j < 4; j++) {
                                        askQuestions(game, currentUser, questions, j);
                                    }

                                    game.getRounds().get(game.getRounds().size() - 1).setLastUserPlayedName(currentUser.getUserName());
                                } else {
                                    view.printMessage("Hier ist dein Mitspieler dran. Versuche es noch einmal");
                                    bol = true;
                                }
                            } else {
                                view.printMessage("Dieser User wurde leider nicht als Mitspieler einer deiner bestehenden Spiele gefunden. Versuche es noch einmal");
                                bol = true;
                            }

                        }
                } while (bol);


            }

            // Vokabeln verwalten
            if (input == 4) {
                vocabListManagement();
            }
            // Konto verwalten
            if (input == 5) {
                view.printMessage("Was möchtest du tun? \n " +
                        "1 - Konto löschen \n " +
                        "2 - Passwort ändern");

                int userManagementInput = view.userInputInt();

                if (userManagementInput == 1) {
                    view.printMessage("Bist du dir sicher? Deine Spielstände werden gelöscht. \n " +
                            "1 - Ja \n " +
                            "2 - Nein");
                    int deleteUserInput = view.userInputInt();
                    if (deleteUserInput == 1) {
                        userService.removeUser(currentUser);
                        view.printMessage("Dein Konto wurde gelöscht.");
                    } else if (deleteUserInput == 2) {
                        break;
                    }
                } else if (userManagementInput == 2) {
                    view.printMessage("Gib Dein neues Password ein.");
                    String newPassword = view.userInputString();
                    userService.changePassword(newPassword, currentUser);
                }
            }
        } while (input != 6);

        view.printMessage("Bis zum nächsten Mal! :)");
        System.exit(0);

    }

    public User logIn() throws IllegalArgumentException, UserAlreadyExistAuthenticationException {
        view.printMessage("Herzlich Willkommen! Was möchtest du tun? \n " +
                "1 - Einloggen \n " +
                "2 - Registrieren");


        boolean rightNumber = true;
        User user = null;

        while (rightNumber) {
            int firstInput = view.userInputInt();
            // Einloggen
            if (firstInput == 1) {
                rightNumber = false;
                boolean bol = true;
                String userName = null;
                String password = null;

                do {
                    try {
                        view.printMessage("Wie lautet dein Benutzername?");
                        userName = view.userInputString();
                        view.printMessage("Gib dein Passwort ein.");
                        password = view.userInputString();

                        user = userService.getUserByUserName(userName);
                        if (user.getPassword().equals(password)) {
                            bol = false;
                        } else {
                            throw new UserAlreadyExistAuthenticationException("Die Kombination aus Benutzername und Passwort konnte leider nicht gefunden werden. Versuche es noch einmal. Drücke enter zum Verlassen des Menüpunkts.");
                        }
                    } catch (UserAlreadyExistAuthenticationException e) {
                        if (userName.isEmpty() & password.isEmpty()) {
                            break;
                        }
                    }
                } while (bol);
            }
            // Registrieren
            else if (firstInput == 2) {

                boolean bol = true;

                do {
                    try {
                        rightNumber = false;
                        view.printMessage("Wie soll dein Benutzername lauten");
                        String userName = view.userInputString();

                        for (User alreadyExistingUser : userService.getUserList()) {
                            if (alreadyExistingUser.getUserName().equals(userName)) {
                                throw new IllegalArgumentException();
                            }
                        }

                        view.printMessage("Gib dein Passwort ein.");
                        String password = view.userInputString();

                        user = userService.createUser(userName, password);

                        view.printMessage("Das ist deine UserId: " + user.getUserId());
                    } catch (IllegalArgumentException e) {
                        view.printMessage("Diesen Benutzernamen gibt es leider schon.");
                    }
                } while (bol);

            } else {
                view.printMessage("Bitte gib eine Zahl entsprechend des Menüs ein");
            }
        }

        return user;
    }

    public void askQuestions(Game game, User currentUser, List<Question> questions, int i) {

        List<String> answerOptions = questionService.giveAnswerOptionsRandom(questions.get(i));
        String vocabString = vocabService.giveVocabStringRandom(questions.get(i).getVocab());

        view.printMessage("Wie kann" + vocabString + " richtig übersetzt werden? Gib die richtige Antwort ein.\n " +
                "1 - " + answerOptions.get(0) + ",\n " +
                "2 - " + answerOptions.get(1) + "oder \n " +
                "3 - " + answerOptions.get(2) + "oder \n " +
                "4 - " + answerOptions.get(3) + "?");
        // user answers question
        String answer = view.userInputString();
        // is answer correct?
        boolean rightOrWrong = questionService.answeredQuestion(answer, questions.get(i).getRightAnswer());

        int points;
        if (rightOrWrong) {
            points = 500;
            view.printMessage("Super, das ist richtig!");
        } else {
            points = -200;
            view.printMessage("Das ist leider falsch. Die richtige Antwort lautet " + questions.get(i).getRightAnswer());
        }

        gameService.calculatePoints(game, currentUser, points);

    }


    public void changeRequestStatus(Request request, Status status) {

        request.setRequestStatus(status);

        if (status == Status.ACCEPTED) {
            view.printMessage("Super! Das Spiel kann losgehen.");
            // new game is created and starts immediately
            Game game = gameService.createGame(request.getRequester(), request.getReceiver());
            for (int i = 0; i<2; i++) {
                VocabList vocabList = null;
                if (i==1) {
                    vocabList = chooseVocablist(vocabListService.getRandomVocabLists());
                }
                // vocablist wurde ausgewählt und wird hier übergeben:
                List<Question> questions = gameService.giveQuestions(game, request.getReceiver(), vocabList);
                for (int j = 0; j<4; j++) {
                    // View Aufruf hier:
                    askQuestions(game, request.getReceiver(), questions, j);
                }
            }
        } else if (status == Status.REJECTED) {
            view.printMessage("Die Anfrage wurde abgelehnt.");
        }
    }

    public void vocabListManagement() throws FileNotFoundException {
        int inputVocabListManagementMenu;
        do {
            view.printMessage("Was möchtest du tun? \n " +
                    "1 - Neue Vokabelliste hinzufügen \n " +
                    "2 - Vokabelliste bearbeiten \n " +
                    "3 - Vokabelliste löschen \n " +
                    "4 - Rückkehr ins Hauptmenü");

            inputVocabListManagementMenu = view.userInputInt();

            // Neue Vokabelliste hinzufügen
            if (inputVocabListManagementMenu == 1) {
                String text = null;
                String path = null;
                boolean bol = true;

                do {
                    try {
                        view.printMessage("Gib den Dateipfad der neuen Vokabelliste ein.");
                        path = view.userInputString();
                        text = vocabListService.readFile(path);
                        bol = false;
                    } catch (FileNotFoundException e) {
                        view.printMessage("Das hat leider nicht funktioniert. Probiere es noch mal oder drücke enter zum Verlassen des Menüpunkts");
                        if (path == null) {
                            break;
                        }
                    }
                } while (bol);

                vocabListService.createVocabList(text);
            } else if (inputVocabListManagementMenu == 2) { // Vokabelliste bearbeiten
                view.printMessage("Gib den Namen der zu bearbeitenden Vokabelliste ein.");
                String vocabListName = view.userInputString();

                VocabList vocabList = vocabListService.getVocabListByName(vocabListName);

                view.printMessage("Was möchtest du bearbeiten? \n " +
                        "1 - Namen der Vokabelliste \n " +
                        "2 - Sprache der Vokabelliste \n " +
                        "3 - Kategorie der Vokabelliste \n " +
                        "4 - Vokabeln der Vokabelliste \n " +
                        "5 - Rückkehr ins Hauptmenü");

                int inputEditVocabList = view.userInputInt();

                if (inputEditVocabList == 1) { // Namen bearbeiten
                    view.printMessage("Gib den neuen Namen der Vokabelliste " + vocabList.getName() + " ein.");
                    String newName = view.userInputString();
                    vocabListService.editName(vocabList, newName);
                } else if (inputEditVocabList == 2) { // Sprache bearbeiten
                    view.printMessage("Gib die neue Sprache der Vokabelliste " + vocabList.getName() + " ein.  \n " +
                            "Die Sprache ist momentan " + vocabList.getLanguage());
                    String newLanguage = view.userInputString();
                    vocabListService.editLanguage(vocabList, newLanguage);
                } else if (inputEditVocabList == 3) { // Kategorie bearbeiten
                    view.printMessage("Gib die neue Kategorie der Vokabelliste " + vocabList.getName() + " ein.  \n " +
                            "Die Kategorie ist momentan " + vocabList.getCategory());
                    String newCat = view.userInputString();
                    vocabListService.editCategory(vocabList, newCat);
                } else if (inputEditVocabList == 4) { // Vokabeln der Liste bearbeiten
                    view.printMessage("Was möchtest du tun? \n " +
                            "1 - Vokabel aus der Vokabelliste entfernen \n " +
                            "2 - Rückkehr ins Hauptmenü");
                    int inputEditVocab = view.userInputInt();

                    if (inputEditVocab == 1) {
                        view.printMessage("Gib den Namen der zu entfernenden Vokabel ein.");
                        String vocabName = view.userInputString();

                        Vocab vocab = vocabService.getVocabByVocabString(vocabName);
                        vocabListService.removeVocab(vocabList, vocab);

                    } else if (inputEditVocab == 3) { // Rückkehr ins Hauptmenü
                        break;
                    }
                } else if (inputEditVocabList == 5) { // Rückkehr ins Hauptmenü
                    inputVocabListManagementMenu = 4;
                }

            } else if (inputVocabListManagementMenu == 3) { // Vokabelliste löschen
                view.printMessage("Gib den Namen der zu löschenden Vokabelliste ein.");
                String vocabListName = view.userInputString();

                VocabList vocabList = vocabListService.getVocabListByName(vocabListName);
                vocabListService.removeVocabList(vocabList);
            }
        } while (inputVocabListManagementMenu != 4);
    }

    public VocabList chooseVocablist(List<VocabList> randomVocabLists) {

        view.printMessage("Welche Vokabelliste möchtest du wählen? \n " +
                "1 - " + randomVocabLists.get(0).getName() + ",\n " +
                "2 - " + randomVocabLists.get(1).getName() + "oder \n " +
                "3 - " + randomVocabLists.get(2).getName() + "?");

        int input = view.userInputInt();
        VocabList randomVocabList = null;

        if (input == 1) {
            randomVocabList = randomVocabLists.get(0);
        }
        if (input == 2) {
            randomVocabList = randomVocabLists.get(1);
        }
        if (input == 3) {
            randomVocabList = randomVocabLists.get(2);
        }

        return randomVocabList;
    }
}