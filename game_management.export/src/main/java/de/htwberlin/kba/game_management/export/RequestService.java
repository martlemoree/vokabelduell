package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;

public interface RequestService {

    /**
     * Processes the answer from a user to a request.
     * Changes status of request accordingly.
     * If request is accepted, starts new game.
     *
     * @param accept gives information about whether the request was accepted (true) or rejected (false)
     * @param request is the object that should be updated
     */
    void changeStatus(Boolean accept, Request request);

    /**
     * adds a new game request.
     *
     * @param requestId     is the unique identifier of the request
     * @param requester     is the user who created the request
     * @param receiver      is the user who receives the request
     * @return a new game request
     */
    Request createRequest(Long requestId, User requester, User receiver);

}
