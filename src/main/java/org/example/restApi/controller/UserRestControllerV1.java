package org.example.restApi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.restApi.model.User;
import org.example.restApi.service.UserService;
import org.example.restApi.util.ObjMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserRestControllerV1 extends HttpServlet {
    private UserService userService;

    @Override
    public void init() {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        if (id == null) {
            List<User> usersList = userService.findAllUsers();
            ObjMapper.objectToJson(usersList, response);
        } else {
            User users = userService.findUserById(Long.valueOf(id));
            ObjMapper.objectToJson(users, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = new ObjectMapper().readValue(request.getReader(), User.class);
        userService.createUser(user);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = new ObjectMapper().readValue(request.getReader(), User.class);
        Long id = Long.valueOf(request.getParameter("id"));
        userService.updateUser(id, user);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        User user = userService.findUserById(id);
        userService.deleteUser(user.getId());
    }
}
