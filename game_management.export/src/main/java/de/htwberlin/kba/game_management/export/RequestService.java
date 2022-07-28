package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;

import java.util.List;

public interface RequestService {

    /**
     * Processes the answer from a user to a request.
     * Changes status of request accordingly.
     *
     * @param accept gives information about whether the request was accepted (true) or rejected (false)
     * @param request is the object that should be updated
     */
    void changeStatus(Boolean accept, Request request) throws CustomOptimisticLockExceptionGame;

    /**
     * adds a new game request.
     *
     * @param requester is the user who created the request
     * @param receiver  is the user who receives the request
     * @return new request
     */
    Request createRequest(User requester, User receiver);

    /**
     * gives back all requests with status pending, where given user is the receiver
     * @param user current user
     * @return list of requests
     */
    List<Request> getPendingRequestsForCurrentUser(User user);

    /**
     * search a request with the given id
     * @param Id that should be searched
     * @return the request with the given id
     */
    Request getRequestById(Long Id) throws CustomObjectNotFoundException;

    // TODO löschen nur für testzwecke
    List<Request> getAllRequests();
}
