package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class RoundRestAdapter {
    private RestTemplate restTemplate;

    @Autowired
    public RoundRestAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    final String localhost = "http://localhost:8080/round/";



}
