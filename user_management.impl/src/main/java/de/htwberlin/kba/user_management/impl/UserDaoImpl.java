package de.htwberlin.kba.user_management.impl;

import de.htwberlin.kba.user_management.export.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createUser(User user) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(user);
        entityTransaction.commit();
    }

    @Override
    public User getUserById(Long userId) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        User user = entityManager.find(User.class, userId);
        if (user == null) {
            throw new EntityNotFoundException("Can't find User with userId" + userId);
        } else {
            entityTransaction.commit();
            return user;
        }
    }

    @Override
    public User getUserByName(String name) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        User user = entityManager.find(User.class, name);
        entityTransaction.commit();
        if (user == null) {
            throw new EntityNotFoundException("Can't find User with User Name" + name);
        } else {
            return user;
        }
    }

    @Override
    public void updateUser(User user) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(user);
        entityTransaction.commit();
    }

    @Override
    public List<User> getAllUsers() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        TypedQuery<User> query = entityManager.createQuery("FROM User AS users", User.class);
        List<User> allUsers = query.getResultList();
        entityTransaction.commit();
        return allUsers;
    }

    @Override
    public void deleteUser(User user) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(user);
        entityTransaction.commit();
    }

}
