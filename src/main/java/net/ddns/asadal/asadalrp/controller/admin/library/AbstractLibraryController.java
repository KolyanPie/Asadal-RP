package net.ddns.asadal.asadalrp.controller.admin.library;

import net.ddns.asadal.asadalrp.domain.AbstractDomain;
import net.ddns.asadal.asadalrp.service.library.CommonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class AbstractLibraryController<T extends AbstractDomain, S extends CommonService<T>>
        implements CommonController<T> {

    protected final S service;

    public AbstractLibraryController(S service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<T> get(Long id) {
        T libraryObject = service.get(id);

        if (libraryObject == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(libraryObject);
    }

    @Override
    public ResponseEntity<Long> remove(Long id) {
        if (service.remove(id)) {
            return ResponseEntity.ok(id);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Map<String, List<Map<String, Object>>>> find(String term) {
        return ResponseEntity.ok(Collections.singletonMap("results", service.find(term)));
    }
}
