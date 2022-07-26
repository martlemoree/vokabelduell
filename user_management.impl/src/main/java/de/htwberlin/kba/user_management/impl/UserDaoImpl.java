package de.htwberlin.kba.user_management.impl;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.SQLException;
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
        User user = entityManager.find(User.class, userId);
        if (user == null) {
            throw new EntityNotFoundException("Can't find User with userId" + userId);
        } else {
            return user;
        }
    }

    @Override
    public User getUserByName(String userName) throws UserNotFoundException {
        User user;
        try {
            TypedQuery<User> query = entityManager.createNamedQuery("getUserByUserName", User.class);
            query.setParameter("userName", userName);
            user = query.getSingleResult();
        } catch (NoResultException e) {
            throw new UserNotFoundException("Der User wurde nicht gefunden. Versuche es noch einmal");
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createNamedQuery("getAllUsers", User.class);
        List<User> allUsers = query.getResultList();
        return allUsers;
    }

    @Override
    public void deleteUserId(Long userId) throws UserNotFoundException {

        //hier named query bei der alle games / requests löschen

        User user;
        try {
            user = entityManager.find(User.class, userId);
        } catch (NoResultException e) {
            throw new UserNotFoundException("Der User mit der Id "+userId + " wurde nicht gefunden.");
        }
        entityManager.remove(user);
    }

}
