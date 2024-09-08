package com.dev.blog.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

public class ImageUtils {
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/blog/src/main/resources/static/images";
    public static String uploadImage(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR);
        Path filePath ;
        if(Files.notExists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String fileCode = new Date().toString();
        try {
            InputStream inputStream= file.getInputStream();
            filePath = uploadPath.resolve(fileCode + "-" + file.getOriginalFilename());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioex) {
            throw new IOException("Could not save file" + file.getOriginalFilename() + ioex);
        }
        return filePath.toString();
    }
}
