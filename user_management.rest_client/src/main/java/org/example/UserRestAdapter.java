package org.example;

import de.htwberlin.kba.configuration.RestTemplateResponseErrorHandler;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserNotFoundException;
import de.htwberlin.kba.user_management.export.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserRestAdapter implements UserService {

    private RestTemplate restTemplate;

    final String localhostUser = "http://localhost:8080/user/";

    @Autowired
    public UserRestAdapter(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate =  restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    public List<User> getUserListWOcurrentUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        final String URL = localhostUser + "all/" + user.getUserName();
        return restTemplate.exchange(URL, HttpMethod.GET, requestEntity, List.class).getBody();
    }

    public List<User> getUserList() {
        final String URL = localhostUser + "all";
        return restTemplate.exchange(URL, HttpMethod.GET, null, List.class).getBody();
    }

    public void changePassword(String userName, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        final String URL = localhostUser + "edit/" + userName + "/" + password;
        restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, void.class).getBody();
    }

    public User createUser(String name, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        final String URL = localhostUser + "create";
        return restTemplate.exchange(URL, HttpMethod.POST, requestEntity, User.class).getBody();
    }

    public User getUserByUserName(String userName) {
        final String URL = localhostUser + userName;
        HttpEntity<String> httpEntity = new HttpEntity<>(userName);
        return restTemplate.exchange(URL, HttpMethod.GET, httpEntity, User.class).getBody();
    }

    @Override
    public void removeUserName(String name) throws UserNotFoundException {
        final String URL = localhostUser + "delete/" + name;
        restTemplate.delete(URL);
    }

    public boolean removeUser(User user) {
        final String URL = localhostUser + "delete/" + user.getUserName();
        restTemplate.delete(URL);
        return true;
    }

    public User getUserById(Long id) {
        return null;
    }

    public boolean removeUserId(Long id) {
        return false;
    }
}
