package net.ddns.asadal.asadalrp.controller.admin.library;

import net.ddns.asadal.asadalrp.domain.library.AbstractLibraryDomain;
import net.ddns.asadal.asadalrp.service.library.CommonUnPicturedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractUnPicturedLibraryController<T extends AbstractLibraryDomain, S extends CommonUnPicturedService<T>>
        extends AbstractLibraryController<T, S> implements CommonUnPicturedController<T> {

    public AbstractUnPicturedLibraryController(S service) {
        super(service);
    }

    @Override
    public ResponseEntity<String> save(T libraryDomain) {
        String answer = service.save(libraryDomain);
        if (answer.equals(libraryDomain.getName())) {
            return ResponseEntity.ok(answer);
        } else {
            return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> update(T libraryDomain) {
        if (libraryDomain.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (service.get(libraryDomain.getId()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String answer = service.update(libraryDomain);
        if (answer.equals(libraryDomain.getName())) {
            return ResponseEntity.ok(answer);
        } else {
            return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
        }
    }
}
