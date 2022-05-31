package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.game_management.export.Status;
import de.htwberlin.kba.user_management.export.User;

import javax.persistence.*;

@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long requestId;

    @Column(name = "request_status")
    private Status requestStatus;

    @OneToOne
    @Column(name = "user_requester")
    private User requester;

    @OneToOne
    @Column(name = "user_receiver")
    private User receiver;

    public Request(Long requestId, Status requestStatus, User requester, User receiver) {
        this.requestId = requestId;
        this.requestStatus = requestStatus;
        this.requester = requester;
        this.receiver = receiver;
    }

    public Request() {

    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
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
