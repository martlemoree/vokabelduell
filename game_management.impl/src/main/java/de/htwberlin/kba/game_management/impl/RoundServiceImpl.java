package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.Round;
import de.htwberlin.kba.game_management.export.RoundService;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.VocabList;
import sun.jvm.hotspot.tools.SysPropsDumper;

import java.util.List;

public class RoundServiceImpl implements RoundService {

    @Override
    public VocabList chooseVocablist(List<VocabList> randomVocabLists) {
        // hier kommt die user-interaktion mit der Konsole hin aber ich weiß echt nicht mehr wie das geht :)

        String ausgabe = "Welche Vokabelliste möchtest du wählen? \n " +
                "1. " + randomVocabLists.get(0).getName() + ",\n " +
                "2. " + randomVocabLists.get(1).getName() + "oder \n " +
                "3. " + randomVocabLists.get(2).getName() + "?";

        if (ausgabe.contains("1")) { //hier wird der user input entgegengenommen, er kann "1", "2" oder "3" eingeben
            return randomVocabLists.get(0);
        } if (ausgabe.contains("2")) { //hier wird der user input entgegengenommen, er kann "1", "2" oder "3" eingeben
            return randomVocabLists.get(1);
        } if (ausgabe.contains("3")) { //hier wird der user input entgegengenommen, er kann "1", "2" oder "3" eingeben
            return randomVocabLists.get(2);
        } else {
            chooseVocablist(randomVocabLists); //geht das? (Gedanke: Einfach von vorne, da User-Eingabe so nicht funktioniert hat. Endlos loop wenn der user es nicht hinbekommt :)
        }
        return null;
    }

    @Override
    public Round createRound(Long roundId, Game game, User requester, User receiver, int currentRound) {
        return new Round(1L, game, requester, receiver, currentRound);
    }
}
