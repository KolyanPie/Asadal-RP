package net.ddns.asadal.asadalrp.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUtil {
    @Value("${upload.path}")
    private static String uploadPath;

    public static String saveFile(MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            String filename = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                if (!uploadDir.mkdir()) {
                    return null;
                }
            }
            try {
                file.transferTo(new File(uploadPath + "/" + filename));
            } catch (IOException e) {
                return null;
            }
            return filename;
        }
        return "";
    }
}
