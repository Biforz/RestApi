package org.example.restApi.service;

import org.apache.commons.io.IOUtils;
import org.example.restApi.model.Event;
import org.example.restApi.model.File;
import org.example.restApi.model.User;
import org.example.restApi.repository.FileRepository;
import org.example.restApi.repository.impl.HibernateFileRepositoryImpl;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig
public class FileService {
    private final FileRepository fileRepository;
    private UserService userService;
    private EventService eventService;

    public FileService() {
        this.fileRepository = new HibernateFileRepositoryImpl();
    }

    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    public File getFileById(Long id) {
        return fileRepository.findById(id);
    }

    public File updateFile(Long id, File file) {
        return fileRepository.update(id, file);
    }

    public File saveFile(File file) {
        return fileRepository.save(file);
    }

    public void deleteFile(Long id) {
        fileRepository.delete(id);
    }

//    public File uploadFile(Long id, Part part) {
//        String fileName = part.getSubmittedFileName();
//
//        File file = new File();
//        file.setName(fileName);
//        file.setFilePath("/Users/Vadik/Desktop/upload" + fileName);
//
////        String uploadDir = "/Users/Vadik/Desktop/upload";
////        Files.createDirectories(Paths.get(uploadDir));
////        String filePath = uploadDir + "/" + fileName;
////        Files.write(Paths.get(filePath), fileData);
//
//        User user = userService.findUserById(id);
//
//        Event event = new Event();
//        event.setUser(user);
//        event.setFile(file);
//        eventService.saveEvent(event);
//
//        return file;
//    }
}
