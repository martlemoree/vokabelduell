package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.game_management.export.Status;
import de.htwberlin.kba.user_management.export.User;

public class Request {

    private long requestId;
    private Status requestStatus;
    private User requester;
    private User receiver;

    public Request(long requestId, Status requestStatus, User requester, User receiver) {
        this.requestId = requestId;
        this.requestStatus = requestStatus;
        this.requester = requester;
        this.receiver = receiver;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public Status getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(Status requestStatus) {
        this.requestStatus = requestStatus;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
