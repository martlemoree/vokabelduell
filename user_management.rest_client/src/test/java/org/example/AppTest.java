package org.example;

import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

/**
 * Unit test for simple App.
 */
public class AppTest
{

    @Test
    public void testRestTemplate(){
        RestTemplateBuilder temp = new RestTemplateBuilder();
        UserRestAdapter adapter = new UserRestAdapter(temp);

        System.out.println(adapter.getUserList());

    }
}
