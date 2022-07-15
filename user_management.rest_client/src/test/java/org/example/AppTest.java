package org.example;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

/**
 * Unit test for simple App.
 */
public class AppTest
{

    @Test
    public void testRestTemplate(){
        RestTemplate temp = new RestTemplate();
        UserRestAdapter adapter = new UserRestAdapter(temp);

        System.out.println(adapter.getUserList());

    }
}
