package org.example.restApi.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.restApi.model.File;
import org.example.restApi.service.FileService;
import org.example.restApi.util.ObjMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/files")
public class FileServlet extends HttpServlet {
    private FileService fileService;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        File file = new ObjectMapper().readValue(req.getReader(), File.class);
        fileService.saveFile(file);
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
