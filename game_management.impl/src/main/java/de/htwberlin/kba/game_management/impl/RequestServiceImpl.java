package de.htwberlin.kba.game_management.impl;

import com.fasterxml.classmate.Annotations;
import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
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
    public void changeStatus(Boolean accept, Request request) throws CustomOptimisticLockExceptionGame {
        if (accept) {
            request.setRequestStatus(ACCEPTED);
        } else {
            request.setRequestStatus(REJECTED);
        }
        requestDao.updateRequest(request);
    }

    @Override
    @Transactional
    public Request createRequest(User requester, User receiver) {
        Request request = new Request(PENDING, requester, receiver);
        requestDao.createRequest(request);
        return request;
    }

    @Override
    @Transactional
    public List<Request> getPendingRequestsForCurrentUser(User user) {

        List<Request> requests = requestDao.getAllRequests();

        List<Request> deleteCandidates = new ArrayList<>();

        // Alle Anfragen ausgeben, wo der currentUser der Receiver ist, die noch nicht bearbeitet wurden
        // Ich möchte alle Anfragen aus der Liste löschen, wo der current user NICHT der Receiver ist und der Status
        // Ich möchte alle Anfragen aus der Liste löschen, wo der status nicht pending nicht

        for (Request request : requests) {
            if (!request.getReceiver().getUserName().equals(user.getUserName()) || request.getRequestStatus() != PENDING) { //
                // Hier sage ich: der status ist nicht pending? -- löschen
                // der receiver ist nicht der user? -- löschen
                deleteCandidates.add(request);
            }
        }

        for (Request deleteCandidate : deleteCandidates) {
            requests.remove(deleteCandidate);
        }

        return requests;
    }

    @Override
    @Transactional
    public Request getRequestById(Long Id) throws CustomObjectNotFoundException {
        return requestDao.getRequestById(Id);
    }

    @Override
    @Transactional
    public List<Request> getAllRequests() {
        return requestDao.getAllRequests();
    }
}
