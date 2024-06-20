package org.example.restApi.service;

import org.example.restApi.model.User;
import org.example.restApi.repository.UserRepository;
import org.example.restApi.repository.impl.HibernateUserRepositoryImpl;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new HibernateUserRepositoryImpl();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        return userRepository.update(id, user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }
}
