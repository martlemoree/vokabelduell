package de.htwberlin.kba.vokabelduell_ui.impl;

import de.htwberlin.kba.vokabelduell_ui.export.VokabellduellUi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class VokabellduellUiController implements VokabellduellUi {

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

    // TODO: Constructor-Injection?
    @Autowired
    public VokabellduellUiController(VokabelduellView view, VocabListService vocabListService, RoundService roundService, QuestionService questionService, GameService gameService, UserService userService) {
        super();
        this.view = view;
        this.vocabListService = vocabListService;
        this.roundService = roundService;
        this.questionService = questionService;
        this.gameService = gameService;
        this.userService = userService;
    }

    public VokabellduellUiController() {
        super();
    }

    // @Autowired
    public void setView(VokabelduellView view) {
        this.view = view;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    public void setVocabListService(VocabListService vocabListService) {
        this.vocabListService = vocabListService;
    }

    public void setRoundService(RoundService roundService) {
        this.roundService = roundService;
    }

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void run() {

        // Login
        User currentUser = logIn();

        do {
            view.printMessage ("Was möchtest du tun? \n " +
                    "1 - Neue Anfrage verschicken \n " +
                    "2 - Anfragen verwalten \n " +
                    "3 - Spiel weiterspielen \n " +
                    "4 - Vokabeln verwalten \n " +
                    "5 - Konto verwalten \n " +
                    "6 - Anwendung beenden");

            int input = view.userInputInt();

            // Neue Anfrage verschicken
            if (input == 1) {

                view.printMessage("Suche dir aus der Liste von Mitspielern einen Gegner aus!");

                // für jeden user in der Liste den UserName ausgeben
                List<User> users = userService.getUserListWOcurrentUser().getUserName();
                for (user : users) {
                    view.printMessage(user.getUserName() + "\n");
                }

                String userName = view.userInputString();
                // TODO DAO Spieler anhand des Benutzernamens auswählen
                User reveiver = userService.getUserByUserName(userName); // diese Methode gibt es noch nicht

                Request request = requestService.createRequest(1L, currentUser, receiver);
                // TODO muss das hier dynamisch sein? Braucht es überhaupt einen Rückgabewert?

                view.printMessage("Wenn dein:e Gegner:in die Anfrage angenommen hat, kann das Spiel losgehen!");

            }

            // Anfragen verwalten
            if (input == 2) {

                view.printMessage("Gib den Benutzernamen des:r Nutzers:in ein, dessen Anfrage du annehmen oder ablehnen möchtest oder drücke 6 zur Rückkehr ins Hauptmenü!");
                // Alle Anfragen ausgeben, wo der currentUser involviert ist
                List<Request> requests = requestService.getRequestsForCurrentUser(currentUser);
                for (request : requests) {
                    view.printMessage(request.getRequester().getUserName() + "\n");
                }

                String userOrMenu = view.userInputString();

                for (request : requests) {
                    // Passt das hier so?
                    if (userOrMenu.equals(request.getRequester().getUserName())){
                        // Status der Anfrage ändern
                        view.printMessage("Möchtest du (1) die Anfrage annehmen oder sie (2) ablehnen? Oder wähle 6 zur Rückkehr ins Hauptmenü!");
                        int input = view.userInputInt();
                        if (input == 1) {
                            changeRequestStatus(request, ACCEPTED);
                        } else if (input == 2) {
                            changeRequestStatus(request, REJECTED);
                        }
                    }
                }
            }

            // Spiel weiterspielen
            if (input == 3) {
                // show all existing games from current user
                List<Game> games = gameService.getGamesFromCurrentUser(currentUser);
                view.printMessage ("Gegen wen möchtest du weiterspielen? \n ");
                for (game : games) {
                    view.printMessage(game.getRequester().getUserName() + " gegen " + game.getReceiver().getUserName());
                }

                String chosenUser = view.userInputString();

                for (game : games) {
                    if (chosenUser.equals(game.getRequester().getUserName()) || chosenUser.equals(game.getReceiver().getUserName())) {
                      playGame(game);
                    }
                }
            }

            // Vokabeln verwalten
            if (input == 4) {
                vocabListManagement();
            }
            // Konto verwalten
            if (input == 5) {
                // TODO Gibt es hier noch mehr Möglichkeiten?
                view.printMessage ("Was möchtest du tun? \n " +
                        "1 - Konto löschen \n " +
                        "2 - Passwort ändern");

                int input = view.userInputInt();

                if (input == 1) {
                    view.printMessage ("Bist du dir sicher? Deine Spielstände werden gelöscht. \n " +
                            "1 - Ja \n " +
                            "2 - Nein");
                    int input = view.userInputInt();
                    if (input == 1) {
                        userService.removeUser(currentUser);
                        view.printMessage ("Dein Konto wurde gelöscht.");
                    } else if (input == 2) {
                        break; // TODO Funktioniert das hier so? --> Ich möchte zurück zum Eingangs-Menü
                    }
                } else if (input == 2) {
                    view.printMessage ("Gib Dein neues Password ein.");
                    String newPassword = view.userInputString();
                    userService.changePassword(newPassword, currentUser);
                }
            }
        } while(input != 6);

        view.printMessage("Bis zum nächsten Mal! :)");
        // TODO: muss hier noch was passieren, um die Anwendung "richtig" zu beenden?

    }

    public void playGame(Game game) {

        // TODO: Wenn ich das hier so verwenden will, kann die Runde nicht jedes Mal bei 1 anfangen
        // Was, wenn ein Spiel weitergespielt wird?
        // Anzahl der Runde wird wo festgehalten?
        for (int i = 1; i < 7; i++) {
            // Jedes Mal wird eine neue Runde erstellt
            // Es muss auch erst Spieler 1 und dann Spieler 2 dran sein

            Round round = roundService.createRound(1L, game, i);

            // get 3 random vocablists to choose from
            List<VocabList> randomVocabLists = vocabListService.getRandomVocablists();

            // user chooses vocablist for round
            VocabList chosenVocabList = chooseVocablist(randomVocabLists);

            // generate Question
            Question question = questionService.createQuestion(1L, round, chosenVocabList);

            // user is asked question
            askQuestion(question);
            // user answers question
            int answer = view.userInputInt();
            // is answer correct?
            boolean rightOrWrong = answerQuestion(answer, question.getRightAnswer());

            // Punkte berechnen
            int points;
            if (rightOrWrong) {
                points = 500;
            } else {
                points = -200;
            }

            // Wie machen wir das hier mit dem User?
            calculatePoints(game, new User( 1L,"AntjeWinner","richtigGutesPassword"), points);

        }

    }


    public User logIn() {
        view.printMessage ("Herzlich Willkommen! Was möchtest du tun? \n " +
                "1 - Einloggen \n " +
                "2 - Registrieren");

        int firstInput = view.userInputInt();

        // Einloggen
        if (firstInput == 1) {
            view.printMessage ("Wie lautet dein Benutzername?");
            String userName = view.userInputString();
            view.printMessage ("Gib dein Passwort ein.");
            String password = view.userInputString();

            // TODO DAO Überprüfung der Anmeldedaten + Übergabe des Users
            return user;

        }
        // Registrieren
        else if (firstInput == 2) {
            view.printMessage ("Wie soll dein Benutzername lauten");
            String userName = view.userInputString();
            // TODO Muss der Benutzername eindeutig sein?
            view.printMessage ("Gib dein Passwort ein.");
            String password = view.userInputString();

            view.printMessage ("Das ist deine einmalige User Id:");

            return userService.createUser(userName, password);
        }
    }

    public void changeRequestStatus(Request request, Status status) {

        // TODO Ist das mit dem setter hier so richtig oder muss das über den Service laufen?
        request.setRequestStatus(status);

        // funktioniert der Vergleich hier so?
        if (status == ACCEPTED) {
            // new game is created and starts
            playGame(gameService.createGame(1L, request.getRequester(), request.getReceiver()));
        }
    }

    public void vocabListManagement() {
        view.printMessage ("Was möchtest du tun? \n " +
                "1 - Neue Vokabelliste hinzufügen \n " +
                "2 - Vokabelliste bearbeiten \n " +
                "3 - Vokabelliste löschen \n " +
                "4 - Rückkehr ins Hauptmenü");

        int inputVocabListManagementMenu = view.userInputInt();

        // Neue Vokabelliste hinzufügen
        if (inputVocabListManagementMenu == 1) { // TODO: Antje: So richtig verstanden?
            view.printMessage ("Gib den Dateipfad der neuen Vokabelliste ein.");
            String path = view.userInputString();
            String text = readFile(path);
            vocabListService.createVocabList(text);
        } else if (inputVocabListManagementMenu == 2) { // Vokabelliste bearbeiten
            view.printMessage ("Gib den Namen der zu bearbeitenden Vokabelliste ein.");
            String input = view.userInputString();

            List<VocabList> vocabLists = vocabListService.getVocabLists();
            for (VocabList vocabList : vocabLists) {
                // TODO: hierfür könnte die equals und die hash methode der VocabList angepasst werden
                if (vocabList.getName().equals(input)) {
                    view.printMessage ("Was möchtest du bearbeiten? \n " +
                            "1 - Namen der Vokabelliste \n " +
                            "2 - Sprache der Vokabelliste \n " +
                            "3 - Kategorie der Vokabelliste \n " +
                            "4 - Vokabeln der Vokabelliste \n " +
                            "5 - Rückkehr ins Hauptmenü");

                    int inputEditVocabList = view.userInputInt();

                    if (inputEditVocabList == 1) { // Namen bearbeiten
                        view.printMessage ("Gib den neuen Namen der Vokabelliste " + vocabList.getName() + " ein.");
                        String newName = view.userInputString();
                        vocabListService.editName(vocabList, newName);
                    } else if (inputEditVocabList == 2) { // Sprache bearbeiten
                        view.printMessage ("Gib die neue Sprache der Vokabelliste " + vocabList.getName() + " ein.  \n " +
                                "Die Sprache ist momentan " + vocabList.getLanguage());
                        String newLanguage = view.userInputString();
                        vocabListService.editLanguage(vocabList,newLanguage);
                    } else if (inputEditVocabList == 3) { // Kategorie bearbeiten
                        view.printMessage ("Gib die neue Kategorie der Vokabelliste " + vocabList.getName() + " ein.  \n " +
                                "Die Kategorie ist momentan " + vocabList.getCategory());
                        String newCat = view.userInputString();
                        vocabListService.editCategory(vocabList,newCat);
                    } else if (inputEditVocabList == 4) { // Vokabeln der Liste bearbeiten
                        // TODO: Inwiefern können die Vokabeln selbst bearbeitet werden? (abgesehen von hinzufügen und löschen)


                        view.printMessage ("Was möchtest du tun? \n " +
                                "1 - Vokabel der Vokabelliste hinzufügen \n " +
                                "2 - Vokabel aus der Vokabelliste entfernen \n " +
                                "3 - Rückkehr ins Hauptmenü");
                        int inputEditVocab = view.userInputString();

                        if (inputEditVocab == 1) { // TODO Vokabel hinzufügen
                            addVocab(VocabList vocabList,Vocab vocab);
                        } else if (inputEditVocab == 2) { // TODO Vokabel entfernen
                            view.printMessage ("Gib den Namen der zu entfernenden Vokabel ein.");
                            String vocabName = view.userInputString();

                            List<Vocab> vocabs = vocabList.getVocabs();
                            for (Vocab vocab : vocabs) {
                                // Wenn es mit einem der Komponenten der String List übereinstimmt
                                List<String> vocabStrings = vocab.getVocabs();
                                for (String vocabString : vocabStrings) {
                                    if (vocabName.equals(vocabString)) {
                                        vocabListService.removeVocab(vocabList, vocab);
                                    }
                                }

                            }


                        } else if (inputEditVocab == 3) {
                            break;
                        }

                    } else if (inputEditVocabList == 5) { // Rückkehr ins Hauptmenü
                        // TODO Funktioniert das so?
                        break;
                    }
                }
            }
        } else if (inputVocabListManagementMenu == 3) { // Vokabelliste löschen
            view.printMessage ("Gib den Namen der zu löschenden Vokabelliste ein.");
            String input = view.userInputString();

            List<VocabList> vocabLists = getVocabLists();
            for (VocabList vocabList : vocabLists) {
                // TODO: hierfür könnte die equals und die hash methode der VocabList angepasst werden
                if (vocabList.getName().equals(input)) {
                    vocabListService.removeVocabList(vocabList);
                    view.printMessage ("Die Vokabelliste " + input + " wurde gelöscht.");
                }
            }
        } else if (inputVocabListManagementMenu == 4) {
            break;
        }
    }
    public VocabList chooseVocablist(List<VocabList> randomVocabLists) {

        view.printMessage ("Welche Vokabelliste möchtest du wählen? \n " +
                "1 - " + randomVocabLists.get(0).getName () + ",\n " +
                "2 - " + randomVocabLists.get(1).getName () + "oder \n " +
                "3 - " + randomVocabLists.get(2).getName () + "?");

        int input = view.userInputInt();

        if (input == 1) {
            return randomVocabLists.get(0);
        }
        if (input == 2)) {
            return randomVocabLists.get(1);
        }
        if (input == 3) {
            return randomVocabLists.get(2);
        } else {
            // TODO exception ?
            return null;
        }
    }

    public void askQuestion(question) {

        Random rand = new Random();

        List<Translation> translations =  = new ArrayList<>();
        translations.add(question.getWrongA());
        translations.add(question.getWrongB());
        translations.add(question.getWrongC());
        translations.add(question.rightAnswer());

        int index1 = rand.nextInt(randomVocabLists.size())
        String answer1 = randomVocabLists.get(index1);
        randomVocabLists.remove(index1);

        int index2 = rand.nextInt(randomVocabLists.size())
        String answer2 = randomVocabLists.get(index2);
        randomVocabLists.remove(index2);

        int index3 = rand.nextInt(randomVocabLists.size())
        String answer3 = randomVocabLists.get(index3);
        randomVocabLists.remove(index3);

        int index4 = rand.nextInt(randomVocabLists.size())
        String answer4 = randomVocabLists.get(index4);
        randomVocabLists.remove(index4);

        view.printMessage ("Wie kann" + question.getVocab() + " richtig übersetzt werden? \n " +
            "1 - " + answer1 + ",\n " + // hier wird ja auch nur die Translations übergeben, gar nicht der String
            "2 - " + answer2 + "oder \n " +
            "2 - " + answer3 + "oder \n " +
            "3 - " + answer4 + "?");
    }

    public boolean answerQuestion(String answer, Translation rightAnswer) {

        List<String> translations = rightAnswer.getTranslations ();

        for (String translation : translations) {
            if (answer.equals(translation)) {
                return true;
            }
        }

        return false;
    }



}
