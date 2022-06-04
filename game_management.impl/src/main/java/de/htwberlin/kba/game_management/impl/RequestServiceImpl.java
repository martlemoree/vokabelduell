package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static de.htwberlin.kba.game_management.export.Status.*;

@Service
public class RequestServiceImpl implements RequestService {

    @Override
    public void changeStatus(Boolean accept, Request request) {

        // TODO DAO
    }

    @Override
    public void createRequest(Long requestId, User requester, User receiver) {
        new Request(requestId, PENDING, requester, receiver);
    }

    public List<Request> getPendingRequestsForCurrentUser(User user) {
        // TODO DAO
        // Javadoc: gives back all requests with status pending, where given user is the receiver
        List<Request> requests = new ArrayList<>();
        return requests;
    }

}
