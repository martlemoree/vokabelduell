package org.example;

import static org.junit.Assert.assertTrue;

import de.htwberlin.kba.user_management.export.User;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testRestTemplate(){
        RestTemplate temp = new RestTemplate();
        UserRestAdapter adapter = new UserRestAdapter(temp);

        System.out.println(adapter.getUserList());

    }
}
