package org.example.restApi.service;

import org.example.restApi.model.User;
import org.example.restApi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final UserService userService = new UserService(userRepository);
    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .name("testName")
                .build();
    }

    @Test
    void createUser() {
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.createUser(user);

        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getName(), result.getName());
    }

    @Test
    void updateUser() {
        when(userRepository.update(1L, user)).thenReturn(user);
        User result = userService.updateUser(user.getId(), user);
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getName(), result.getName());
    }

    @Test
    void findUserById() {
        when(userRepository.findById(1L)).thenReturn(user);
        User result = userService.findUserById(1L);
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getName(), result.getName());
    }

    @Test
    void findAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userRepository.findAll()).thenReturn(users);
        List<User> result = userService.findAllUsers();
        assertNotNull(result);
        assertEquals(result, users);
    }

    @Test
    void deleteUser() {
        userService.deleteUser(1L);
        verify(userRepository, times(1)).delete(1L);
    }
}
