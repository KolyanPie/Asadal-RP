package net.ddns.asadal.asadalrp.controller.admin.library;

import net.ddns.asadal.asadalrp.domain.AbstractDomain;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

public interface CommonUnPicturedController<T extends AbstractDomain> extends CommonController<T> {

    @PostMapping("/save")
    ResponseEntity<String> save(T libraryDomain);

    @PostMapping("/update")
    ResponseEntity<String> update(T libraryDomain);
}
