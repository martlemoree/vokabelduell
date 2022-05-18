package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Request;
import de.htwberlin.kba.game_management.export.RequestService;
import de.htwberlin.kba.game_management.export.Status;
import de.htwberlin.kba.user_management.export.User;

import static de.htwberlin.kba.game_management.export.Status.*;

public class RequestServiceImpl implements RequestService {

    @Override
    public void changeStatus(Boolean accept, Request request) {

        if (accept) {
            request.setRequestStatus(ACCEPTED);
        } if (!accept) {
            request.setRequestStatus(REJECTED);
        }
    }

    @Override
    public Request createRequest(Long requestId, User requester, User receiver) {

        return new Request(requestId, PENDING, requester, receiver);
    }
}
