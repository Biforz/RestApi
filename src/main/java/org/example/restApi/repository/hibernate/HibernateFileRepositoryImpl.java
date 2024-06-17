package org.example.restApi.repository.hibernate;

import org.example.restApi.config.HibernateSessionFactory;
import org.example.restApi.model.File;
import org.example.restApi.repository.FileRepository;
import org.hibernate.Session;

import java.util.List;

public class HibernateFileRepositoryImpl implements FileRepository {
    @Override
    public List<File> findAll() {
        try (Session session = HibernateSessionFactory.session()) {
            return session.createQuery("FROM File").list();
        }
    }

    @Override
    public File findById(Long id) {
        try (Session session = HibernateSessionFactory.session()) {
            return session.get(File.class, id);
        }
    }

    @Override
    public File save(File file) {
        try (Session session = HibernateSessionFactory.session()) {
            session.beginTransaction();
            session.save(file);
            session.getTransaction().commit();
            return file;
        }
    }

    @Override
    public File update(Long id, File file) {
        try (Session session = HibernateSessionFactory.session()) {
            session.beginTransaction();
            session.merge(file);
            session.getTransaction().commit();
            return file;
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
