package net.ddns.asadal.asadalrp.service.library.pictured;

import net.ddns.asadal.asadalrp.domain.library.AbstractPicturedLibraryDomain;
import net.ddns.asadal.asadalrp.repo.library.AbstractLibraryRepo;
import net.ddns.asadal.asadalrp.service.library.AbstractLibraryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public abstract class AbstractPicturedLibraryService<T extends AbstractPicturedLibraryDomain, R extends AbstractLibraryRepo<T>>
        extends AbstractLibraryService<T, R> implements CommonPicturedLibraryService<T> {

    @Value("${error.name.unavailable}")
    protected String nameUnavailable;
    @Value("${error.file.trouble}")
    protected String fileTrouble;
    @Value("${upload.path}")
    protected String uploadPath;

    public AbstractPicturedLibraryService(R repo) {
        super(repo);
    }

    @Override
    public String save(T libraryDomain, MultipartFile picture) throws IOException {
        String name = libraryDomain.getName();
        if (StringUtils.isEmpty(name) || repo.findByName(name) != null) {
            if (picture == null || picture.isEmpty()) {
                return nameUnavailable + "\n" + fileTrouble;
            }
            return nameUnavailable;
        }
        if (picture == null || picture.isEmpty()) {
            return fileTrouble;
        }

        savePicture(libraryDomain, picture);
        repo.save(libraryDomain);
        return libraryDomain.getName();
    }

    @Override
    public String update(T libraryDomain, MultipartFile picture) throws IOException {
        T libraryDomainFromDb = get(libraryDomain.getId());
        String name = libraryDomain.getName();

        if (StringUtils.isEmpty(name) || (!name.equals(libraryDomainFromDb.getName()) && repo.findByName(name) != null)) {
            return nameUnavailable;
        }

        if (picture != null && !picture.isEmpty()) {
            savePicture(libraryDomain, picture);
        } else {
            libraryDomain.setPicturePath(libraryDomainFromDb.getPicturePath());
        }
        repo.save(libraryDomain);
        return libraryDomain.getName();
    }

    private void savePicture(T domain, MultipartFile picture) throws IOException {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + picture.getOriginalFilename();

        picture.transferTo(new File(uploadPath + "/" + resultFilename));

        domain.setPicturePath(resultFilename);
    }
}
