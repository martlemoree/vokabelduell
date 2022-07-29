package org.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.htwberlin.kba.game_management.export.Request;
import de.htwberlin.kba.game_management.export.RequestService;
import de.htwberlin.kba.user_management.export.User;


@Service
public class RequestRestAdapter implements RequestService {
    private RestTemplate restTemplate;

    @Autowired
    public RequestRestAdapter(RestTemplate restTemplate){
        this.restTemplate =  restTemplate;
    }
    final String localhost = "http://localhost:8080/request/";

    public Request createRequest(User requester, User receiver){
        String reqName = requester.getUserName();
        String recName = receiver.getUserName();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        final String URL = localhost + "create/" + reqName + "/" + recName;
        return restTemplate.exchange(URL, HttpMethod.POST, requestEntity, Request.class).getBody();
    }

    public void changeStatus(Boolean accept, Request request) {
        String reqId = String.valueOf(request.getRequestId());
        String accept_str = null;

        if (accept) {
            accept_str = "1";
        } else {
            accept_str = "0";
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        final String URL = localhost + "changeStatus/" + reqId + "/" + accept_str;
        String result = restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, String.class).getBody();
    }

    public List<Request> getPendingRequestsForCurrentUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        String userName = user.getUserName();
        final String URL = localhost + "pendingRequests" + "/" + userName;

        ParameterizedTypeReference<List<Request>> typeRef = new ParameterizedTypeReference<List<Request>>() {
        };

        return restTemplate.exchange(URL, HttpMethod.GET, requestEntity, typeRef).getBody();
    }

    @Override
    public Request getRequestById(Long Id) {
        return null;
    }

    public List<Request> getAllRequests() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        final String URL = localhost + "all";
        return restTemplate.exchange(URL, HttpMethod.GET, requestEntity, List.class).getBody();
    }


}
