package de.htwberlin.kba.configuration;

import de.htwberlin.kba.game_management.export.GameService;
import de.htwberlin.kba.game_management.export.QuestionService;
import de.htwberlin.kba.game_management.export.RoundService;
import de.htwberlin.kba.game_management.impl.QuestionServiceImpl;
import de.htwberlin.kba.game_management.impl.RequestServiceImpl;
import de.htwberlin.kba.game_management.impl.RoundServiceImpl;
import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import de.htwberlin.kba.vokabelduell_ui.impl.VokabellduellUiController;
import de.htwberlin.kba.vokabelduell_ui.impl.VokabelduellView;

import de.htwberlin.kba.game_management.impl.GameServiceImpl;
import de.htwberlin.kba.user_management.impl.UserServiceImpl;
import de.htwberlin.kba.vocab_management.impl.TranslationServiceImpl;
import de.htwberlin.kba.vocab_management.impl.VocabListServiceImpl;
import de.htwberlin.kba.vocab_management.impl.VocabServiceImpl;

public class ConfigurationImpl {

    public static void main(String[] args) {
        VokabellduellUiController controller = registerComponents();
        controller.run();
    }

    // Ist das hier die DI von Hand die wir dann nicht mehr brauchen???
    private static VokabellduellUiController registerComponents() {
        GameServiceImpl gameService = new GameServiceImpl();
        UserServiceImpl userService = new UserServiceImpl();
        VocabServiceImpl vocabService = new VocabServiceImpl();
        VocabListServiceImpl vocabListService = new VocabListServiceImpl();
        RoundServiceImpl roundService = new RoundServiceImpl();
        QuestionServiceImpl questionService = new QuestionServiceImpl();
        TranslationServiceImpl translationService = new TranslationServiceImpl();
        RequestServiceImpl requestService = new RequestServiceImpl ();
        // haben wir sowas irgendwo?
        // falls ja, muss das in \configuration\src\main\resources\beans.xml auch festgehalten werden

        // QuestionServiceImpl braucht VocabListServiceImpl, reicht das schon? --> wo müssen sich dann variable&setter befinden? nur im impl oder auch im interface?
        // bidService.setItemService(itemService);
        VokabelduellView vokabelduellView = new VokabelduellView();
        //  auctionController.setBidService(bidService);
      //  auctionController.setView(auctionView);
        return new VokabellduellUiController(vokabelduellView, vocabListService, roundService, questionService, gameService, userService);
    }
}
