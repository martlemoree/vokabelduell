package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

import static de.htwberlin.kba.game_management.export.Status.*;

@Service
public class RequestServiceImpl implements RequestService {

    private RequestDao requestDao;

    @Autowired
    public RequestServiceImpl(RequestDao requestDao) {
        this.requestDao = requestDao;
    }

    // constructor without parameters needed for mockito testing
    public RequestServiceImpl(){}

    @Override
    @Transactional
    public void changeStatus(Boolean accept, Request request) {
        if (accept) {
            request.setRequestStatus(ACCEPTED);
        } else {
            request.setRequestStatus(REJECTED);
        }
        requestDao.updateRequest(request);
    }

    @Override
    @Transactional
    public Request createRequest(User requester, User receiver) throws SQLException {
        Request request = new Request(PENDING, requester, receiver);
        requestDao.createRequest(request);
        return request;
    }

    @Override
    @Transactional
    public List<Request> getPendingRequestsForCurrentUser(User user) {

        return requestDao.getAllPendingRequests();
    }

    @Override
    @Transactional
    public Request getRequestById(Long Id) {
        return requestDao.getRequestById(Id);
    }

    @Override
    @Transactional
    public List<Request> getAllRequests() {
        return requestDao.getAllRequests();
    }
}
