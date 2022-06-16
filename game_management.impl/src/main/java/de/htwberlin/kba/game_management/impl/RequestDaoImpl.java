package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Request;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
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
        TypedQuery<Request> query = entityManager.createNamedQuery("getAllRequests", Request.class);
        List<Request> allRequests = query.getResultList();
        return allRequests;
    }

    @Override
    public List<Request> getAllPendingRequests() {
        TypedQuery<Request> query = entityManager.createNamedQuery("getAllPendingRequests", Request.class);
        List<Request> allPendingRequests = query.getResultList();
        return allPendingRequests;
    }

    @Override
    public void deleteRequest(Request request) {
        entityManager.remove(request);
    }
}
