package com.core.services;


import com.core.models.BasicModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class StorageService {
    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final Path rootLocation = Paths.get("upload-dir");

    public BasicModel store(MultipartFile file) {
        BasicModel basicModel = new BasicModel();
        String fileName = String.valueOf(Timestamp.valueOf(LocalDateTime.now()).getTime()) + file.getOriginalFilename();
        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName));
            basicModel.setPayload(fileName);
        } catch (Exception e) {
            throw new RuntimeException("FAILED_TO_UPLOAD_IMAGE");
        }
        return basicModel;
    }

    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAILED_TO_LOAD_IMAGE");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAILED_TO_LOAD_IMAGE");
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }
}
