package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Request;
import de.htwberlin.kba.game_management.export.RequestService;
import de.htwberlin.kba.game_management.export.Status;
import de.htwberlin.kba.user_management.export.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class RequestServiceTest {

    //optional

    private RequestService service;

    @Before
    public void setUp(){
        this.service = new RequestServiceImpl();
    }
/*
    @DisplayName("checks whether a request is created correctly.")
    @Test
    public void testCreateRequest(){
        //1. Arrange
        User martin = new User(234567L,"MartinTheBrain", "lol123");
        User stella = new User(234568L,"stellomello", "123lol");

        Long requestId = 123456L;
        Status requestStatus = Status.PENDING;
        User requester = martin;
        User receiver = stella;

        //2. Act
        Request req = service.createRequest(requestId, requestStatus, requester, receiver);

        //3. Assert
        Assert.assertNotNull(req);
        Assert.assertEquals(requestId, req.getRequestId());
        Assert.assertEquals(requestStatus, req.getRequestStatus());
        Assert.assertEquals(requester, req.getRequester());
        Assert.assertEquals(receiver, req.getReceiver());
    }

    @DisplayName("tests whether the Status is changing to ACCEPTED when the request is accepted.")
    @Test
    public void testAcceptRequest(){
        //1. Arrange
        User martin = new User(234567L,"MartinTheBrain", "lol123");
        User stella = new User(234568L,"stellomello", "123lol");

        Long requestId = 123456L;
        Status requestStatus = Status.PENDING;
        User requester = martin;
        User receiver = stella;

        //2. Act
        Request req = service.createRequest(requestId, requestStatus, requester, receiver);
        service.changeStatus(Boolean.TRUE, req);

        //3. Assert
        Assert.assertEquals(Status.ACCEPTED, req.getRequestStatus() );
    }

    @DisplayName("tests whether the Status is changing to REJECTED when the request is rejected.")
    @Test
    public void testRejectRequest(){
        //1. Arrange
        User martin = new User(234567L,"MartinTheBrain", "lol123");
        User stella = new User(234568L,"stellomello", "123lol");

        Long requestId = 123456L;
        Status requestStatus = Status.PENDING;
        User requester = martin;
        User receiver = stella;

        //2. Act
        Request req = service.createRequest(requestId, requestStatus, requester, receiver);
        service.changeStatus(Boolean.FALSE, req);

        //3. Assert
        Assert.assertEquals(Status.REJECTED, req.getRequestStatus() );
    }

 */
}
