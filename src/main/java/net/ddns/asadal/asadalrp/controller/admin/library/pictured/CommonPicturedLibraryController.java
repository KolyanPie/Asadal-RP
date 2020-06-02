package net.ddns.asadal.asadalrp.controller.admin.library.pictured;

import net.ddns.asadal.asadalrp.controller.admin.library.CommonController;
import net.ddns.asadal.asadalrp.domain.AbstractPicturedDomain;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CommonPicturedLibraryController<T extends AbstractPicturedDomain> extends CommonController<T> {

    @PostMapping("/save")
    ResponseEntity<String> save(T libraryDomain, @RequestParam("picture") MultipartFile picture) throws IOException;

    @PostMapping("/update")
    ResponseEntity<String> update(T libraryDomain, @RequestParam("picture") MultipartFile picture) throws IOException;
}
