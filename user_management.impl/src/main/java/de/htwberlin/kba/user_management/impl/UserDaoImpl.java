package de.htwberlin.kba.user_management.impl;

import de.htwberlin.kba.user_management.export.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createUser(User user) throws SQLException {
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
    public User getUserByName(String userName) {
        TypedQuery<User> query = entityManager.createNamedQuery("getUserByUserName", User.class);
        query.setParameter("userName", userName);
        User user = query.getSingleResult();
        if (user == null) {
            throw new EntityNotFoundException("Can't find user with username" + userName);
        } else {
            return user;
        }
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
    public void deleteUser(User user) {
        entityManager.remove(user);
    }

    @Override
    public void deleteUserId(Long userId) {

        //hier named query bei der alle games / requests löschen

        User user = entityManager.find(User.class, userId);
        if (user == null) {
            throw new EntityNotFoundException("Can't find User with userId" + userId);
        } else {
        }
        entityManager.remove(user);
    }

}
