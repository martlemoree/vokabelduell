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

    // TODO: Constructor-Injection?
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

                // für jeden user in der Liste den UserName ausgeben
                List<User> users = userService.getUserListWOcurrentUser(currentUser.getUserId());
                for (User user : users) {
                    view.printMessage(currentUser.getUserName() + "\n");
                }

                String userName = view.userInputString();
                // TODO DAO Spieler anhand des Benutzernamens auswählen
                User receiver = userService.getUserByUserName(userName); // diese Methode gibt es noch nicht

                Request request = requestService.createRequest(1L, currentUser, receiver);
                // TODO muss das hier dynamisch sein? Braucht es überhaupt einen Rückgabewert?

                view.printMessage("Wenn dein:e Gegner:in die Anfrage angenommen hat, kann das Spiel losgehen!");

            }

            // Anfragen verwalten
            if (input == 2) {

                // TODO Was tun bei der 6?
                view.printMessage("Gib den Benutzernamen des:r Nutzers:in ein, dessen Anfrage du annehmen oder ablehnen möchtest oder '6' zur Rückkehr ins Hauptmenü!");
                // Alle Anfragen ausgeben, wo der currentUser involviert ist, die noch nicht abgelehnt wurden
                List<Request> requests = requestService.getRequestsForCurrentUser(currentUser);
                for (Request request : requests) {
                    if (request.getRequestStatus() != Status.REJECTED) {
                        view.printMessage(request.getRequester().getUserName() + "\n");
                    }
                }

                String userOrMenu = view.userInputString();

                for (Request request : requests) {
                    // Passt das hier so?
                    if (userOrMenu.equals(request.getRequester().getUserName())) {
                        // Status der Anfrage ändern
                        // TODO Was tun bei der 6?
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
                // TODO Man kann nur dann weiterspielen wenn man dran ist :)
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
                vocabListManagement();
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
                        break; // TODO Funktioniert das hier so? --> Ich möchte zurück zum Eingangs-Menü
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
            view.printMessage("Wie lautet dein Benutzername?");
            String userName = view.userInputString();
            view.printMessage("Gib dein Passwort ein.");
            String password = view.userInputString();

            // TODO DAO Überprüfung der Anmeldedaten + Übergabe des Users
            return currentUser;

        }
        // Registrieren
        else if (firstInput == 2) {
            view.printMessage("Wie soll dein Benutzername lauten");
            String userName = view.userInputString();
            // TODO DAO der Benutzername muss eindeutig sein!
            view.printMessage("Gib dein Passwort ein.");
            String password = view.userInputString();

            view.printMessage("Das ist deine einmalige User Id:");

            return userService.createUser(userName, password);
        }
        return null;
    }
    public void playGame(Game game, User currentUser) {

        List<Question> questions = null;
        List<Round> rounds = game.getRounds();

        // if game has just been created or the last round was played by both players a new round must be initiated
        if (rounds.isEmpty() || !rounds.get(rounds.size() - 1).getisPlayedByTwo()) {
            questions = startNewRound(game);

            for (int i = 0; i<4; i++) {
                askQuestions(game, currentUser, questions, i);
            }
        } else {
            // play the last existing round
            // sind die Runde und ihre Fragen irgendwie connected?
            // eigentlich muss die Runde ihre FRagen kennen
            // momentan kennen nur die FRagen die Runde
        }
        // Herausforderung annehmen
        // Liste auswählen
        // Erste Runde erste FRagen beantworten
        // Herausforderer beantwortet die Fragen aus der ersten runde der liste die die person ausgewählt hat, die die fragen ausgweählt hat
        // dann beginnt der herausforderer die nächste runde und wählt eine liste aus
        // dann spielt der Receiver die Runde die der Requester begonnen hat mit der ausgewählten liste
        // dann beginnt der receiver die nächste runde und sucht eine liste aus

        // DIe Runden müssen nicht jedes Mal erstellt und hochgezählt werden, sondern NUR, wenn der currentUser der Receiver des Games ist, denn der fängt an
        // ODER ?!

        // Ansonsten muss die Runde weitergespielt werden, die vom Receiver schon begonnen wurde, UND NUR DIE KÖNNEN AUCH WEITERGESPIELT WERDEN; SONST MUSS MAN WARTEN



    }

    public void askQuestions(Game game, User currentUser, List<Question> questions, int i) {
        askQuestion(questions.get(i));
        // user answers question
        String answer = view.userInputString();
        // is answer correct?
        boolean rightOrWrong = answeredQuestion(answer, questions.get(i).getRightAnswer());

        // Punkte berechnen
        int points;
        if (rightOrWrong) {
            points = 500;
            view.printMessage("Super, das ist richtig!");
        } else {
            points = -200;
            view.printMessage("Das ist leider falsch. Die richtige Antwort lautet " + questions.get(i).getRightAnswer());
        }

        // TODO ist die Methode im gameService richtig oder muss die hier hin? Welche Methoden gehören überhaupt noch in den Service?
        gameService.calculatePoints(game, currentUser, points);

        /*
        Die Question hat die Felder
        private boolean correctAnsweredRequester;
        private boolean correctAnsweredReceiver;

        das bringt gefühlt gar nichts, wenn die Runde nicht ihre Fragen kennt
         */
    }

    // user is asked question

    public List<Question> startNewRound(Game game) {
        List<Round> rounds = new ArrayList<>();
        Round round = roundService.createRound(1L, game, 1);
        rounds.add(round);
        // der list der round des games hinzufügen
        game.setRounds(rounds);

        // get 3 random vocablists to choose from
        List<VocabList> randomVocabLists = vocabListService.getRandomVocabLists();

        // user chooses vocablist for round
        VocabList chosenVocabList = chooseVocablist(randomVocabLists);

        // generate Questions
        List<Question> questions = new ArrayList<>();
        Question question1 = questionService.createQuestion(1L, round, chosenVocabList);
        Question question2 = questionService.createQuestion(1L, round, chosenVocabList);
        Question question3 = questionService.createQuestion(1L, round, chosenVocabList);
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        return questions;
    }
    public void changeRequestStatus(Request request, Status status) {

        // TODO Ist das mit dem setter hier so richtig oder muss das über den Service laufen?
        request.setRequestStatus(status);

        if (status == Status.ACCEPTED) {
            // new game is created and starts
            playGame(gameService.createGame(1L, request.getRequester(), request.getReceiver()), request.getReceiver());
        } else if (status == Status.REJECTED) {
            view.printMessage("Die Anfrage wurde abgelehnt.");
        }
    }

    public void vocabListManagement() throws FileNotFoundException {
        // TODO: Muss ich mich hier irgendwie um die Exception kümmern?
        int inputVocabListManagementMenu;
        do {
            view.printMessage("Was möchtest du tun? \n " +
                    "1 - Neue Vokabelliste hinzufügen \n " +
                    "2 - Vokabelliste bearbeiten \n " +
                    "3 - Vokabelliste löschen \n " +
                    "4 - Rückkehr ins Hauptmenü");

            inputVocabListManagementMenu = view.userInputInt();

            // Neue Vokabelliste hinzufügen
            if (inputVocabListManagementMenu == 1) { // TODO: Antje: So richtig verstanden?
                view.printMessage("Gib den Dateipfad der neuen Vokabelliste ein.");
                String path = view.userInputString();
                String text = vocabListService.readFile(path);
                vocabListService.createVocabList(text);
            } else if (inputVocabListManagementMenu == 2) { // Vokabelliste bearbeiten
                view.printMessage("Gib den Namen der zu bearbeitenden Vokabelliste ein.");
                String input = view.userInputString();

                List<VocabList> vocabLists = vocabListService.getVocabLists();
                for (VocabList vocabList : vocabLists) {
                    // TODO: hierfür könnte die equals und die hash methode der VocabList angepasst werden
                    if (vocabList.getName().equals(input)) {
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
                            // TODO: Inwiefern können die Vokabeln selbst bearbeitet werden? (abgesehen von hinzufügen und löschen)


                            view.printMessage("Was möchtest du tun? \n " +
                                    "1 - Vokabel der Vokabelliste hinzufügen \n " +
                                    "2 - Vokabel aus der Vokabelliste entfernen \n " +
                                    "3 - Rückkehr ins Hauptmenü");
                            int inputEditVocab = view.userInputInt();

                            if (inputEditVocab == 1) {
                                // TODO Antje: Was wenn eine Vokabel mit Synonymen hinzugefügt werden soll?
                                view.printMessage("Gib die neue Vokabel ein!");
                                List<String> newVocabsString = new ArrayList<>();
                                newVocabsString.add(view.userInputString());

                                // TODO Antje: Was wenn eine Übersetzung mit Synonymen hinzugefügt werden soll?
                                view.printMessage("Gib die Übersetzung der neuen Vokabel ein!");
                                List<String> newTranslationsString = new ArrayList<String>(Arrays.asList(view.userInputString()));

                                Translation newTranslation = translationService.createTranslation(1L, newTranslationsString);
                                List<Translation> newTranslations = new ArrayList<Translation>(Arrays.asList(newTranslation));

                                Vocab newVocab = vocabService.createVocab(1L, newVocabsString, newTranslations);
                                vocabListService.addVocab(vocabList, newVocab);
                            } else if (inputEditVocab == 2) {
                                view.printMessage("Gib den Namen der zu entfernenden Vokabel ein.");
                                String vocabName = view.userInputString();

                                List<Vocab> vocabs = vocabList.getVocabs();
                                // TODO DAO das hier gehört wahrscheinlich eher in die DAO als getByVocab oder sowas?
                                for (Vocab vocab : vocabs) {
                                    List<String> vocabStrings = vocab.getVocabs();
                                    for (String vocabString : vocabStrings) {
                                        if (vocabName.equals(vocabString)) {
                                            vocabListService.removeVocab(vocabList, vocab);
                                        }
                                    }

                                }


                            } else if (inputEditVocab == 3) { // Rückkehr ins Hauptmenü
                                break;
                            }

                        } else if (inputEditVocabList == 5) { // Rückkehr ins Hauptmenü
                            inputVocabListManagementMenu = 4;
                        }
                    }
                }
            } else if (inputVocabListManagementMenu == 3) { // Vokabelliste löschen
                view.printMessage("Gib den Namen der zu löschenden Vokabelliste ein.");
                String input = view.userInputString();

                List<VocabList> vocabLists = vocabListService.getVocabLists();
                for (VocabList vocabList : vocabLists) {
                    // TODO: hierfür könnte die equals und die hash methode der VocabList angepasst werden
                    if (vocabList.getName().equals(input)) {
                        vocabListService.removeVocabList(vocabList);
                        view.printMessage("Die Vokabelliste " + input + " wurde gelöscht.");
                    }
                }
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
            // TODO exception ?
            return null;
        }
    }

    public void askQuestion(Question question) {

        Random rand = new Random();

        List<Translation> translations = new ArrayList<>();
        translations.add(question.getWrongA());
        translations.add(question.getWrongB());
        translations.add(question.getWrongC());
        translations.add(question.getRightAnswer());

        // TODO: Wäre schön das als Methode auszugliedern aber habe noch keine Möglichkeit gefunden wie :)
        int index1 = rand.nextInt(translations.size());
        List<String> translationStrings1 = translations.get(index1).getTranslations();
        int translationStringsindex1 = rand.nextInt(translationStrings1.size());
        String answer1 = translationStrings1.get(translationStringsindex1);
        translations.remove(index1);

        int index2 = rand.nextInt(translations.size());
        List<String> translationStrings2 = translations.get(index2).getTranslations();
        int translationStringsindex2 = rand.nextInt(translationStrings2.size());
        String answer2 = translationStrings2.get(translationStringsindex2);
        translations.remove(index2);

        int index3 = rand.nextInt(translations.size());
        List<String> translationStrings3 = translations.get(index3).getTranslations();
        int translationStringsindex3 = rand.nextInt(translationStrings3.size());
        String answer3 = translationStrings3.get(translationStringsindex3);
        translations.remove(index3);

        int index4 = rand.nextInt(translations.size());
        List<String> translationStrings4 = translations.get(index1).getTranslations();
        int translationStringsindex4 = rand.nextInt(translationStrings4.size());
        String answer4 = translationStrings4.get(translationStringsindex4);
        translations.remove(index4);

        view.printMessage("Wie kann" + question.getVocab() + " richtig übersetzt werden? \n " +
                "1 - " + answer1 + ",\n " + // hier wird ja auch nur die Translations übergeben, gar nicht der String
                "2 - " + answer2 + "oder \n " +
                "2 - " + answer3 + "oder \n " +
                "3 - " + answer4 + "?");
    }

    public boolean answeredQuestion(String answer, Translation rightAnswer) {

        List<String> translations = rightAnswer.getTranslations();

        for (String translation : translations) {
            if (answer.equals(translation)) {
                return true;
            }
        }

        return false;
    }
}
