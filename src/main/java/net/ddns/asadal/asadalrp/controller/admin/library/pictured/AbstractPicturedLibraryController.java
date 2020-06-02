package net.ddns.asadal.asadalrp.controller.admin.library.pictured;

import net.ddns.asadal.asadalrp.controller.admin.library.AbstractLibraryController;
import net.ddns.asadal.asadalrp.domain.library.AbstractPicturedLibraryDomain;
import net.ddns.asadal.asadalrp.service.library.pictured.CommonPicturedLibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public abstract class AbstractPicturedLibraryController<T extends AbstractPicturedLibraryDomain, S extends CommonPicturedLibraryService<T>>
        extends AbstractLibraryController<T, S> implements CommonPicturedLibraryController<T> {

    public AbstractPicturedLibraryController(S service) {
        super(service);
    }

    @Override
    public ResponseEntity<String> save(T libraryDomain, MultipartFile file) throws IOException {
        String answer = service.save(libraryDomain, file);
        if (answer.equals(libraryDomain.getName())) {
            return ResponseEntity.ok(answer);
        } else {
            return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> update(T libraryDomain, MultipartFile file) throws IOException {
        if (libraryDomain.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (service.get(libraryDomain.getId()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String answer = service.update(libraryDomain, file);
        if (answer.equals(libraryDomain.getName())) {
            return ResponseEntity.ok(answer);
        } else {
            return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
        }
    }
}
