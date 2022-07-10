package org.example;

import de.htwberlin.kba.user_management.export.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserRestAdapter {

    private RestTemplate restTemplate;

    final String localhostUser = "http://localhost:8080/user/";

    @Autowired
    public UserRestAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getUserListWOcurrentUser(String userName) {
        final String URL = localhostUser + "all/" + userName;
        HttpEntity<String> httpEntity = new HttpEntity<>(userName);
        return restTemplate.exchange(URL, HttpMethod.GET, httpEntity, List.class).getBody();
    }

    public List<User> getUserList() {
        final String URL = localhostUser + "all";
        return restTemplate.exchange(URL, HttpMethod.GET, null, List.class).getBody();
    }

    public User getUserByUserName(String userName) {
        final String URL = localhostUser + userName;
        HttpEntity<String> httpEntity = new HttpEntity<>(userName);
        return restTemplate.exchange(URL, HttpMethod.GET, httpEntity, User.class).getBody();
    }

    public User createUser(User user) {
        final String URL = localhostUser + "create";
        HttpEntity<User> httpEntity = new HttpEntity<>(user);
        return restTemplate.exchange(URL, HttpMethod.POST, httpEntity, User.class).getBody();
    }

    public String changePassword(String userName, String password) {
        final String URL = localhostUser + "edit/" + userName;
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(multiValueMap);
        return restTemplate.exchange(URL, HttpMethod.PUT, httpEntity, String.class).getBody();
    }

    public Void removeUser(String userName) {
        final String URL = localhostUser + "delete/" + userName;
        HttpEntity<String> httpEntity = new HttpEntity<>(userName);
        return restTemplate.exchange(URL, HttpMethod.DELETE, httpEntity, Void.class).getBody();
    }
}