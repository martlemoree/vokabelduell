package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Request;
import de.htwberlin.kba.game_management.export.Status;
import de.htwberlin.kba.user_management.export.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RequestDaoImpl implements RequestDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createRequest(Request request) {
        entityManager.persist(request);
    }

    @Override
    public Request getRequestById(Long requestId) {
        Request request = entityManager.find(Request.class, requestId);
        if (request == null) {
            throw new EntityNotFoundException("Can't find Request with requestId" + requestId);
        } else {
            return request;
        }
    }

    @Override
    public void updateRequest(Request request) {
        entityManager.merge(request);
    }

    @Override
    public List<Request> getAllRequests() {
        TypedQuery<Request> query = entityManager.createQuery("SELECT requests FROM Request AS requests", Request.class);
        List<Request> allRequests = query.getResultList();
        return allRequests;
    }

    // TODO hier sollen einfach nur alle pending requests zurückgegeben werden - kriege ich nicht hin lol
    @Override
    public List<Request> getAllPendingRequests() {
        Status stat_pending = Status.PENDING;
       TypedQuery<Request> query = entityManager.createQuery("SELECT r.requestId, r.receiver, r.requester, r.requestStatus FROM Request r WHERE r.requestStatus = stat_pending", Request.class);
        List<Request> allRequests = query.getResultList();
        return allRequests;
    }

    @Override
    public void deleteRequest(Request request) {
        entityManager.remove(request);
    }

}
