package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Request;

import java.util.List;

public interface RequestDao {

    void createRequest(Request request);

    Request getRequestById(Long requestId);

    void updateRequest(Request request);

    List<Request> getAllRequests();

    void deleteRequest(Request request);

}
