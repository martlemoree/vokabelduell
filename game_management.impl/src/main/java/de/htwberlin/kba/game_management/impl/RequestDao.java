package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.CustomLockException;
import de.htwberlin.kba.game_management.export.CustomObjectNotFoundException;
import de.htwberlin.kba.game_management.export.Request;

import java.util.List;

public interface RequestDao {

    void createRequest(Request request);

    Request getRequestById(Long requestId) throws CustomObjectNotFoundException;

    void updateRequest(Request request) throws CustomLockException;

    List<Request> getAllRequests();

    void deleteRequest(Request request);

    List<Request> getAllPendingRequests();

}
