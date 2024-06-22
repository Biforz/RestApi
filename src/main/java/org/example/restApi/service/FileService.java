package org.example.restApi.service;

import org.example.restApi.model.File;
import org.example.restApi.repository.FileRepository;
import org.example.restApi.repository.impl.HibernateFileRepositoryImpl;

import javax.servlet.annotation.MultipartConfig;
import java.util.List;

@MultipartConfig
public class FileService {
    private final FileRepository fileRepository;

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
}
