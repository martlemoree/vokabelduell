package de.htwberlin.kba.configuration;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.impl.UserDaoImpl;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
import java.util.List;

public class DbTest {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpaDemoPU");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    /*
    @Test
    public void createDbUser() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        User user = new User(1L,"MartinTheBrain123","123qwe");
        entityManager.persist(user);
        entityTransaction.commit();
    }
     */

    /*
    @Test
    public void findUserByUserName() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        TypedQuery<User> q = entityManager.createQuery("SELECT users FROM User AS users WHERE users.userName <= :value", User.class);
        q.setParameter("value", "MartinTheBrain123");
        List<User> userList = q.getResultList();
        System.out.println(userList);
        entityTransaction.commit();
    }
    */

    @Test
    public void deleteUserById() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        List<User> users = entityManager.createQuery("FROM User AS users", User.class).getResultList();
        for (User user : users) {
            entityManager.remove(user);
        }
        entityTransaction.commit();
    }

    /*
    @Test
    public void updateUserName() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        List<User> users = entityManager.createQuery("SELECT users FROM User AS users WHERE users.userName LIKE '%123'", User.class).getResultList();
        for (User user : users) {
            entityManager.merge(user);
        }
        entityTransaction.commit();
    }
     */
}
