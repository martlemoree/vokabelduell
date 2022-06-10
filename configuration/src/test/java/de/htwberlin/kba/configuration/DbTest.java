package de.htwberlin.kba.configuration;

import de.htwberlin.kba.game_management.export.Request;
import de.htwberlin.kba.user_management.export.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
import java.util.List;

public class DbTest {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpaDemoPU");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Test
    public void createDbUser() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        User user = new User("MartinTheBrain123","123qwe");
        entityManager.persist(user);
        entityTransaction.commit();
    }

    @Test
    public void findUserByUserName() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        TypedQuery<User> q = entityManager.createQuery("FROM User AS users WHERE users.userName <= :value", User.class);
        q.setParameter("value", "MartinTheBrain123");
        List<User> userList = q.getResultList();
        System.out.println(userList);
        entityTransaction.commit();
    }

    @Test
    public void deleteAllUsers() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        List<User> users = entityManager.createQuery("FROM User AS users", User.class).getResultList();
        for (User user : users) {
            entityManager.remove(user);
        }
        entityTransaction.commit();
    }

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

    @Test
    public List<User> getAllUsers() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        TypedQuery<User> query = entityManager.createQuery("FROM User AS users", User.class);
        List<User> allUsers = query.getResultList();
        entityTransaction.commit();

        Assertions.assertEquals(3, allUsers.size());
        return allUsers;
    }

    @Test
    public List<Request> getAllPendingRequestsTest() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        TypedQuery<Request> query = entityManager.createQuery("FROM Request AS requests WHERE requests.requestStatus = 'PENDING'", Request.class);
        List<Request> allPendingRequests = query.getResultList();
        entityTransaction.commit();
        return allPendingRequests;
    }

}
