package de.htwberlin.kba.vokabelduell_ui.impl;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserAlreadyExistsException;
import de.htwberlin.kba.user_management.export.UserNotFoundException;
import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.vocab_management.export.*;
import de.htwberlin.kba.vokabelduell_ui.export.VokabellduellUi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.FileNotFoundException;
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
    private String userName;

    @Autowired
    public VokabellduellUiController(VokabelduellView view, VocabListService vocabListService, RoundService roundService, QuestionService questionService, GameService gameService, UserService userService, VocabService vocabService, TranslationService translationService, RequestService requestService) {
        super();
        this.view = view;
        this.vocabListService = vocabListService;
        this.roundService = roundService;
        this.questionService = questionService;
        this.gameService = gameService;
        this.userService = userService;
        this.vocabService = vocabService;
        this.translationService = translationService;
        this.requestService = requestService;
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

    public void run() {

        // TODO löschen, nur zum Test

        userName = "MartinTheBrain";
        User currentUser = null;

        try {
            currentUser = userService.getUserByUserName(userName);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }

        view.printMessage("Gib den Benutzernamen des:r Nutzers:in ein, dessen Anfrage du annehmen oder ablehnen möchtest oder '6' zur Rückkehr ins Hauptmenü!");

        // Alle Anfragen ausgeben, wo der currentUser involviert ist, die noch nicht bearbeitet wurden
        List<Request> requests = requestService.getPendingRequestsForCurrentUser(currentUser);

        for (Request request : requests) {
            view.printMessage(request.getRequester().getUserName() + "\n");
        }

        String userOrMenu = "AntjeWinner";

        for (Request request : requests) {
            if (userOrMenu.equals(request.getRequester().getUserName())) {
                // Status der Anfrage ändern
                view.printMessage("Möchtest du (1) die Anfrage annehmen oder sie (2) ablehnen? Oder wähle 6 zur Rückkehr ins Hauptmenü!");
                int requestAnswer = 1;
                if (requestAnswer == 1) {
                    changeRequestStatus(request, Status.ACCEPTED);
                } else if (requestAnswer == 2) {
                    changeRequestStatus(request, Status.REJECTED);
                }
            }
        }
    }
    public void changeRequestStatus(Request request, Status status) {

        if (status == Status.ACCEPTED) {
            view.printMessage("Super! Das Spiel kann losgehen.");
            // new game is created starts immediately
            Long gameId = gameService.createGame(request.getRequester(), request.getReceiver());


//            List<Long> randomVocabLists = vocabListService.getRandomVocabLists();
//            VocabList vocabList = chooseVocablist(vocabListService.getVocabListById());

            VocabList vocabList = null;
            try {
                vocabList = vocabListService.getVocabListById(2L);
            } catch (VocabListObjectNotFoundException e) {
                throw new RuntimeException(e);
            }
            // vocablist wurde ausgewählt und wird hier übergeben:
                boolean questionsChangedError = false;
                List<List<String>> questions = null;
                do {
                    try {
                        questions = gameService.giveQuestions(gameId, request.getReceiver().getUserName(), 2L);
                    } catch (CustomOptimisticLockExceptionGame e) {
                        questionsChangedError=true;
                    } catch (CustomObjectNotFoundException e) {
                        view.printMessage("Das hat leider nicht geklappt. Bitte versuche es noch einmal.");
                        break;
                    } catch (VocabListObjectNotFoundException e) {
                        view.printMessage("Das hat leider nicht geklappt. Bitte versuche es noch einmal.");
                        break;
                    }
                } while (questionsChangedError);
                for (int j = 0; j < 4; j++) {
                    // View Aufruf hier:
                    askQuestions(gameId, request.getReceiver(), questions, j);
                }
        } else if (status == Status.REJECTED) {
            view.printMessage("Die Anfrage wurde abgelehnt.");
        }

        // change status only if everything went through correctly
        request.setRequestStatus(status);
    }

    public void askQuestions(Long gameId, User currentUser, List<List<String>> questions, int i) {

        List<String> question = questions.get(i);

        view.printMessage("Wie kann" + question.get(0) + " richtig übersetzt werden? Gib die richtige Antwort ein.\n " +
                "1 - " + question.get(1) + ",\n " +
                "2 - " + question.get(2) + "oder \n " +
                "3 - " + question.get(3) + "oder \n " +
                "4 - " + question.get(4) + "?");
        // user answers question
        String answer = view.userInputString();
        // is answer correct?
        boolean rightOrWrong = false;
        try {
            rightOrWrong = questionService.answeredQuestion(answer, Long.valueOf(question.get(5)));
        } catch (CustomObjectNotFoundException e) {
            view.printMessage("Das hat leider nicht geklappt. Bitte versuche es noch einmal.");
            System.exit(0);
        }

        int points;
        if (rightOrWrong) {
            points = 500;
            view.printMessage("Super, das ist richtig!");
        } else {
            points = -200;
            view.printMessage("Das ist leider falsch.");
        }

        boolean saved = false;
        do {
            try {
                gameService.calculatePoints(gameId, userName, points);
            } catch (CustomOptimisticLockExceptionGame e) {
                saved=true;
            } catch (UserNotFoundException e) {
                saved=true;
            } catch (CustomObjectNotFoundException e) {
                saved=true;
            }
        } while (saved);



    }
/*
    public VocabList chooseVocablist(List<Long> randomVocabLists) {

        boolean bol = false;
        VocabList randomVocabList = null;

        do {

            view.printMessage("Welche Vokabelliste möchtest du wählen? \n " +
                    "1 - " + randomVocabLists.get(0).getName() + ",\n " +
                    "2 - " + randomVocabLists.get(1).getName() + "oder \n " +
                    "3 - " + randomVocabLists.get(2).getName() + "? \n " +
                    "4 - " + "Rückkehr ins Hauptmenü?");

            int input = view.userInputInt();

            if (input == 1) {
                randomVocabList = randomVocabLists.get(0);
            }
            if (input == 2) {
                randomVocabList = randomVocabLists.get(1);
            }
            if (input == 3) {
                randomVocabList = randomVocabLists.get(2);
            } else {
                view.printMessage("Gib eine Zahl aus dem Menü ein.");
                bol = true;
            }

        } while (bol);

        return randomVocabList;
    }

 */
}/*

        //-----------------------------------------------------------------------------------------------------------------

        // hier geht es los mit der Spielelogik


        try {
            // Login
            User currentUser = logIn();

            int input = 0;
            do {
                boolean rightInput = true;

                view.printMessage("""
                        Was möchtest du tun?\s
                         1 - Neue Anfrage verschicken\s
                         2 - Anfragen verwalten\s
                         3 - Spiel weiterspielen\s
                         4 - Vokabeln verwalten\s
                         5 - Konto verwalten\s
                         6 - Anwendung beenden""");

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

                    List<User> users = null;
                    try {
                        users = userService.getUserListWOcurrentUser(userName);
                    } catch (UserNotFoundException e) {
                        view.printMessage("Der User wurde leider nicht gefunden. Melde dich erneut an.");
                        System.exit(0);
                    }

                    for (User user : users) {
                        view.printMessage(user.getUserName() + "\n");
                    }


                    User receiver = null;
                    boolean userNotFound;

                    do {
                        userName = view.userInputString();
                        try {
                            userNotFound = false;
                            receiver = userService.getUserByUserName(userName);
                        } catch (UserNotFoundException e) {
                            userNotFound = true;
                            view.printMessage("Dieser Mitspieler konnte leider nicht gefunden werden. Probiere es noch einmal.");
                        }
                    } while (userNotFound);


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
                    List<Game> games = null;

                    try {
                        games = gameService.getGamesFromCurrentUser(userName);
                    } catch (UserNotFoundException e) {
                        view.printMessage("Der User " + userName + "wurde leider nicht gefunden. Starte das Spiel erneut und melde dich erneut an.");
                        System.exit(0);
                    }

                    if (games.isEmpty()) {
                        view.printMessage("Leider gibt es noch keine begonnen Spiele. Verschicke Anfragen oder nimm eine an, um ein neues Spiel zu starten.");
                    }

                    view.printMessage("Gegen wen möchtest du weiterspielen? \n ");

                    for (Game game : games) {
                        view.printMessage("" + game.getRounds());
                        // due to game logic, rounds cannot be empty if game has been started correctly
                        // else game cant continue
                        if (game.getRounds().size() < 6 & game.getRounds() != null & game.getRounds().size() != 0) {
                            if (!game.getRounds().get(game.getRounds().size() - 1).getLastUserPlayedName().equals(userName)) {
                                view.printMessage(game.getRequester().getUserName() + " gegen " + game.getReceiver().getUserName());
                            } else {
                                view.printMessage(game.getRequester().getUserName() + " gegen " + game.getReceiver().getUserName() + "Hier ist dein Mitspieler dran.");
                            }
                        }
                    }

                    boolean bol = true;
                    do {

                        String chosenUser = view.userInputString();
                        for (Game game : games) {
                            if (chosenUser.equals(game.getRequester().getUserName()) || chosenUser.equals(game.getReceiver().getUserName())) {
                                // due to game logic rounds cannot be empty
                                view.printMessage("Alles klar, kann losgehen!");
                                if (!game.getRounds().get(game.getRounds().size() - 1).getLastUserPlayedName().equals(userName)) {

                                    // vocabList just needs to be chosen if old round has been finished by the other player
                                    // View Aufruf hier:
                                    VocabList vocabList = null;
                                    if (!game.getRounds().get(game.getRounds().size() - 1).getisPlayedByTwo()) {
                                        vocabList = chooseVocablist(vocabListService.getRandomVocabLists());
                                    } // no else: vocabList stays null if last existing round has been played by other player but not by current player

                                    boolean questionsChangedError = false;
                                    List<List<String>> questions = null;
                                    do {
                                        try {
                                            questions = gameService.giveQuestions(game.getGameId(), userName, vocabList.getVocabListId());
                                        } catch (CustomOptimisticLockExceptionGame e) {
                                            questionsChangedError=true;
                                        } catch (CustomObjectNotFoundException | VocabListObjectNotFoundException e) {
                                            view.printMessage("Es ist ein Fehler aufgetreten. Beginne das Spiel bitte von vorne");
                                            System.exit(0);
                                        }
                                    } while (questionsChangedError);

                                    // Schleife koordiniert den View Aufruf:
                                    for (int j = 0; j < 4; j++) {
                                        askQuestions(game.getGameId(), currentUser, questions, j);
                                    }

                                    boolean lastPlayerChangedError = false;
                                    do {
                                        try {
                                            roundService.changeLastPlayer(game.getGameId(), currentUser.getUserName());
                                        } catch (CustomOptimisticLockExceptionGame e) {
                                            lastPlayerChangedError=true;
                                        } catch (CustomObjectNotFoundException ex) {
                                            view.printMessage("Das hat leider nicht geklappt.");
                                        }
                                    } while (lastPlayerChangedError);
                                    break;

                                } else {
                                    view.printMessage("Hier ist dein Mitspieler dran. Versuche es noch einmal");
                                    bol = true;
                                }
                            } else {
                                view.printMessage("Dieser User wurde leider nicht als Mitspieler einer deiner bestehenden Spiele gefunden. Versuche es noch einmal oder drücke enter, um die Auswahl abzubrechen.");
                                bol = !chosenUser.isEmpty();
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
                    view.printMessage("""
                            Was möchtest du tun?\s
                             1 - Konto löschen\s
                             2 - Passwort ändern\s
                             3 - Rückkehr ins Hauptmenü""");

                    boolean bol = true;
                    do {
                        int userManagementInput = view.userInputInt();

                        if (userManagementInput == 1) {
                            view.printMessage("""
                                    Bist du dir sicher? Deine Spielstände werden gelöscht.\s
                                     1 - Ja\s
                                     2 - Nein""");
                            int deleteUserInput = view.userInputInt();

                            if (deleteUserInput == 1) {
                                try {
                                    userService.removeUserName(userName);
                                } catch (UserNotFoundException e) {
                                    view.printMessage("Das hat leider nicht funktioniert. Du musst dich erneut anmelden.");
                                    System.exit(0);
                                }

                                view.printMessage("Dein Konto wurde gelöscht.");
                                view.printMessage("Bis zum nächsten Mal! :)");
                                System.exit(0);
                            } else if (deleteUserInput == 2) {
                                break;
                            }
                        } else if (userManagementInput == 2) {
                            view.printMessage("Gib Dein neues Password ein.");
                            String newPassword = view.userInputString();
                            try {
                                userService.changePassword(newPassword, userName);
                            } catch (UserNotFoundException e) {
                                view.printMessage("Es ist leider ein Fehler aufgetreten. Du solltest die Anwendung neu starten.");
                                input = 6;
                                break;
                            }
                        } else if (userManagementInput == 3) {
                            bol = false;
                        } else {
                            view.printMessage("Gib eine der Auswahlmöglichkeiten aus dem Menü ein.");
                        }

                    } while (bol);

                }
            } while (input != 6);
        } catch (RuntimeException e) {
            view.printMessage("Es ist leider ein Fehler aufgetreten. Bitte kontaktiere den Systemadministrator.");
        } finally {
            view.printMessage("Die Anwendung wird beendet. Bis zum nächsten Mal! :)");
            System.exit(0);
        }
    }

    public User logIn() {
        view.printMessage("""
                Herzlich Willkommen! Was möchtest du tun?\s
                 1 - Einloggen\s
                 2 - Registrieren""");


        boolean rightNumber = true;
        User user = null;

        while (rightNumber) {
            int firstInput = view.userInputInt();
            // Einloggen
            if (firstInput == 1) {
                rightNumber = false;
                boolean bol = true;
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
                            view.printMessage("Du bist eingeloggt.");
                        } else {
                            view.printMessage("Die Kombination aus Benutzername und Passwort konnte leider nicht gefunden werden. Versuche es noch einmal. Drücke enter zum Verlassen des Menüpunkts.");
                        }
                    } catch (UserNotFoundException e) {
                        if (userName == null & password == null) {
                            break;
                        }
                        view.printMessage("Den Benutzernamen gibt es leider nicht. Probiere es noch einmal.");

                    }
                } while (bol);
            }
            // Registrieren
            else if (firstInput == 2) {
                rightNumber = false;
                userName = null;
                boolean invalidName;

                do {

                    invalidName = false;
                    view.printMessage("Wie soll dein Benutzername lauten");
                    userName = view.userInputString();

                    view.printMessage("Gib dein Passwort ein.");
                    String password = view.userInputString();

                    try {
                        user = userService.createUser(userName, password);
                    } catch (UserAlreadyExistsException e) {
                        if (userName.isEmpty() & password.isEmpty()) {
                            break;
                        }
                        view.printMessage("Diesen Benutzernamen gibt es leider schon. Probiere es noch einmal. Drücke enter zum Verlassen des Menüpunkts.");
                        invalidName = true;
                    }
                } while (invalidName);

            } else {
                view.printMessage("Bitte gib eine Zahl entsprechend des Menüs ein");
            }
        }

        return user;
    }

    public void askQuestions(Long gameId, User currentUser, List<List<String>> questions, int i) {

        List<String> question = questions.get(i);

        view.printMessage("Wie kann" + question.get(0) + " richtig übersetzt werden? Gib die richtige Antwort ein.\n " +
                "1 - " + question.get(1) + ",\n " +
                "2 - " + question.get(2) + "oder \n " +
                "3 - " + question.get(3) + "oder \n " +
                "4 - " + question.get(4) + "?");
        // user answers question
        String answer = view.userInputString();
        // is answer correct?
        boolean rightOrWrong = false;
        try {
            rightOrWrong = questionService.answeredQuestion(answer, Long.valueOf(question.get(5)));
        } catch (CustomObjectNotFoundException e) {
            view.printMessage("Das hat leider nicht geklappt. Bitte versuche es noch einmal.");
            System.exit(0);
        }

        int points;
        if (rightOrWrong) {
            points = 500;
            view.printMessage("Super, das ist richtig!");
        } else {
            points = -200;
            view.printMessage("Das ist leider falsch.");
        }

        boolean saved = false;
        do {
            try {
                gameService.calculatePoints(gameId, userName, points);
            } catch (CustomOptimisticLockExceptionGame e) {
                saved=true;
            } catch (UserNotFoundException e) {
                saved=true;
            } catch (CustomObjectNotFoundException e) {
                saved=true;
            }
        } while (saved);



    }


    public void changeRequestStatus(Request request, Status status) {

        if (status == Status.ACCEPTED) {
            view.printMessage("Super! Das Spiel kann losgehen.");
            // new game is created starts immediately
            Long gameId = gameService.createGame(request.getRequester(), request.getReceiver());


                   VocabList vocabList = chooseVocablist(vocabListService.getRandomVocabLists());
                // vocablist wurde ausgewählt und wird hier übergeben:
                boolean questionsChangedError = false;
                List<List<String>> questions = null;
                do {
                    try {
                        questions = gameService.giveQuestions(gameId, request.getReceiver().getUserName(), vocabList.getVocabListId());
                    } catch (CustomOptimisticLockExceptionGame e) {
                        questionsChangedError=true;
                    } catch (CustomObjectNotFoundException e) {
                        view.printMessage("Das hat leider nicht geklappt. Bitte versuche es noch einmal.");
                        break;
                    } catch (VocabListObjectNotFoundException e) {
                        view.printMessage("Das hat leider nicht geklappt. Bitte versuche es noch einmal.");
                        break;
                    }
                } while (questionsChangedError);
                for (int j = 0; j < 4; j++) {
                    // View Aufruf hier:
                    askQuestions(gameId, request.getReceiver(), questions, j);
                }
        } else if (status == Status.REJECTED) {
            view.printMessage("Die Anfrage wurde abgelehnt.");
        }

        // change status only if everything went through correctly
        request.setRequestStatus(status);
    }

    public void vocabListManagement() {
        int inputVocabListManagementMenu;
        do {
            view.printMessage("""
                    Achtung: Das vocabListManagement ist noch nicht vollständig implementiert. Daher kann es zu unvorhergesehenen Fehlern kommen.\040
                    Wenden Sie sich in solchen Fällen an Ihren Systemadministrator.
                    Was möchtest du tun?\s
                     1 - Neue Vokabelliste hinzufügen\s
                     2 - Vokabelliste bearbeiten\s
                     3 - Vokabelliste löschen\s
                     4 - Rückkehr ins Hauptmenü""");

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
                        view.printMessage("Das hat leider nicht funktioniert. Probiere es noch einmal oder drücke enter zum Verlassen des Menüpunkts.");
                        if (path == null) {
                            break;
                        }
                    }
                } while (bol);

                try {
                    vocabListService.createVocabList(text);
                } catch (FileNotFoundException e) {
                    view.printMessage("Der File konnte leider nicht gefunden werden.");
                }

            } else if (inputVocabListManagementMenu == 2) { // Vokabelliste bearbeiten

                view.printMessage("Gib die Id der zu bearbeitenden Vokabelliste ein.");
                boolean bol;
                VocabList vocabList = null;
                Long vocabListId = null;
                do {
                    try {
                        vocabListId = view.userInputLong();
                        vocabList = vocabListService.getVocabListById(vocabListId);
                        bol = false;
                    } catch (VocabListObjectNotFoundException e) {
                        view.printMessage("Die Vokabelliste wurde nicht gefunden. Probier es noch einmal. Oder drücke enter zum Verlassen des Menüpunkts.");
                        bol = true;
                        if (vocabListId == null) {
                            inputVocabListManagementMenu = 4;
                            break;
                        }
                    }
                } while (bol);


                view.printMessage("""
                        Was möchtest du bearbeiten?\s
                         1 - Namen der Vokabelliste\s
                         2 - Sprache der Vokabelliste\s
                         3 - Kategorie der Vokabelliste\s
                         4 - Vokabeln der Vokabelliste\s
                         5 - Rückkehr ins Hauptmenü""");

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
                    view.printMessage("""
                            Was möchtest du tun?\s
                             1 - Vokabel aus der Vokabelliste entfernen\s
                             2 - Rückkehr ins Hauptmenü""");

                    bol = true;
                    do {
                        try {
                            int inputEditVocab = view.userInputInt();

                            if (inputEditVocab == 1) {
                                view.printMessage("Gib den Namen der zu entfernenden Vokabel ein.");
                                String vocabName = view.userInputString();

                                Vocab vocab = vocabService.getVocabByVocabString(vocabName);
                                vocabListService.removeVocab(vocabList, vocab);
                                bol = false;
                                view.printMessage("Die Vokabel wurde entfernt.");

                            } else if (inputEditVocab == 2) { // Rückkehr ins Hauptmenü
                                break;
                            }
                        } catch (VocabListObjectNotFoundException e) {
                            view.printMessage("Die Vokabel wurde nicht gefunden. Probier es noch einmal.");
                        }
                    } while (bol);


                } else if (inputEditVocabList == 5) { // Rückkehr ins Hauptmenü
                    inputVocabListManagementMenu = 4;
                }

            } else if (inputVocabListManagementMenu == 3) { // Vokabelliste löschen
                String vocabListName = null;
                try {
                    view.printMessage("Gib die Id der zu löschenden Vokabelliste ein.");
                    long vocabListId = view.userInputLong();

                    VocabList vocabList = vocabListService.getVocabListById(vocabListId);
                    vocabListService.removeVocabList(vocabList);
                } catch (VocabListObjectNotFoundException e) {
                    view.printMessage("Diese Vokabelliste gibt es nicht. Probiere es noch einmal. Oder drücke enter, um die Auswahl abzubrechen");
                    if (vocabListName == null) {
                        break;
                    }
                }
            }
        } while (inputVocabListManagementMenu != 4);
    }

    public VocabList chooseVocablist(List<VocabList> randomVocabLists) {

        boolean bol = false;
        VocabList randomVocabList = null;

        do {

            view.printMessage("Welche Vokabelliste möchtest du wählen? \n " +
                    "1 - " + randomVocabLists.get(0).getName() + ",\n " +
                    "2 - " + randomVocabLists.get(1).getName() + "oder \n " +
                    "3 - " + randomVocabLists.get(2).getName() + "? \n " +
                    "4 - " + "Rückkehr ins Hauptmenü?");

            int input = view.userInputInt();

            if (input == 1) {
                randomVocabList = randomVocabLists.get(0);
            }
            if (input == 2) {
                randomVocabList = randomVocabLists.get(1);
            }
            if (input == 3) {
                randomVocabList = randomVocabLists.get(2);
            } else {
                view.printMessage("Gib eine Zahl aus dem Menü ein.");
                bol = true;
            }

        } while (bol);

        return randomVocabList;
    }


}*/