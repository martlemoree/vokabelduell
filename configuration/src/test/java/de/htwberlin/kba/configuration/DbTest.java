package de.htwberlin.kba.configuration;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.impl.UserDaoImpl;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DbTest {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpaDemoPU");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Test
    public void createDbUser() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        User user = new User(1L,"MartinTheBrain123","123qwe");
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        userDaoImpl.createUser(user);
        entityTransaction.commit();
    }
}
