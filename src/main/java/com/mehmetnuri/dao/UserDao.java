package com.mehmetnuri.dao;

import com.mehmetnuri.domain.User;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Singleton
public class UserDao {

    @Inject
    EntityManager entityManager;

    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    public User findByEmail(String email) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void updateUser(Long id, User user) {
        User userToUpdate = entityManager.find(User.class, id);
        if (null != userToUpdate) {
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setEmail(user.getEmail());
        } else {
            throw new RuntimeException("No such user available");
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteUser(Long id) {
        User user = findById(id);
        entityManager.remove(user);
    }
}
