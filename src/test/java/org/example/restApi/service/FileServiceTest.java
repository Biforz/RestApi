package org.example.restApi.service;

import org.example.restApi.model.File;
import org.example.restApi.repository.FileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class FileServiceTest {
    private final FileRepository fileRepository = Mockito.mock(FileRepository.class);
    private final FileService fileService = new FileService(fileRepository);
    private File testFile;

    @BeforeEach
    void setUp() {
        testFile = File.builder()
                .id(1L)
                .name("testName")
                .filePath("testFilePath")
                .build();
    }

    @Test
    void getAllEvents() {
        List<File> files = fileService.getAllFiles();
        files.add(testFile);

        when(fileRepository.findAll()).thenReturn(files);

        List<File> allFiles = fileService.getAllFiles();

        assertNotNull(allFiles);
        assertEquals(files, allFiles);
    }

    @Test
    void getEventById() {
        when(fileRepository.findById(1L)).thenReturn(testFile);
        File file = fileService.getFileById(1L);

        assertNotNull(file);
        assertEquals(file.getId(), testFile.getId());
        assertEquals(file.getName(), testFile.getName());
        assertEquals(file.getFilePath(), testFile.getFilePath());
    }

    @Test
    void saveFile() {
        when(fileRepository.save(testFile)).thenReturn(testFile);
        File result = fileService.saveFile(testFile);
        assertNotNull(result);
        assertEquals(result.getId(), testFile.getId());
        assertEquals(result.getName(), testFile.getName());
        assertEquals(result.getFilePath(), testFile.getFilePath());
    }

    @Test
    void updateFile() {
        when(fileRepository.update(1L, testFile)).thenReturn(testFile);

        File result = fileService.updateFile(1L, testFile);

        assertNotNull(result);
        assertEquals(result.getId(), testFile.getId());
        assertEquals(result.getName(), testFile.getName());
        assertEquals(result.getFilePath(), testFile.getFilePath());
    }

    @Test
    void deleteFile() {
        fileService.deleteFile(1L);
        verify(fileRepository, times(1)).delete(1L);
    }
}
