package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "getAllRequests", query = "FROM Request AS requests"),
        @NamedQuery(name = "getAllPendingRequests", query = "FROM Request AS requests WHERE requests.requestStatus = 'PENDING'")
})
@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long requestId;

    @Column(name = "request_status")
    private Status requestStatus;

    @OneToOne(cascade = CascadeType.MERGE) // because https://stackoverflow.com/questions/2302802/how-to-fix-the-hibernate-object-references-an-unsaved-transient-instance-save
    @JoinColumn(name = "user_requester", referencedColumnName = "user_id")
    private User requester;

    @OneToOne(cascade = CascadeType.MERGE) // because https://stackoverflow.com/questions/2302802/how-to-fix-the-hibernate-object-references-an-unsaved-transient-instance-save
    @JoinColumn(name = "user_receiver", referencedColumnName = "user_id")
    private User receiver;

    @Version
    private Integer version;

    public Request(Status requestStatus, User requester, User receiver) {
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
