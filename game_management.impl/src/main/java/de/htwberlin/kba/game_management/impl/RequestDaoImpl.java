package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.CustomLockException;
import de.htwberlin.kba.game_management.export.CustomObjectNotFoundException;
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
    public Request getRequestById(Long requestId) throws CustomObjectNotFoundException {
        Request request = null;
        try {
            request = entityManager.find(Request.class, requestId);
        } catch (NoResultException e) {
            throw new CustomObjectNotFoundException("Can't find Request with requestId" + requestId);
        }
        return request;
    }

    @Override
    public void updateRequest(Request request) throws CustomLockException {
        try {
            entityManager.merge(request);
        } catch (OptimisticLockException e) {
            throw new CustomLockException("Das Update der Request muss noch einmal durchgeführt werden.");
        }

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
