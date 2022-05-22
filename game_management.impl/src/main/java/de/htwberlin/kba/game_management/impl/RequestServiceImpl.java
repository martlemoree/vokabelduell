package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import org.springframework.stereotype.Service;

import static de.htwberlin.kba.game_management.export.Status.*;

@Service
public class RequestServiceImpl implements RequestService {

    @Override
    public void changeStatus(Boolean accept, Request request, User requester, User receiver) {

        if (accept) {
            request.setRequestStatus(ACCEPTED);

            // start game
            GameService service = new GameServiceImpl();
            Game game = new Game (1L, requester, receiver);
            service.playGame(game, requester, receiver);
        } if (!accept) {
            request.setRequestStatus(REJECTED);
        }
    }

    @Override
    public Request createRequest(Long requestId, User requester, User receiver) {

        return new Request(requestId, PENDING, requester, receiver);
    }
}
