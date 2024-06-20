package org.example.restApi.repository.impl;

import org.example.restApi.config.HibernateSessionFactory;
import org.example.restApi.model.User;
import org.example.restApi.repository.UserRepository;
import org.hibernate.Session;

import java.util.List;

public class HibernateUserRepositoryImpl implements UserRepository {
    @Override
    public List<User> findAll() {
        try (Session session = HibernateSessionFactory.session()) {
            return session.createQuery("FROM User").list();
        }
    }

    @Override
    public User findById(Long id) {
        try (Session session = HibernateSessionFactory.session()) {
            return session.get(User.class, id);
        }
    }

    @Override
    public User save(User user) {
        try (Session session = HibernateSessionFactory.session()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return user;
        }
    }

    @Override
    public User update(Long id, User user) {
        try (Session session = HibernateSessionFactory.session()) {
            session.beginTransaction();
            session.merge(user);
            session.getTransaction().commit();
            return user;
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = HibernateSessionFactory.session()) {
            session.beginTransaction();
            session.remove(findById(id));
            session.getTransaction().commit();
        }
    }
}
