package de.htwberlin.kba.game_management.export;

public interface RequestService {
    /**
     * Processes the answer from a user to a request.
     * Changes status of request accordingly.
     * If request is accepted, starts new game.
     * @param gives information about whether the request was accepted (1) or rejected (0)
     */
    public void changeStatus(Boolean accept);

    /**
     * Request is pending in the beginning, or accepted or rejected when the receiver answers the request
     */
    public enum Category {
        PENDING
        ACCEPTED
        REJECTED
    }
}
