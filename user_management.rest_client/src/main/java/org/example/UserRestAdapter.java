package org.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserAlreadyExistsException;
import de.htwberlin.kba.user_management.export.UserNotFoundException;
import de.htwberlin.kba.user_management.export.UserService;

@Service
public class UserRestAdapter implements UserService {

    private RestTemplate restTemplate;

    final String localhostUser = "http://localhost:8080/user/";

    @Autowired
    public UserRestAdapter(RestTemplate restTemplate){
        this.restTemplate =  restTemplate;
    }


    @Override
    public List<User> getUserListWOcurrentUser(User user) throws UserNotFoundException {
        final String URL = localhostUser + "all/" + user.getUserName();
        HttpEntity<String> httpEntity = new HttpEntity<>(user.getUserName());
        return restTemplate.exchange(URL, HttpMethod.GET, httpEntity, List.class).getBody();
    }

    public List<User> getUserList() {
        final String URL = localhostUser + "all";
        return restTemplate.exchange(URL, HttpMethod.GET, null, List.class).getBody();
    }


    public void changePassword(String userName, String password) {
        final String URL = localhostUser + "edit/" + userName;
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(multiValueMap);
        restTemplate.exchange(URL, HttpMethod.PUT, httpEntity, String.class).getBody();
    }

    @Override
    public User createUser(String name, String password) throws UserAlreadyExistsException {
        User user = new User(name, password);
        final String URL = localhostUser + "create";
        HttpEntity<User> httpEntity = new HttpEntity<>(user);
        return restTemplate.exchange(URL, HttpMethod.POST, httpEntity, User.class).getBody();
    }

    public void removeUser(String userName) {
        final String URL = localhostUser + "delete/" + userName;
        restTemplate.delete(URL);
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
/*

    @Override
    public void changePassword(String password, User user) {
        // TODO Auto-generated method stub

    }

    @Override
    public User createUser(String name, String password) throws UserAlreadyExistsException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void removeUserName(String name) throws UserNotFoundException {
        // TODO Auto-generated method stub

    }*/
}
