package net.ddns.asadal.asadalrp.controller.admin.library;

import net.ddns.asadal.asadalrp.domain.library.AbstractLibraryDomain;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

public interface CommonUnPicturedController<T extends AbstractLibraryDomain> extends CommonController<T> {

    @PostMapping("/save")
    ResponseEntity<String> save(T libraryDomain);

    @PostMapping("/update")
    ResponseEntity<String> update(T libraryDomain);
}
