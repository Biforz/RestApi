package org.example.restApi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.restApi.model.Event;
import org.example.restApi.model.File;
import org.example.restApi.model.User;
import org.example.restApi.service.EventService;
import org.example.restApi.service.FileService;
import org.example.restApi.service.UserService;
import org.example.restApi.util.ObjMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@MultipartConfig()
@WebServlet("/files")
public class FileRestControllerV1 extends HttpServlet {
    private final FileService fileService = new FileService();
    private final UserService userService = new UserService();
    private final EventService eventService = new EventService();

    @Override
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");

        if (id == null) {
            List<File> fileList = fileService.getAllFiles();
            ObjMapper.objectToJson(fileList, resp);
        } else {
            File file = fileService.getFileById(Long.valueOf(id));
            ObjMapper.objectToJson(file, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");
        User user = userService.findUserById(Long.valueOf(userId));
        Part filePart = req.getPart("file");
        filePart.write(new java.io.File("/Users/Vadik/Desktop/upload/" + filePart.getSubmittedFileName()).toString());

        File file = new File();
        file.setName(filePart.getSubmittedFileName());
        file.setFilePath("/Users/Vadik/Desktop/upload/" + filePart.getSubmittedFileName());
        fileService.saveFile(file);

        Event event = new Event();
        event.setUser(user);
        event.setFile(file);
        eventService.saveEvent(event);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        File file = new ObjectMapper().readValue(req.getReader(), File.class);
        Long id = Long.valueOf(req.getParameter("id"));
        fileService.updateFile(id, file);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.parseLong(req.getParameter("id"));
        File file = fileService.getFileById(id);
        fileService.deleteFile(file.getId());
    }
}
