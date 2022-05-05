package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Request;
import de.htwberlin.kba.game_management.export.RequestService;
import de.htwberlin.kba.game_management.export.Status;
import de.htwberlin.kba.user_management.export.User;

public class RequestServiceImpl implements RequestService {

    public void changeStatus(Boolean accept) {
    }

    @Override
    public Request createRequest(Long requestId, Status requestStatus, User requester, User receiver) {

        return null;
    }
}
