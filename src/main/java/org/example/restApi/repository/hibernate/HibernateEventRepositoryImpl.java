package org.example.restApi.repository.hibernate;

import org.example.restApi.config.HibernateSessionFactory;
import org.example.restApi.model.Event;
import org.example.restApi.repository.EventRepository;
import org.hibernate.Session;

import java.util.List;

public class HibernateEventRepositoryImpl implements EventRepository {
    @Override
    public List<Event> findAll() {
        try (Session session = HibernateSessionFactory.session()) {
            return session.createQuery("FROM Event", Event.class).list();
        }
    }

    @Override
    public Event findById(Long id) {
        try (Session session = HibernateSessionFactory.session()) {
            return session.get(Event.class, id);
        }
    }

    @Override
    public Event save(Event event) {
        try (Session session = HibernateSessionFactory.session()) {
            session.beginTransaction();
            session.save(event);
            session.getTransaction().commit();
            return event;
        }
    }

    @Override
    public Event update(Long id, Event event) {
        try (Session session = HibernateSessionFactory.session()) {
            session.beginTransaction();
            session.merge(event);
            session.getTransaction().commit();
            return event;
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = HibernateSessionFactory.session()) {
            session.beginTransaction();
            Event event = findById(id);
            session.remove(event);
            session.getTransaction().commit();
        }
    }
}
