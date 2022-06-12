package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Request;
import de.htwberlin.kba.game_management.export.Status;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class RequestDaoImpl implements RequestDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createRequest(Request request) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(request);
        entityTransaction.commit();
    }

    @Override
    public Request getRequestById(Long requestId) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Request request = entityManager.find(Request.class, requestId);
        entityTransaction.commit();
        if (request == null) {
            throw new EntityNotFoundException("Can't find Request with requestId" + requestId);
        } else {
            return request;
        }
    }

    @Override
    public void updateRequest(Request request) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(request);
        entityTransaction.commit();
    }

    @Override
    public List<Request> getAllRequests() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        TypedQuery<Request> query = entityManager.createQuery("FROM Request AS requests", Request.class);
        List<Request> allRequests = query.getResultList();
        entityTransaction.commit();
        return allRequests;
    }

    @Override
    public List<Request> getAllPendingRequests() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        TypedQuery<Request> query = entityManager.createQuery("FROM Request AS requests WHERE requests.requestStatus = 'PENDING'", Request.class);
        List<Request> allPendingRequests = query.getResultList();
        entityTransaction.commit();
        return allPendingRequests;
    }

    @Override
    public void deleteRequest(Request request) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(request);
        entityTransaction.commit();
    }

}
