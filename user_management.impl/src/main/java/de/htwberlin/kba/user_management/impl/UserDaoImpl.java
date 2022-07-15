package de.htwberlin.kba.user_management.impl;

import de.htwberlin.kba.user_management.export.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
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
    public User getUserByName(String name) {
      //  User user = entityManager.find(User.class, name);
        TypedQuery<User> q = entityManager.createQuery("Select u from User as u where u.userName = :name", User.class);
        q.setParameter("name", name);
        User user = q.getSingleResult();
        if (user == null) {
            throw new EntityNotFoundException("Can't find User with User Name" + name);
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

}
