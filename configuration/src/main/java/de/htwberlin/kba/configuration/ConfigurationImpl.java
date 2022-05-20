package de.htwberlin.kba.configuration;

import de.htwberlin.kba.game_management.impl.QuestionServiceImpl;
import de.htwberlin.kba.game_management.impl.RequestServiceImpl;
import de.htwberlin.kba.game_management.impl.RoundServiceImpl;
import de.htwberlin.kba.game_ui.impl.GameUiController;
import de.htwberlin.kba.game_ui.impl.GameView;

import de.htwberlin.kba.game_management.impl.GameServiceImpl;
import de.htwberlin.kba.user_management.impl.UserServiceImpl;
import de.htwberlin.kba.vocab_management.impl.TranslationServiceImpl;
import de.htwberlin.kba.vocab_management.impl.VocabListServiceImpl;
import de.htwberlin.kba.vocab_management.impl.VocabServiceImpl;

public class ConfigurationImpl {

    public static void main(String[] args) {
        GameUiController controller = registerComponents();
        controller.run();
    }

    // Ist das hier die DI von Hand die wir dann nicht mehr brauchen???
    private static GameUiController registerComponents() {
        GameServiceImpl gameService = new GameServiceImpl();
        UserServiceImpl userService = new UserServiceImpl();
        VocabServiceImpl vocabServiceImpl = new VocabServiceImpl();
        VocabListServiceImpl vocabListServiceImpl = new VocabListServiceImpl();
        RoundServiceImpl roundServiceImpl = new RoundServiceImpl();
        QuestionServiceImpl questionServiceImpl = new QuestionServiceImpl();
        TranslationServiceImpl translationServiceImpl = new TranslationServiceImpl();
        RequestServiceImpl requestServiceImpl = new RequestServiceImpl ();
        // haben wir sowas irgendwo?
        // falls ja, muss das in \configuration\src\main\resources\beans.xml auch festgehalten werden

        // QuestionServiceImpl braucht VocabListServiceImpl, reicht das schon? --> wo müssen sich dann variable&setter befinden? nur im impl oder auch im interface?
        // bidService.setItemService(itemService);
        GameView auctionView = new GameView();
        //  auctionController.setBidService(bidService);
      //  auctionController.setView(auctionView);
        return new GameUiController(auctionView);
    }
}
