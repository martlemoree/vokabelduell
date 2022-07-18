package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;

import javax.transaction.Transactional;
import java.util.List;

public interface RequestService {

    /**
     * Processes the answer from a user to a request.
     * Changes status of request accordingly.
     *
     * @param accept gives information about whether the request was accepted (true) or rejected (false)
     * @param request is the object that should be updated
     */
    void changeStatus(Boolean accept, Request request);

    /**
     * adds a new game request.
     *
     * @param requester is the user who created the request
     * @param receiver  is the user who receives the request
     * @return
     */
    Request createRequest(User requester, User receiver);

    /**
     * gives back all requests with status pending, where given user is the receiver
     * @param user current user
     * @return list of requests
     */
    List<Request> getPendingRequestsForCurrentUser(User user);

    @Transactional
    Request getRequestById(Long Id);

    @Transactional
    List<Request> getAllRequests();
}
