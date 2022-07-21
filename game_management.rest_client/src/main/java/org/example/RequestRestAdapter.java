package org.example;

import de.htwberlin.kba.game_management.export.Request;
import de.htwberlin.kba.user_management.export.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class RequestRestAdapter {
    private RestTemplate restTemplate;

    @Autowired
    public RequestRestAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    final String localhost = "http://localhost:8080/request/";

    public Request createRequest(User requester, User receiver){
        String reqName = requester.getUserName();
        String recName = receiver.getUserName();
        final String URL = localhost + "create/" + reqName + "/" + recName;
        return restTemplate.exchange(URL, HttpMethod.POST, null, Request.class).getBody();
    }

    public void changeStatus(Boolean accept, Request request) {
        String reqId = String.valueOf(request.getRequestId());
        String accept_str = null;

        if (accept) {
            accept_str = "1";
        } else {
            accept_str = "0";
        }

        final String URL = localhost + "changeStatus/" + reqId + "/" + accept_str;
        String result = restTemplate.exchange(URL, HttpMethod.PUT, null, String.class).getBody();
    }

    public List<Request> getPendingRequestsForCurrentUser(User user) {
        String userName = user.getUserName();
        final String URL = localhost + "pendingRequests" + "/" + userName;
        return restTemplate.exchange(URL, HttpMethod.GET, null, List.class).getBody();
    }

    public List<Request> getAllRequests() {
        final String URL = localhost + "all";
        return restTemplate.exchange(URL, HttpMethod.GET, null, List.class).getBody();
    }


}
