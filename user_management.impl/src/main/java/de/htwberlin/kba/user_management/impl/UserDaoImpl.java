package de.htwberlin.kba.user_management.impl;

import de.htwberlin.kba.user_management.export.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserById(Long userId) {
        TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user WHERE user.userId=:userId", User.class);
        query.setParameter("userId", userId);
        List<User> userResultList = query.getResultList();
        if (!userResultList.isEmpty()) {
            User user = userResultList.get(0);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("SELECT users FROM User AS users", User.class);
        List<User> allUsers = query.getResultList();
        return allUsers;
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);
    }

}
