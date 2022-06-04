package de.htwberlin.kba.vokabelduell_ui.impl;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.vocab_management.export.*;
import de.htwberlin.kba.vokabelduell_ui.export.VokabellduellUi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.FileNotFoundException;
import java.util.*;

@Controller
public class VokabellduellUiController implements VokabellduellUi {

    // TODO: Im Controller nur Verschachtelung von Services und View
    // get user by name, get vocablist by name: eindeutige namen
    // TODO: Genutzte Services hier und in der bean und in der springimpl der config vermerken ?
    // TODO: ungültige Usereingaben verarbeiten
    // TODO: userName muss eindeutig sein --> Implementierung in DAO???!!!
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
    public void run() {

        // Login
        User currentUser = logIn();

        int input;
        do {
            view.printMessage("Was möchtest du tun? \n " +
                    "1 - Neue Anfrage verschicken \n " +
                    "2 - Anfragen verwalten \n " +
                    "3 - Spiel weiterspielen \n " +
                    "4 - Vokabeln verwalten \n " +
                    "5 - Konto verwalten \n " +
                    "6 - Anwendung beenden");

            input = view.userInputInt();

            // Neue Anfrage verschicken
            if (input == 1) {

                view.printMessage("Suche dir aus der Liste von Mitspielern einen Gegner aus!");

                List<User> users = userService.getUserListWOcurrentUser(currentUser.getUserId());
                for (User user : users) {
                    view.printMessage(user.getUserName() + "\n");
                }

                String userName = view.userInputString();
                // TODO DAO Spieler anhand des Benutzernamens auswählen
                User receiver = userService.getUserByUserName(userName);

                requestService.createRequest(1L, currentUser, receiver);
                view.printMessage("Wenn dein:e Gegner:in die Anfrage angenommen hat, kann das Spiel losgehen!");
            }

            // Anfragen verwalten
            if (input == 2) {

                view.printMessage("Gib den Benutzernamen des:r Nutzers:in ein, dessen Anfrage du annehmen oder ablehnen möchtest oder '6' zur Rückkehr ins Hauptmenü!");
                // Alle Anfragen ausgeben, wo der currentUser involviert ist, die noch nicht abgelehnt wurden
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
                List<Game> games = gameService.getGamesFromCurrentUser(currentUser);
                view.printMessage("Gegen wen möchtest du weiterspielen? \n ");
                for (Game game : games) {
                    if (game.getRounds().size() > 6) { // TODO > 6, was wenn man die 6. Runde beendet?
                        view.printMessage(game.getRequester().getUserName() + " gegen " + game.getReceiver().getUserName());
                    }
                }

                String chosenUser = view.userInputString();

                for (Game game : games) {
                    if (chosenUser.equals(game.getRequester().getUserName()) || chosenUser.equals(game.getReceiver().getUserName())) {
                        playGame(game, currentUser);
                    }
                }
            }

            // Vokabeln verwalten
            if (input == 4) {
                try {
                    vocabListManagement();
                } catch (Exception e) {
                    view.printMessage("Das hat leider nicht geklappt.");
                }

            }
            // Konto verwalten
            if (input == 5) {
                // TODO Gibt es hier noch mehr Möglichkeiten?
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

    public User logIn() {
        view.printMessage("Herzlich Willkommen! Was möchtest du tun? \n " +
                "1 - Einloggen \n " +
                "2 - Registrieren");

        int firstInput = view.userInputInt();

        // Einloggen
        if (firstInput == 1) {
            boolean bol = true;
            User user;
            do {
                view.printMessage("Wie lautet deine BenutzerId?");
                String userName = view.userInputString();
                view.printMessage("Gib dein Passwort ein.");
                String password = view.userInputString();

                user = userService.getUserByUserName(userName);
                if (user.getPassword().equals(password)) {
                    bol = false;
                }
            } while (bol);

            return user;
        }
        // Registrieren
        else if (firstInput == 2) {
            view.printMessage("Wie soll dein Benutzername lauten");
            String userName = view.userInputString();
            view.printMessage("Gib dein Passwort ein.");
            String password = view.userInputString();

            User user = userService.createUser(userName, password);

            view.printMessage("Das ist deine UserId: " + user.getUserId());

            return user;
        }
        return null;
    }
    public void playGame(Game game, User currentUser) {

        List<Question> questions;
        List<Round> rounds = game.getRounds();

        // if game has not just been created and the last round was not played by both players
        // the last existing round of the game is played
        if (!rounds.isEmpty() & !rounds.get(rounds.size() - 1).getisPlayedByTwo()) {
            Round round = game.getRounds().get(game.getRounds().size()-1);
            questions = round.getQuestions();

            for (int i = 0; i<4; i++) {
                askQuestions(game, currentUser, questions, i);
            }

            round.setPlayedByTwo(true);
        }

        // if game has just been started OR the last round of the game was played by both players
        // (happens in the if clause above)
        // new Round must be started
        Round round = roundService.startNewRound(game);

        // get 3 random vocablists to choose from
        List<VocabList> randomVocabLists = vocabListService.getRandomVocabLists();

        // user chooses vocablist for round
        VocabList chosenVocabList = chooseVocablist(randomVocabLists);

        // generate Questions
        questions = questionService.createQuestions(game, chosenVocabList);
        // der Round hinzufügen
        round.setQuestions(questions);

        for (int i = 0; i<4; i++) {
            askQuestions(game, currentUser, questions, i);
        }
        // now its the other players turn who has to login and play now
    }

    public void askQuestions(Game game, User currentUser, List<Question> questions, int i) {

        List<String> answerOptions = questionService.giveAnswerOptionsRandom(questions.get(i));
        String vocabString = vocabService.giveVocabStringRandom(questions.get(i).getVocab());

        view.printMessage("Wie kann" + vocabString + " richtig übersetzt werden? \n " +
                "1 - " + answerOptions.get(0) + ",\n " +
                "2 - " + answerOptions.get(1) + "oder \n " +
                "2 - " + answerOptions.get(2) + "oder \n " +
                "3 - " + answerOptions.get(3) + "?");
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
            // new game is created and starts
            playGame(gameService.createGame(1L, request.getRequester(), request.getReceiver()), request.getReceiver());
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
                view.printMessage("Gib den Dateipfad der neuen Vokabelliste ein.");
                String path = view.userInputString();
                String text = vocabListService.readFile(path);
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

                        Vocab vocab = vocabService.getVocabByName(vocabName);
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

        if (input == 1) {
            return randomVocabLists.get(0);
        }
        if (input == 2) {
            return randomVocabLists.get(1);
        }
        if (input == 3) {
            return randomVocabLists.get(2);
        } else {
            return null;
        }
    }
}
