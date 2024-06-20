package org.example.restApi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.example.restApi.model.Event;
import org.example.restApi.model.File;
import org.example.restApi.model.User;
import org.example.restApi.repository.FileRepository;
import org.example.restApi.repository.impl.HibernateFileRepositoryImpl;
import org.example.restApi.service.EventService;
import org.example.restApi.service.FileService;
import org.example.restApi.service.UserService;
import org.example.restApi.util.ObjMapper;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@MultipartConfig()
@WebServlet("/files")
public class FileRestControllerV1 extends HttpServlet {
    private final FileRepository fileRepository = new HibernateFileRepositoryImpl();
    private FileService fileService;
    private UserService userService;
    private EventService eventService;

    @Override
    public void init() {
        fileService = new FileService();
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
        String userIdHeader = req.getHeader("id");
        Long userId = Long.parseLong(userIdHeader);
        File file = new File();
        User user = userService.findUserById(userId);
        Event event = new Event();
        try {
            List<FileItem> multiFiles = fileUpload.parseRequest(req);
            for (FileItem fileItem : multiFiles) {
                fileItem.write(new java.io.File("/Users/Vadik/Desktop/upload/" + fileItem.getName()));

                file.setName(fileItem.getName());
                file.setFilePath("/Users/Vadik/Desktop/upload/" + fileItem.getName());


                event.setUser(user);
                event.setFile(file);
                eventService.saveEvent(event);

                fileService.saveFile(file);
            }
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        File file = new ObjectMapper().readValue(req.getReader(), File.class);
        Long id = Long.valueOf(req.getParameter("id"));
        fileService.updateFile(id, file);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        File file = fileService.getFileById(id);
        fileService.deleteFile(file.getId());
    }
}
