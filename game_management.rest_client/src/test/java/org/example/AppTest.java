package org.example;

import static org.junit.Assert.assertTrue;

import de.htwberlin.kba.game_management.export.Request;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.user_management.impl.UserDao;
import de.htwberlin.kba.user_management.impl.UserDaoImpl;
import de.htwberlin.kba.user_management.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    RestTemplate temp = new RestTemplate();
    RequestRestAdapter requestRestAdapter = new RequestRestAdapter(temp);

    @Test
    public void RequestCreateRequest(){
        System.out.println(requestRestAdapter.getAllRequests());

    }
}
